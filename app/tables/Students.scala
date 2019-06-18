package tables

import models.Student
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{Await, Future}
import scala.concurrent.ExecutionContext.Implicits.global
import tables.DBConfigs.db

import scala.concurrent.duration._

class Students(tag: Tag) extends Table[Student](tag, "students") {
  def id = column[Int]("id", O.PrimaryKey)
  def firstName = column[String]("firstname")
  def lastName = column[String]("lastname")

  def * = (id, firstName, lastName) <> (Student.tupled, Student.unapply)
}

object Students {

  val students = TableQuery[Students]

  def all: Future[List[Student]] =
    db.run(students.to[List].result)

  def forId(id: Int): Future[List[Student]] =
    db.run(students.filter(_.id === id).to[List].result)

  def insert(student: Student) =
    db.run(students += student)

  def delete(student: Student) =
    db.run(students.filter(_.id === student.id).delete)

}