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

class QueryKSQLActor(out: ActorRef) extends Actor with ActorLogging {

  import MediaTypes._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  // TODO: Is it incorrect to make a new actor every time? Not sure.....

  // TODO: receive correct class from FE
  override def receive: Receive = {
    case request: JsValue =>
      APIRequest.format.reads(request) match {
        case JsSuccess(value, path) =>
          System.out.println("hi did we ever get here")
          val receiver = context.actorOf(Props(new ReceiveFromKSQLActor(out, value.sequence, value.sql)))
          val json = Json.toJson(KsqlQuery(value.sql))
          val data = ByteString(json.toString())
          Http().singleRequest(HttpRequest(HttpMethods.POST,
            uri = "http://10.127.119.68:8080/query",
            entity = HttpEntity(`application/json`, data)
          )).pipeTo(receiver)
        case JsError(errors) =>
          System.out.println("Bad Request: " + errors)
      }
  }
}

object QueryKSQLActor {
  def props(out: ActorRef): Props = Props(new QueryKSQLActor(out))
}
