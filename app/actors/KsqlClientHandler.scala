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

import akka.actor._

object MyWebSocketActor {
  def props(out: ActorRef) = Props(new MyWebSocketActor(out))
}

// pass the message to the talk to ksql actor, and let it handle outputtnig
class MyWebSocketActor(out: ActorRef) extends Actor {

  private val talkToKSqlActor: ActorRef = context.actorOf(Props(new TalkToKSqlActor(out)))

  def receive = {
    case msg: String =>
      talkToKSqlActor ! msg
  }
}

// pls talk back to the original actor bro
class TalkToKSqlActor(out: ActorRef) extends Actor with ActorLogging {

  import MediaTypes._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()

  val receiver : ActorRef = system.actorOf(Receiver.props, "receiver")

  override def receive: Receive = {
    /*
    case Select(query) =>
      val json = Json.toJson(KsqlQuery(query))
      val data = ByteString(json.toString())
      Http().singleRequest(HttpRequest(HttpMethods.POST,
        uri = "http://localhost:8080/query",
        entity = HttpEntity(`application/json`, data)
      ))
        .pipeTo(receiver)
        */
    case msg: String =>
      out ! ("I received your message: " + msg)
  }
}
