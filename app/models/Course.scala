package models

case class Course(id: Int, name: String)

object Course {
  import play.api.libs.json._

  implicit object CourseFormat extends Format[Course] {
    override def reads(json: JsValue): JsResult[Course] = {
      val id = (json \ "id").as[Int]
      val name = (json \ "name").as[String]
      JsSuccess(Course(id, name))
    }

    override def writes(o: Course): JsValue = Json.obj(
      "id" -> o.id,
      "name" -> o.name
    )
  }

  def tupled = (Course.apply _).tupled
}
