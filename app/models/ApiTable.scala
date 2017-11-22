package models

import play.api.libs.json.Json
import play.api.libs.json.OFormat

case class ApiTable(tableName: String, fields: List[Field])

object ApiTable {
  implicit val format: OFormat[ApiTable] = Json.format[ApiTable]
}
