package models

import play.api.libs.json.Json
import play.api.libs.json.OFormat

case class Request(
  sequence: Int,
  sql: String
)

object Request {
  implicit val format: OFormat[Request] = Json.format[Request]
}

