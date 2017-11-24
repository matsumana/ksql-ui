package models.ksql

import play.api.libs.functional.syntax._
import play.api.libs.json.JsPath
import play.api.libs.json.Reads

case class KSQLResponse(row: Option[KSQLRow], errorMessage: Option[KSQLErrorMessage])

object KSQLResponse {
  implicit val reads: Reads[KSQLResponse] = (
      (JsPath \ "row").readNullable[KSQLRow] and
      (JsPath \ "errorMessage").readNullable[KSQLErrorMessage]
    )  (KSQLResponse.apply _)
}
