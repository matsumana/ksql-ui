package models

case class ResponseText(
                         sequence: Int,
                         sql: String,
                         mode: Int = 0,
                         text: String
                       )
