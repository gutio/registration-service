name := """registration-service"""
organization := "jp.gutio"

version := "0.1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.12.3"

libraryDependencies += guice
libraryDependencies += "com.google.api-client" % "google-api-client" % "1.23.0"
libraryDependencies += "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test

libraryDependencies += "com.dripower" %% "play-circe" % "2608.5"

libraryDependencies ++= Seq(
  "com.h2database"  %  "h2"                           % "1.4.196", // your jdbc driver here
  "org.scalikejdbc" %% "scalikejdbc"                  % "3.1.0",
  "org.scalikejdbc" %% "scalikejdbc-config"           % "3.1.0",
  "org.scalikejdbc" %% "scalikejdbc-play-initializer" % "2.6.0-scalikejdbc-3.1"
)

libraryDependencies += "org.flywaydb" %% "flyway-play" % "4.0.0"

// Adds additional packages into Twirl
//TwirlKeys.templateImports += "jp.gutio.controllers._"

// Adds additional packages into conf/routes
// play.sbt.routes.RoutesKeys.routesImport += "jp.gutio.binders._"
