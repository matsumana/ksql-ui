package models.ksql

import play.api.libs.json.{ JsValue, Json, OFormat }

import scala.collection.immutable

case class KsqlRow(columns: immutable.Seq[JsValue])

object KsqlRow {
  implicit val format: OFormat[KsqlRow] = Json.format[KsqlRow]
}
