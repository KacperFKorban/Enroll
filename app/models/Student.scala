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

  implicit object StudentWithCoursesFormat extends Format[(Student, List[Int])] {
    override def reads(json: JsValue): JsResult[(Student, List[Int])] = {
      val id = (json \ "id").as[Int]
      val firstName = (json \ "name").as[String]
      val lastName = (json \ "surname").as[String]
      val courseIds = (json \ "courses").as[List[Int]]
      JsSuccess((Student(id, firstName, lastName), courseIds))
    }

    override def writes(o: (Student, List[Int])): JsValue = Json.obj(
      "id" -> o._1.id,
      "name" -> o._1.firstName,
      "surname" -> o._1.lastName,
      "courses" -> o._2
    )
  }

  def tupled = (Student.apply _).tupled
}