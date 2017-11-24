package actors

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpEntity
import akka.http.scaladsl.model.HttpMethods
import akka.http.scaladsl.model.HttpRequest
import akka.http.scaladsl.model.MediaTypes
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import akka.util.ByteString
import models.APIRequest
import models.ksql.KsqlQuery
import play.api.libs.json.JsError
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global

class QueryKSQLActor(out: ActorRef, uri: String) extends Actor with ActorLogging {

  import MediaTypes._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  // TODO: receive correct class from FE
  override def receive: Receive = {
    case request: JsValue =>
      APIRequest.format.reads(request) match {
        case JsSuccess(value, path) =>
          // TODO: Is it incorrect to make a new actor every time? Not sure.....
          val receiver = context.actorOf(Props(new ReceiveFromKSQLActor(out, value.sequence, value.sql)))
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

object QueryKSQLActor {
  def props(out: ActorRef, uri: String): Props = Props(new QueryKSQLActor(out, uri))
}
