package models

import play.api.libs.json.Json
import play.api.libs.json.OFormat

case class APIRequest(
  sequence: Int,
  sql: String
)

object APIRequest {
  implicit val format: OFormat[APIRequest] = Json.format[APIRequest]
}
