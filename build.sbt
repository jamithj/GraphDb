name := "GraphDb"

version := "1.0.0"

scalaVersion := "2.11.8"

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  Seq(
    "com.typesafe.akka" %% "akka-actor" % "2.3.4",
    "com.typesafe.akka" %% "akka-kernel" % "2.3.4",
    "com.typesafe.akka" %% "akka-testkit" % "2.3.4" % "test",
    "org.scalatest" % "scalatest_2.11" % "2.2.4" % "test"
  )
}