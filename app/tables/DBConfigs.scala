package tables

import slick.jdbc.PostgresProfile.api._

object DBConfigs {

  val db = Database.forConfig("mydb")


  def main(args: Array[String]): Unit = {
    val schema = Students.students.schema ++ Courses.courses.schema ++ StudentsToCourses.studentsToCourses.schema
    db.run(schema.create)
    schema.create.statements.foreach(println(_))
    Thread.sleep(1000)
  }
}
