package models

case class Student(id: Int, firstName: String, lastName: String)

object Student {
  import play.api.libs.json._

  implicit object StudentFormat extends Format[Student] {
    override def reads(json: JsValue): JsResult[Student] = {
      val id = (json \ "id").as[Int]
      val firstName = (json \ "firstName").as[String]
      val lastName = (json \ "lastName").as[String]
      JsSuccess(Student(id, firstName, lastName))
    }

    override def writes(o: Student): JsValue = Json.obj(
      "id" -> o.id,
      "firstName" -> o.firstName,
      "lastName" -> o.lastName
    )
  }

  def tupled = (Student.apply _).tupled
}