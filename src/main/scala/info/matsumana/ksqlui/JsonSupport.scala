package info.matsumana.ksqlui

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import info.matsumana.ksqlui.model.{ Request, ResponseText }
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  // sandox
  implicit val helloRequestProtocol = jsonFormat1(HelloRequest)
  implicit val helloResponseProtocol = jsonFormat1(HelloResponse)

  // app
  implicit val requestProtocol = jsonFormat2(Request)
  implicit val responseTextProtocol = jsonFormat4(ResponseText)

  // FIXME can't compile
  //  implicit def responseTableProtocol[A: Seq[Any]] = jsonFormat5(ResponseTable.apply[A])
}
