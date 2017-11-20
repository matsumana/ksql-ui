package info.matsumana.ksqlui

import akka.NotUsed
import akka.http.scaladsl.model.ws.{ BinaryMessage, Message, TextMessage }
import akka.stream.Materializer
import akka.stream.scaladsl.{ Flow, Sink, Source }
import info.matsumana.ksqlui.model._
import spray.json._

trait WebSocketHandler extends JsonSupport {

  implicit def materializer: Materializer

  def greeter: Flow[Message, Message, Any] = Flow[Message].mapConcat {
    case tm: TextMessage =>
      TextMessage(Source.single("Hello ") ++ tm.textStream ++ Source.single("!")) :: Nil
    case bm: BinaryMessage =>
      // ignore binary messages but drain content to avoid the stream being clogged
      bm.dataStream.runWith(Sink.ignore)
      Nil
  }

  def sql: Flow[Message, TextMessage.Strict, NotUsed] = Flow[Message].map {
    case TextMessage.Strict(json) =>
      val requst = json.parseJson.convertTo[Request]

      // FIXME
      // If SQL starts with 'CREATE'(case-insensitive), mode=0 and use ResponseText
      // else mode=1 and user ResponseTable
      val mode = 0
      val text =
        """Message
        ----------------------------
        Stream created and running
        """

      val response = ResponseText(requst.sequence, requst.sql, mode, text)
      TextMessage(response.toJson.compactPrint)
  }
}
