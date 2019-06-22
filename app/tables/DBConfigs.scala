package tables

import models.Course
import slick.jdbc.PostgresProfile.api._
import tables.Courses.courses
import tables.Students.students
import tables.StudentsToCourses.studentsToCourses

object DBConfigs {

  val db = Database.forConfig("mydb")


  def main(args: Array[String]): Unit = {
    val schema = students.schema ++ courses.schema ++ studentsToCourses.schema
    db.run(DBIO.seq(
      schema.create,
      courses += Course(0, "Scala"),
      courses += Course(1, "Erlang"),
      courses += Course(2, "Python"),
      courses += Course(3, "C++"),
      courses += Course(4, "Assembler"),
      courses += Course(5, "Fortran"),
      courses += Course(6, "Ruby"),
      courses += Course(7, "JavaScript"),
    ))
    schema.create.statements.foreach(println(_))
    Thread.sleep(1000)
  }
}
