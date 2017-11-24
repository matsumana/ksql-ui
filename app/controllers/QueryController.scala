package controllers

import javax.inject.Inject
import javax.inject.Singleton

import actors.QueryKSQLActor
import akka.actor.ActorSystem
import akka.stream.Materializer
import models.APIRequest
import models.ResponseTable
import play.api.libs.json.JsValue
import play.api.libs.streams.ActorFlow
import play.api.mvc.WebSocket.MessageFlowTransformer
import play.api.mvc._

/**
  * Handle queries sent by the user.
  * Uses websockets!
  */
@Singleton
class QueryController @Inject() (cc: ControllerComponents)(implicit system: ActorSystem, mat: Materializer)
  extends AbstractController(cc) {

  //implicit val messageFlowTransformer = MessageFlowTransformer.jsonMessageFlowTransformer[APIRequest, ResponseTable]

  def query() = WebSocket.accept[JsValue, JsValue] { request =>
    ActorFlow.actorRef { out =>
      QueryKSQLActor.props(out)
    }
  }
}
