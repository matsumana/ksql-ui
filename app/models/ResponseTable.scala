package models

import play.api.libs.json.JsArray
import play.api.libs.json.JsNumber
import play.api.libs.json.JsObject
import play.api.libs.json.JsString
import play.api.libs.json.Json
import play.api.libs.json.OWrites

case class ResponseTable[A](
  sequence: Int,
  sql: String,
  mode: Int = 1,
  title: Seq[String],
  data: Seq[A]
)

object ResponseTable {
  implicit def responseTableWrites[T](implicit fmt: OWrites[T]): OWrites[ResponseTable[T]] = new OWrites[ResponseTable[T]] {
    override def writes(ts: ResponseTable[T]): JsObject = {
      JsObject(Seq(
        "sequence" -> JsNumber(ts.sequence),
        "sql" -> JsString(ts.sql),
        "mode" -> JsNumber(ts.mode),
        "title" -> JsArray(ts.title.map(Json.toJson(_))),
        "data" -> JsArray(ts.data.map(Json.toJson(_)))
      ))
    }
  }
}
