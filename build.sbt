lazy val akkaHttpVersion = "10.0.10"
lazy val akkaVersion = "2.5.6"

lazy val removeNodeModules = taskKey[Unit]("remove node_modules")
lazy val yarnInstall = taskKey[Unit]("yarn install")
lazy val yarnLint = taskKey[Unit]("yarn lint")
lazy val yarnTest = taskKey[Unit]("yarn test")
lazy val yarnBuildProd = taskKey[Unit]("yarn build for production")

lazy val root = (project in file("."))
    .enablePlugins(PlayScala)
    .settings(
    inThisBuild(List(
      organization := "info.matsumana",
      scalaVersion := "2.12.3"
    )),
    name := "ksql-ui",
    version := "0.1.0-SNAPSHOT",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.akka" %% "akka-stream" % akkaVersion,
      "org.scalatest" %% "scalatest" % "3.0.1" % Test
    ),
    removeNodeModules := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "rm -rf node_modules"
      s.log.info("Start remove node_modules")
      if ((command !) == 0) {
        s.log.success("Finish remove node_modules")
      } else {
        throw new IllegalStateException("Fail remove node_modules")
      }
    },
    yarnInstall := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "yarn install"
      s.log.info("Start yarn install")
      if ((command !) == 0) {
        s.log.success("Finish yarn install")
      } else {
        throw new IllegalStateException("Fail yarn install")
      }
    },
    yarnLint := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "yarn lint"
      s.log.info("Start yarn lint")
      if ((command !) == 0) {
        s.log.success("Finish yarn lint")
      } else {
        throw new IllegalStateException("Fail yarn lint")
      }
    },
    yarnTest := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "yarn test"
      s.log.info("Start yarn test")
      if ((command !) == 0) {
        s.log.success("Finish yarn test")
      } else {
        throw new IllegalStateException("Fail yarn test")
      }
    },
    yarnBuildProd := {
      val s: TaskStreams = streams.value
      val shell: Seq[String] = Seq("bash", "-c")
      val command: Seq[String] = shell :+ "yarn build:prod"
      s.log.info("Start yarn build:prod")
      if ((command !) == 0) {
        s.log.success("Finish yarn build:prod")
      } else {
        throw new IllegalStateException("Fail yarn build:prod")
      }
    }
  )

clean := (clean dependsOn yarnInstall).value
yarnInstall := (yarnInstall dependsOn removeNodeModules).value

assembly := (assembly dependsOn yarnBuildProd).value
yarnBuildProd := (yarnBuildProd dependsOn yarnTest).value
yarnTest := (yarnTest dependsOn yarnLint).value
