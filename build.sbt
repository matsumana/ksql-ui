lazy val akkaHttpVersion = "10.0.10"
lazy val akkaVersion = "2.5.6"

lazy val yarnInstall = taskKey[Unit]("yarn install")
lazy val yarnBuild = taskKey[Unit]("yarn build")
lazy val yarnBuildProd = taskKey[Unit]("yarn build:prod")

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
    yarnInstall := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "yarn install"
      s.log.info("Start yarn install")
      if ((command !) == 0) {
        s.log.success("Finish yarn install")
      }
    },
    yarnBuild := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "yarn build"
      s.log.info("Start yarn build")
      if ((command !) == 0) {
        s.log.success("Finish yarn build")
      }
    },
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

(packageBin in Compile) := (packageBin in Compile).dependsOn(yarnBuildProd).value
