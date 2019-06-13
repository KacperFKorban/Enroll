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
      val student = (json \ "student").as[Student]
      val courseIds = (json \ "courses").as[List[Int]]
      JsSuccess((student, courseIds))
    }

    override def writes(o: (Student, List[Int])): JsValue = Json.obj(
      "student" -> o._1,
      "courses" -> o._2
    )
  }

  implicit object StudentWithCourseNamesFormat extends Format[(Student, List[String])] {
    override def reads(json: JsValue): JsResult[(Student, List[String])] = {
      val student = (json \ "student").as[Student]
      val courseIds = (json \ "courses").as[List[String]]
      JsSuccess((student, courseIds))
    }

    override def writes(o: (Student, List[String])): JsValue = Json.obj(
      "student" -> o._1,
      "courses" -> o._2
    )
  }

  def tupled = (Student.apply _).tupled
}