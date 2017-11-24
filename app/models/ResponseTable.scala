package models

import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.OWrites

import scala.collection.immutable

case class ResponseTable(
  sequence: Int,
  sql: String,
  mode: Int = 1,
  data: immutable.Seq[JsValue]
)

object ResponseTable {
  implicit def writes: OWrites[ResponseTable] = Json.writes[ResponseTable]
}
