package info.matsumana.tsujun

import akka.actor.ActorRef
import akka.http.scaladsl.model._
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.testkit.ScalatestRouteTest
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.{Matchers, WordSpec}

class RoutesSpec extends WordSpec with Matchers with ScalaFutures with ScalatestRouteTest with Routes {

  override val userRegistryActor: ActorRef = system.actorOf(UserRegistryActor.props, "UserRegistry")

  lazy val r: Route = routes

  "Routes" should {
    "return no users if no present (GET /)" in {
      val request = HttpRequest(uri = "/")

      request ~> r ~> check {
        status should ===(StatusCodes.OK)
        contentType should ===(ContentTypes.`text/html(UTF-8)`)
      }
    }
  }
}
