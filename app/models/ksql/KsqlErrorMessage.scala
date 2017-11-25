package models.ksql

import play.api.libs.json.{ Json, OFormat }

import scala.collection.immutable

case class KsqlErrorMessage(message: String, stackTrace: immutable.Seq[String])

object KsqlErrorMessage {
  implicit val format: OFormat[KsqlErrorMessage] = Json.format[KsqlErrorMessage]
}
