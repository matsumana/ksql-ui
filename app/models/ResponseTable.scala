package models

case class ResponseTable[A](
  sequence: Int,
  sql: String,
  mode: Int = 1,
  title: Seq[String],
  data: Seq[A]
)
