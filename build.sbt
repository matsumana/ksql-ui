lazy val akkaHttpVersion = "10.0.10"
lazy val akkaVersion = "2.5.6"

lazy val yarnBuildProd = taskKey[Unit]("yarn build for production")

lazy val root = (project in file(".")).
  settings(
    inThisBuild(List(
      organization    := "info.matsumana",
      scalaVersion    := "2.12.3"
    )),
    name := "tsujun",
    libraryDependencies ++= Seq(
      "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
      "com.typesafe.akka" %% "akka-stream"          % akkaVersion,

      "com.typesafe.akka" %% "akka-http-testkit" % akkaHttpVersion % Test,
      "org.scalatest"     %% "scalatest"         % "3.0.1"         % Test
    ),
    yarnBuildProd := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "yarn build:prod"
      s.log.info("Start yarn build:prod")
      if ((command !) == 0) {
        s.log.success("Finish yarn build:prod")
      }
    }
  )

assembly := (assembly dependsOn yarnBuildProd).value
