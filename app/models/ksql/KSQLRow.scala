package models.ksql

import play.api.libs.json.JsValue
import play.api.libs.json.Json
import play.api.libs.json.OFormat

import scala.collection.immutable

case class KSQLRow(columns: immutable.Seq[JsValue])

object KSQLRow {
  implicit val format: OFormat[KSQLRow] = Json.format[KSQLRow]
}
