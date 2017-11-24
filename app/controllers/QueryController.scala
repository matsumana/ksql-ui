package controllers

import javax.inject.Inject
import javax.inject.Singleton

import actors.QueryKSQLActor
import akka.actor.ActorSystem
import akka.stream.Materializer
import play.api.Configuration
import play.api.libs.json.JsValue
import play.api.libs.streams.ActorFlow
import play.api.mvc._

/**
  * Handle queries sent by the user.
  * Uses websockets!
  */
@Singleton
class QueryController @Inject()
  (cc: ControllerComponents, config: Configuration)
  (implicit system: ActorSystem, mat: Materializer)
  extends AbstractController(cc) {

  // TODO: make this use typed request / response types
  def query() = WebSocket.accept[JsValue, JsValue] { request =>
    ActorFlow.actorRef { out =>
      QueryKSQLActor.props(out, config.get[String]("ksql.uri"))
    }
  }
}
