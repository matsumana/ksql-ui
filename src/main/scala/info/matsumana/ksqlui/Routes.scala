package info.matsumana.ksqlui

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.model.ws.{ BinaryMessage, Message, TextMessage }
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete
import akka.stream.Materializer
import akka.stream.scaladsl.{ Flow, Sink, Source }
import info.matsumana.ksqlui.DummyApiActor.Start

trait Routes extends JsonSupport {

  implicit def system: ActorSystem

  implicit def materializer: Materializer

  lazy val log = Logging(system, classOf[Routes])

  def dummyApiActor: ActorRef

  def greeter: Flow[Message, Message, Any] =
    Flow[Message].mapConcat {
      case tm: TextMessage =>
        TextMessage(Source.single("Hello ") ++ tm.textStream ++ Source.single("!")) :: Nil
      case bm: BinaryMessage =>
        // ignore binary messages but drain content to avoid the stream being clogged
        bm.dataStream.runWith(Sink.ignore)
        Nil
    }

  lazy val routes: Route =
    get {
      pathSingleSlash {
        getFromResource("templates/index.html")
      }
    } ~ path("hello") {
      post {
        entity(as[HelloRequest]) { request =>
          complete {
            HelloResponse(message = s"Hello, ${request.name}!")
          }
        }
      }
    } ~ path("dummy") {
      get {
        dummyApiActor ! Start
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "ok"))
      }
    } ~ path("health") {
      get {
        complete(HttpEntity(ContentTypes.`text/plain(UTF-8)`, "ok"))
      }
    } ~ path("greeter") {
      handleWebSocketMessages(greeter)
    } ~ path("favicon.ico") {
      getFromResource("assets/favicon.ico")
    } ~ pathPrefix("assets") {
      getFromResourceDirectory("assets/")
    }
}
