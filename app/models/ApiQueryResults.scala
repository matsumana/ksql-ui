package models

import play.api.libs.json.{ JsObject, Json, OFormat }

// TODO: this is incomplete, i think
case class ApiQueryResults(
  query: String,
  results: List[JsObject]
)

object ApiQueryResults {
  implicit val format: OFormat[ApiQueryResults] = Json.format[ApiQueryResults]
}
