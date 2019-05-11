name := "Enroll"
 
version := "1.0" 
      
lazy val `enroll` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  "com.typesafe.slick" %% "slick" % "3.3.0",
  "org.postgresql" % "postgresql" % "9.4.1212",
  "com.typesafe.slick" %% "slick-hikaricp" % "3.3.0",
  jdbc,
  ehcache,
  ws,
  specs2 % Test,
  guice
)

unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      