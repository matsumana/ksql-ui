package models

import play.api.libs.json.{ Json, OFormat }

case class Field(fieldName: String, fieldType: String)

object Field {
  implicit val format: OFormat[Field] = Json.format[Field]
}
