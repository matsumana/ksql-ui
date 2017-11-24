package actors

import actors.Client.Select
import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.pattern.pipe
import akka.stream.ActorMaterializer
import akka.stream.ActorMaterializerSettings
import akka.util.ByteString
import play.api.libs.json.Json
import play.api.libs.json.OFormat

import scala.concurrent.ExecutionContext.Implicits.global

object Client {
  def props: Props = Props[Client]

  final case class Select(query: String)
}

class Client extends Actor with ActorLogging {

  import MediaTypes._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val receiver : ActorRef = system.actorOf(Receiver.props, "receiver")

  override def receive: Receive = {
    case Select(query) =>
      val json = Json.toJson(KsqlQuery(query))
      val data = ByteString(json.toString())
      Http().singleRequest(HttpRequest(HttpMethods.POST,
        uri = "http://localhost:8080/query",
        entity = HttpEntity(`application/json`, data)
      ))
        .pipeTo(receiver)
  }
}

object Receiver {
  def props: Props = Props[Receiver]
}

class Receiver extends Actor with ActorLogging {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer(ActorMaterializerSettings(context.system))

  override def receive: Receive = {
    case HttpResponse(StatusCodes.OK, headers, entity, _) =>
      entity.dataBytes.runForeach(body => log.info("Got response, body: " + body.utf8String))
    case resp@HttpResponse(code, _, _, _) =>
      log.info("Request failed, response code: " + code)
      resp.discardEntityBytes()
  }
}


final case class KsqlQuery(ksql: String)

object KsqlQuery {
  implicit val format: OFormat[KsqlQuery] = Json.format[KsqlQuery]
}
