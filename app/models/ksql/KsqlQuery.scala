package models.ksql

import play.api.libs.json.{ Json, OFormat }

final case class KsqlQuery(ksql: String)

object KsqlQuery {
  implicit val format: OFormat[KsqlQuery] = Json.format[KsqlQuery]
}
