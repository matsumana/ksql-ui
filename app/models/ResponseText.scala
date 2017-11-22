package models

import play.api.libs.json.Json
import play.api.libs.json.OWrites

case class ResponseText(
  sequence: Int,
  sql: String,
  mode: Int = 0,
  text: String
)

object ResponseText {
  implicit val writes: OWrites[ResponseText] = Json.writes[ResponseText]
}
