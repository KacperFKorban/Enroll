package utils

import models.{Course, Student, StudentToCourse}
import play.api.http.{ContentTypeOf, ContentTypes, Writeable}
import play.api.libs.json.{JsValue, Json, Writes}
import play.api.mvc.Codec

trait WritableImplicits {
  implicit def jsonWritable[A](implicit writes: Writes[A], codec: Codec): Writeable[A] = {
    implicit val contentType = ContentTypeOf[A](Some(ContentTypes.JSON))
    val transform = Writeable.writeableOf_JsValue.transform compose writes.writes
    Writeable(transform)
  }

  implicit val CourseWrites = new Writes[Course] {
    override def writes(o: Course): JsValue = Json.obj(
      "name" -> o.name
    )
  }

  implicit val StudentWrites = new Writes[Student] {
    override def writes(o: Student): JsValue = Json.obj(
      "firstName" -> o.firstName,
      "lastName" -> o.lastName
    )
  }

  implicit val StudentToCourseWrites = new Writes[StudentToCourse] {
    override def writes(o: StudentToCourse): JsValue = Json.obj(
      "studentId" -> o.studentId,
      "courseId" -> o.courseId
    )
  }

}

object WritableImplicits extends WritableImplicits