package tables

import models.Student
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global

class Students(tag: Tag) extends Table[Student](tag, "students") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("firstname")
  def lastName = column[String]("lastname")
  def * = (id, firstName, lastName) <> (Student.tupled, Student.unapply)
}

object Students {

  val db = Database.forConfig("mydb")

  val students = TableQuery[Students]

  def all: Future[List[Student]] =
    db.run(students.to[List].result)

}