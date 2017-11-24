package actors

import akka.actor.Actor
import akka.actor.ActorLogging
import akka.actor.ActorRef
import akka.actor.ActorSystem
import akka.actor.Props
import akka.actor._
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.ActorMaterializerSettings
import akka.util.ByteString
import models.ksql.KSQLResponse
import play.api.libs.json.JsError
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsValue
import play.api.libs.json.Json

import scala.util.Try

class ReceiveFromKSQLActor(out: ActorRef) extends Actor with ActorLogging {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer(ActorMaterializerSettings(context.system))

  override def receive: Receive = {
    case HttpResponse(StatusCodes.OK, headers, entity, _) =>
      entity.dataBytes.runForeach { byteString: ByteString =>
        val body = byteString.utf8String
        Try(Json.parse(body)).map { jsValue: JsValue =>
          KSQLResponse.reads.reads(jsValue) match {
            case JsSuccess(value, _) =>
              out ! ("woohoo success" + value.toString)
            case JsError(errors) =>
              out ! ("boooo it didn't work " + errors)
          }
        }
      }
    case resp@HttpResponse(code, _, _, _) =>
      out ! ("Request failed, response code: " + code)
      resp.discardEntityBytes()
  }
}

object ReceiveFromKSQLActor {
  def props: Props = Props[ReceiveFromKSQLActor]
}
