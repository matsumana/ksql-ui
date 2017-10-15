package info.matsumana.tsujun

import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport
import spray.json.DefaultJsonProtocol

trait JsonSupport extends SprayJsonSupport with DefaultJsonProtocol {
  implicit val helloRequestProtocol = jsonFormat1(HelloRequest)
  implicit val helloResponseProtocol = jsonFormat1(HelloResponse)
}
