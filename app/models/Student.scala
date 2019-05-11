package models

import play.api.libs.json.{JsValue, Json, Writes}

case class Student(id: Int, firstName: String, lastName: String)

