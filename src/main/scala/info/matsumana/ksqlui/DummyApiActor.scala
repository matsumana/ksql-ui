package info.matsumana.ksqlui

import akka.actor.{ Actor, ActorLogging, Props }
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.{ ActorMaterializer, ActorMaterializerSettings }
import akka.util.ByteString

object DummyApiActor {

  final case object Start

  def props: Props = Props[DummyApiActor]
}

class DummyApiActor extends Actor with ActorLogging {

  import DummyApiActor._
  import akka.pattern.pipe
  import context.dispatcher

  final implicit val materializer: ActorMaterializer =
    ActorMaterializer(ActorMaterializerSettings(context.system))

  val http = Http(context.system)

  def receive: PartialFunction[Any, Unit] = {
    case Start =>
      log.info("received: Start")
      http.singleRequest(HttpRequest(uri = "https://www.google.co.jp/")).pipeTo(self)
    case HttpResponse(StatusCodes.OK, headers, entity, _) =>
      entity.dataBytes.runFold(ByteString(""))(_ ++ _).foreach { body =>
        log.info("Got response, body: " + body.utf8String)
      }
    case resp @ HttpResponse(code, _, _, _) =>
      log.info("Request failed, response code: " + code)
      resp.discardEntityBytes()
  }
}
