package info.matsumana.tsujun

import akka.actor.{ ActorRef, ActorSystem }
import akka.event.Logging
import akka.http.scaladsl.model.{ ContentTypes, HttpEntity }
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete

trait Routes extends JsonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[Routes])

  def userRegistryActor: ActorRef

  lazy val routes: Route =
    path("") {
      get {
        getFromResource("templates/index.html")
      }
    } ~ {
      pathPrefix("assets") {
        getFromResourceDirectory("assets/")
      }
    }
}
