package controllers

import javax.inject.Inject
import javax.inject.Singleton

import actors.QueryKSQLActor
import akka.actor.ActorSystem
import akka.stream.Materializer
import akka.stream.scaladsl.Flow
import akka.stream.scaladsl.Sink
import akka.stream.scaladsl.Source
import play.api.libs.streams.ActorFlow
import play.api.mvc._

import scala.concurrent.duration._

/**
  * Handle queries sent by the user.
  * Uses websockets!
  */
@Singleton
class QueryController @Inject() (cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer)
  extends AbstractController(cc) {

  def query() = WebSocket.accept[String, String] { request =>
    ActorFlow.actorRef { out =>
      QueryKSQLActor.props(out)
    }
  }
}
