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
import models.ksql.KsqlQuery
import play.api.libs.json.Json

import scala.concurrent.ExecutionContext.Implicits.global

class QueryKSQLActor(out: ActorRef) extends Actor with ActorLogging {

  import MediaTypes._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  // TODO: Is it incorrect to make a new actor every time? Not sure.....
  val receiver : ActorRef = context.actorOf(Props(new ReceiveFromKSQLActor(out)))

  // TODO: receive correct class from FE
  override def receive: Receive = {
    case query: String =>
      val json = Json.toJson(KsqlQuery(query))
      val data = ByteString(json.toString())
      Http().singleRequest(HttpRequest(HttpMethods.POST,
        uri = "http://10.127.119.68:8080/query",
        entity = HttpEntity(`application/json`, data)
      )).pipeTo(receiver)
  }
}

object QueryKSQLActor {
  def props(out: ActorRef): Props = Props(new QueryKSQLActor(out))
}
