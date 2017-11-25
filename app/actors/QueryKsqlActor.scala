package actors

import akka.actor.{ Actor, ActorLogging, ActorRef, ActorSystem, Props }
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{ HttpEntity, HttpMethods, HttpRequest, MediaTypes }
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import akka.util.ByteString
import models.ApiRequest
import models.ksql.KsqlQuery
import play.api.libs.json.{ JsError, JsSuccess, JsValue, Json }

import scala.concurrent.ExecutionContext.Implicits.global

class QueryKsqlActor(out: ActorRef, uri: String) extends Actor with ActorLogging {

  import MediaTypes._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  // TODO: receive correct class from FE
  override def receive: Receive = {
    case request: JsValue =>
      ApiRequest.format.reads(request) match {
        case JsSuccess(value, path) =>
          // TODO: Is it incorrect to make a new actor every time? Not sure.....
          val receiver = context.actorOf(Props(new ReceiveFromKsqlActor(out, value.sequence, value.sql)))
          val json = Json.toJson(KsqlQuery(value.sql))
          val data = ByteString(json.toString())
          Http().singleRequest(HttpRequest(HttpMethods.POST,
            uri = uri + "/query",
            entity = HttpEntity(`application/json`, data)
          )).pipeTo(receiver)
        case JsError(errors) =>
          out ! Json.obj("msg" -> "bad request!", "errors" -> errors.toString())
      }
  }
}

object QueryKsqlActor {
  def props(out: ActorRef, uri: String): Props = Props(new QueryKsqlActor(out, uri))
}
