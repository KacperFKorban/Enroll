package models

case class StudentToCourse(id: Int, studentId: Int, courseId: Int)

object StudentToCourse {
  import play.api.libs.json._

  implicit object StudentToCourseFormat extends Format[StudentToCourse] {
    override def reads(json: JsValue): JsResult[StudentToCourse] = {
      val id = (json \ "id").as[Int]
      val studentId = (json \ "studentId").as[Int]
      val courseId = (json \ "courseId").as[Int]
      JsSuccess(StudentToCourse(id, studentId, courseId))
    }

    override def writes(o: StudentToCourse): JsValue = Json.obj(
      "id" -> o.id,
      "studentId" -> o.studentId,
      "courseId" -> o.courseId
    )
  }

  def tupled = (StudentToCourse.apply _).tupled
}