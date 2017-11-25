package models

import play.api.libs.json.{ Json, OFormat }

case class ApiRequest(
                       sequence: Int,
                       sql: String
                     )

object ApiRequest {
  implicit val format: OFormat[ApiRequest] = Json.format[ApiRequest]
}
