package models

import play.api.libs.json.{ JsValue, Json, OWrites }

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
