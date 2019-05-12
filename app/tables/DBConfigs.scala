package tables

import slick.jdbc.PostgresProfile.api._

object DBConfigs {

  val db = Database.forConfig("mydb")

}
