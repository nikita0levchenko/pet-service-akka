package com.nlevchenko.sccakkaapp.config

import com.typesafe.config.Config
import pureconfig._
import pureconfig.error.ConfigReaderFailures
import pureconfig.generic.ProductHint
import pureconfig.generic.auto._

final case class ApiConfig(endpoint: String, port: Int)
final case class PostgreConfig(profile: String, db: DbConfig)
final case class AppConfig(api: ApiConfig, postgres: PostgreConfig)
final case class DbConfig(
  driver: String,
  url: String,
  user: String,
  password: String,
  connectionPool: String,
  dataSourceClass: String
)

object AppConfig {
  implicit def hint[A]: ProductHint[A] = ProductHint[A](ConfigFieldMapping(CamelCase, CamelCase))

  def create(): Either[ConfigReaderFailures, AppConfig] = ConfigSource.default.load[AppConfig]
}

object PostgreConfig {
  val path                                              = "db"
  def toTypesafeConfig(dbConfig: PostgreConfig): Config = ConfigWriter[PostgreConfig].to(dbConfig).atPath(path)
}
