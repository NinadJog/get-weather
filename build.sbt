val scala3Version = "3.0.2"

lazy val root = project
  .in(file("."))
  .settings(
    name := "Get Weather",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.novocode" % "junit-interface" % "0.11" % "test",
      "com.softwaremill.sttp.client3" %% "core" % "3.3.15",
      "com.softwaremill.sttp.client3" %% "circe" % "3.3.15",
      "io.circe" %% "circe-core" % "0.14.1",
      "io.circe" %% "circe-generic" % "0.14.1"
    )
  )
