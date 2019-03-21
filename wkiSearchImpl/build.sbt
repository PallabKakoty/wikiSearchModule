name := """wikiSearchImpl"""
organization := "personal"

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.12"

crossScalaVersions := Seq("2.11.12")

libraryDependencies ++= Seq(
  "net.codingwell" %% "scala-guice" % "4.1.0",
  "com.hhandoko" %% "play25-scala-pdf" % "4.0.0",
  "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % Test,
  ws
)
resolvers ++= Seq(
  Resolver.sonatypeRepo("snapshots")
)

resolvers += "scalaz-bintray" at "http://dl.bintray.com/scalaz/releases"

routesGenerator := InjectedRoutesGenerator

lazy val play25 = RootProject(file("../play25"))

lazy val play25Ex = (project in file(".")).enablePlugins(PlayScala).dependsOn(play25)
