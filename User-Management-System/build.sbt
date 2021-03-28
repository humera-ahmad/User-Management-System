name := "User-Management-System"

version := "0.1"

scalaVersion := "2.13.5"

coverageEnabled := true

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "3.2.5" % "test",
  "org.mockito" %% "mockito-scala" % "1.16.32" % Test
)