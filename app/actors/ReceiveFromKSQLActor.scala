package actors

import akka.actor.{ Actor, ActorLogging, ActorRef, ActorSystem, Props, _ }
import akka.http.scaladsl.model._
import akka.stream.{ ActorMaterializer, ActorMaterializerSettings }
import akka.util.ByteString
import models.ResponseTable
import models.ksql.KSQLResponse
import play.api.Logger
import play.api.libs.json.{ JsError, JsSuccess, JsValue, Json }

import scala.util.Try

class ReceiveFromKSQLActor(out: ActorRef, sequence: Int, sql: String) extends Actor with ActorLogging {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer(ActorMaterializerSettings(context.system))

  override def receive: Receive = {
    case Status.Failure(e) =>
      out ! Json.obj("msg" -> "Error in HTTP Request.", "errors" -> e.toString)
    case HttpResponse(StatusCodes.OK, headers, entity, _) =>
      entity.dataBytes.runForeach { byteString: ByteString =>
        val body = byteString.utf8String
        Try(Json.parse(body)).map { jsValue: JsValue =>
          KSQLResponse.reads.reads(jsValue) match {
            case JsSuccess(value, _) =>
              value.row match {
                case Some(ksqlrow) =>
                  out ! Json.toJson(ResponseTable(sequence, sql, 1, ksqlrow.columns))
                case None =>
                  // In this case, 'errorMessage' will definitely be defined.
                  val errorMessage = value.errorMessage.get
                  // If we get an error about the limit being reached, simply stop.
                  if (errorMessage.message.startsWith("LIMIT")) {
                    Logger.debug("stopping myself!")
                    context.stop(self)
                  } else {
                    out ! Json.obj("errors" -> value.errorMessage.get)
                  }
              }
            case JsError(errors) =>
              out ! Json.obj("errors" -> errors.toString())
          }
        }
      }
    case resp @ HttpResponse(code, _, _, _) =>
      out ! Json.obj("error" -> ("Request failed, response code: " + code))
      resp.discardEntityBytes()
  }
}

object ReceiveFromKSQLActor {
  def props: Props = Props[ReceiveFromKSQLActor]
}
