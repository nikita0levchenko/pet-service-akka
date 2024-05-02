import sbt._

object Versions {
  val scalaTest       = "3.2.8"
  val akkaHttp        = "10.2.3"
  val akkaActor       = "2.6.15"
  val akkaStream      = "2.6.15"
  val slick           = "3.3.3"
  val pureConfig      = "0.15.0"
  val slickPg         = "0.19.4"
  val circe           = "0.13.0"
  val akkaHttpCirce   = "1.37.0"
  val logback         = "1.2.3"
  val scalaLogging    = "3.9.4"
  val organizeImports = "0.5.0"
}

object Dependencies {
  lazy val scalaTest     = "org.scalatest"              %% "scalatest"        % Versions.scalaTest
  lazy val akkaHttp      = "com.typesafe.akka"          %% "akka-http"        % Versions.akkaHttp
  lazy val akkaActor     = "com.typesafe.akka"          %% "akka-actor"       % Versions.akkaActor
  lazy val akkaStream    = "com.typesafe.akka"          %% "akka-stream"      % Versions.akkaStream
  lazy val slick         = "com.typesafe.slick"         %% "slick"            % Versions.slick
  lazy val pureConfig    = "com.github.pureconfig"      %% "pureconfig"       % Versions.pureConfig
  lazy val slickPg       = "com.github.tminglei"        %% "slick-pg"         % Versions.slickPg
  lazy val circeGeneric  = "io.circe"                   %% "circe-generic"    % Versions.circe
  lazy val circeCore     = "io.circe"                   %% "circe-core"       % Versions.circe
  lazy val akkaHttpCirce = "de.heikoseeberger"          %% "akka-http-circe"  % Versions.akkaHttpCirce
  lazy val slickHikariCP = "com.typesafe.slick"         %% "slick-hikaricp"   % Versions.slick
  lazy val logback       = "ch.qos.logback"              % "logback-classic"  % Versions.logback
  lazy val scalaLogging  = "com.typesafe.scala-logging" %% "scala-logging"    % Versions.scalaLogging
  lazy val scalaFix      = "com.github.liancheng"       %% "organize-imports" % Versions.organizeImports
}
