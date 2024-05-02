import Dependencies._

ThisBuild / scalaVersion := "2.13.6"
ThisBuild / version := "0.0.1"
ThisBuild / organization := "com.nlevchenko"
ThisBuild / scalafixDependencies += scalaFix
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

addCommandAlias("build", "prepare; test")
addCommandAlias("prepare", "fix; fmt")
addCommandAlias("check", "fixCheck; fmtCheck")
addCommandAlias("fix", "all compile:scalafix test:scalafix")
addCommandAlias("fixCheck", "compile:scalafix --check; test:scalafix --check")
addCommandAlias("fmt", "all scalafmtSbt scalafmt test:scalafmt")
addCommandAlias("fmtCheck", "all scalafmtSbtCheck scalafmtCheck test:scalafmtCheck")

lazy val dependencies = Seq(
  akkaHttp,
  akkaActor,
  akkaStream,
  slick,
  pureConfig,
  slickPg,
  circeGeneric,
  circeCore,
  akkaHttpCirce,
  slickHikariCP,
  logback,
  scalaLogging,
  scalaTest % Test
)

lazy val root = (project in file("."))
  .settings(
    name := "Pet service akka",
    libraryDependencies ++= dependencies
  )

scalacOptions ++= Seq("-Wunused")