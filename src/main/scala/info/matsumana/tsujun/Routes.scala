package info.matsumana.tsujun

import akka.actor.ActorSystem
import akka.event.Logging
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.directives.MethodDirectives.get
import akka.http.scaladsl.server.directives.RouteDirectives.complete

trait Routes extends JsonSupport {

  implicit def system: ActorSystem

  lazy val log = Logging(system, classOf[Routes])

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
    } ~ path("favicon.ico") {
      getFromResource("assets/favicon.ico")
    } ~ pathPrefix("assets") {
      getFromResourceDirectory("assets/")
    }
}
