package tables

import models.{Student, Course, StudentToCourse}
import slick.jdbc.PostgresProfile.api._
import tables.Students._
import tables.Courses._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import tables.DBConfigs.db

class StudentsToCourses(tag: Tag) extends Table[StudentToCourse](tag, "studentstocourses") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def studentId = column[Int]("studentid")
  def courseId = column[Int]("courseid")

  def * = (id, studentId, courseId) <> (StudentToCourse.tupled, StudentToCourse.unapply)

  def student = foreignKey("STUDENT_FK", studentId, students)(_.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)
  def course = foreignKey("COURSE_FK", courseId, courses)(_.id, onUpdate=ForeignKeyAction.Cascade, onDelete=ForeignKeyAction.Cascade)
}

object StudentsToCourses {

  val studentsToCourses = TableQuery[StudentsToCourses]

  def all = {
    val allStudentsWithCourses = students
      .join(studentsToCourses).on(_.id === _.studentId)
      .join(courses).on(_._2.courseId === _.id)
      .to[List]
      .result

    val list = for {
      studentResult <- allStudentsWithCourses
    } yield {
      studentResult.map { row =>
        val studentRow = row._1._1
        val courseRow = row._2
        (studentRow, courseRow.name)
      }
    }

    val res = Map()

    db.run(list).map(_.foldLeft(Map[Student, List[String]]()){ (m, s) =>
      val l: List[String] = s._2 :: m.getOrElse(s._1, List())
      m.updated(s._1, l)
    }.toList)
  }

  def forId(id: Int) = {
    val allStudentsWithCourses = students
      .filter(_.id === id)
      .join(studentsToCourses).on(_.id === _.studentId)
      .join(courses).on(_._2.courseId === _.id)
      .to[List]
      .result

    val list = for {
      studentResult <- allStudentsWithCourses
    } yield {
      studentResult.map { row =>
        val studentRow = row._1._1
        val courseRow = row._2
        (studentRow, courseRow.id)
      }
    }

    val res = Map()

    db.run(list).map(_.foldLeft(Map[Student, List[Int]]()){ (m, s) =>
      val l: List[Int] = s._2 :: m.getOrElse(s._1, List())
      m.updated(s._1, l)
    }.toList.head)
  }

  def insert(studentId: Int, courseId: Int) =
    db.run(studentsToCourses.map(x => (x.studentId, x.courseId)) += (studentId, courseId))

  def deleteStudentsCourses(studentId: Int) =
    db.run(studentsToCourses.filter(_.studentId === studentId).delete)
}
