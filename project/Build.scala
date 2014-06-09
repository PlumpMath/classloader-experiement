import sbt._
import sbt.Keys._
import scala._

object BuildSettings {

  import Dependencies._

  lazy val sharedSettings = Defaults.defaultSettings ++ Seq(
    name := "classloader-experiment",
    version := "0.1",
    organization := "com.dbtsai",
    scalaVersion := "2.10.2",
    exportJars := true,
    crossPaths := false,
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    javacOptions ++= Seq("-source", "1.6", "-target", "1.6")
  ) 
}

object ClassloaderExperiment extends Build {

  import BuildSettings._

  lazy val root = Project(
    id = "root",
    base = file("."),
    settings = sharedSettings ++ Seq(
      name := "root"
    ) 
  ) aggregate(calling, called)

  lazy val calling = Project(
    id = "calling", 
    base = file("calling"), 
    settings = sharedSettings ++ Seq(
      name := "calling",
      libraryDependencies ++= Seq("org.apache.spark" %% "spark-assembly" % "0.9.2-hadoop2.0.5-alpha")
    )
  ) dependsOn called

  lazy val called = Project(
    id = "called",
    base = file("called"),
    settings = sharedSettings ++ Seq(
      name := "called"
    )
  )
}

