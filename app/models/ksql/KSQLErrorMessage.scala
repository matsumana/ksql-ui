package models.ksql

import play.api.libs.json.Json
import play.api.libs.json.OFormat

import scala.collection.immutable

case class KSQLErrorMessage(message: String, stackTrace: immutable.Seq[String])

object KSQLErrorMessage {
  implicit val format: OFormat[KSQLErrorMessage] = Json.format[KSQLErrorMessage]
}
