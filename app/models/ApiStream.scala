package models

import play.api.libs.json.Json
import play.api.libs.json.OFormat

/**
 * This is the model returned to the frontend concerning stream information
 */
case class ApiStream(
  streamName: String,
  fields: List[Field]
)

object ApiStream {
  implicit val format: OFormat[ApiStream] = Json.format[ApiStream]
}
