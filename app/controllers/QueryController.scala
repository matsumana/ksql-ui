package controllers

import javax.inject.{ Inject, Singleton }

import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.{ Flow, Sink, Source }
import play.api.mvc._

import scala.concurrent.duration._

/**
 * Handle queries sent by the user.
 * Uses websockets!
 */
@Singleton
class QueryController @Inject() (cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer)
    extends AbstractController(cc) {

  // TODO: actually implement this
  def query() = WebSocket.accept[String, String] { request =>

    // Just ignore the input
    val in = Sink.ignore

    // Send a single 'Hello!' message and close
    val out = Source.tick(1.second, 1.second, "Hello")

    Flow.fromSinkAndSource(in, out)
  }
}

