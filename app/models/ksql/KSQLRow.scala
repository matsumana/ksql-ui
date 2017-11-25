package models.ksql

import play.api.libs.json.{ JsValue, Json, OFormat }

import scala.collection.immutable

case class KSQLRow(columns: immutable.Seq[JsValue])

object KSQLRow {
  implicit val format: OFormat[KSQLRow] = Json.format[KSQLRow]
}
