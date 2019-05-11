package tables

import models.{Student, Course, StudentToCourse}
import slick.driver.PostgresDriver.api._
import tables.Students._
import tables.Courses._

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class StudentsToCourses(tag: Tag) extends Table[StudentToCourse](tag, "studentstocourses") {

  def id = column[Int]("id")
  def studentId = column[Int]("studentid")
  def courseId = column[Int]("courseid")

  def * = (id, studentId, courseId) <> (StudentToCourse.tupled, StudentToCourse.unapply)

  def student = foreignKey("STUDENT_FK", studentId, students)(_.id)
  def course = foreignKey("COURSE_FK", courseId, courses)(_.id)
}

object StudentsToCourses {

  val db = Database.forConfig("mydb")

  val studentsToCourses = TableQuery[StudentsToCourses]

  def all = {
//    val allStudentsWithCourses = students
//      .join(studentsToCourses).on(_.id === _.studentId)
////      .join(courses).on(_._2.courseId === _.id)
//      .to[List]
//      .result

//    val grouped = students
//      .join(studentsToCourses).on(_.id === _.studentId)
//      .join(courses).on(_._2.courseId === _.id)
//      .groupBy(_._1._1.id)
//      .to[List]
//      .result

//    val res = for {
//      studentResult <- allStudentsWithCourses
//    } yield {
//      studentResult.map { row =>
//        val studentRow = row._1._1
//        val courseRow = row._2
//        (studentRow.id, studentRow.firstName, studentRow.lastName, courseRow.name)
//      }
//    }

//    val res = for {
//      studentResult <- allStudentsWithCourses
//    } yield {
//      studentResult.map { row =>
//        val studentRow = row._1
//        val sToCourseRow = row._2
//        (studentRow.id, studentRow.firstName, studentRow.lastName, sToCourseRow._3)
//      }
//    }

//    db.run(res)

    db.run(studentsToCourses.to[List].result)
  }

}
