package info.matsumana.ksqlui

import akka.actor.{ ActorRef, ActorSystem }
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Route
import akka.stream.ActorMaterializer

import scala.concurrent.{ ExecutionContext, Future }
import scala.io.StdIn

object WebServer extends Routes {

  // set up ActorSystem and other dependencies here
  implicit val system: ActorSystem = ActorSystem("akkaHttpServer")
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  // Needed for the Future and its methods flatMap/onComplete in the end
  implicit val executionContext: ExecutionContext = system.dispatcher

  lazy val r: Route = routes
  override val dummyApiActor: ActorRef = system.actorOf(DummyApiActor.props, "dummyApiActor")

  def main(args: Array[String]) {
    val serverBindingFuture: Future[ServerBinding] = Http().bindAndHandle(r, "0.0.0.0", 8080)

    println(s"Server online at http://localhost:8080/\nPress RETURN to stop...")

    StdIn.readLine()

    serverBindingFuture
      .flatMap(_.unbind())
      .onComplete { done =>
        done.failed.map { ex => log.error(ex, "Failed unbinding") }
        system.terminate()
      }
  }
}
