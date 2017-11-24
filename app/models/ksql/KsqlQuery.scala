package models.ksql

import play.api.libs.json.Json
import play.api.libs.json.OFormat

final case class KsqlQuery(ksql: String)

object KsqlQuery {
  implicit val format: OFormat[KsqlQuery] = Json.format[KsqlQuery]
}
