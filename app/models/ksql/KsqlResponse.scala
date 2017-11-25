package models.ksql

import play.api.libs.functional.syntax._
import play.api.libs.json.{ JsPath, Reads }

case class KsqlResponse(row: Option[KsqlRow], errorMessage: Option[KsqlErrorMessage])

object KsqlResponse {
  implicit val reads: Reads[KsqlResponse] = (
    (JsPath \ "row").readNullable[KsqlRow] and
      (JsPath \ "errorMessage").readNullable[KsqlErrorMessage]
    ) (KsqlResponse.apply _)
}
