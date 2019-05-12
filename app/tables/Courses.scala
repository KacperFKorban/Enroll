package tables

import models.Course
import slick.jdbc.PostgresProfile.api._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import tables.DBConfigs.db

class Courses(tag: Tag) extends Table[Course](tag, "courses") {
  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)
  def name = column[String]("name")

  def * = (id, name) <> (Course.tupled, Course.unapply)
}

object Courses {

  val courses = TableQuery[Courses]

  def all: Future[List[Course]] =
    db.run(courses.to[List].result)

}
