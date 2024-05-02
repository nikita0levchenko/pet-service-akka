package com.nlevchenko.sccakkaapp.database

import com.nlevchenko.sccakkaapp.config.PostgreConfig
import slick.basic.DatabaseConfig
import slick.jdbc.JdbcProfile

class DatabaseEngine(dbConfig: PostgreConfig) extends AutoCloseable {
  private val slickConfig =
    DatabaseConfig.forConfig[JdbcProfile](PostgreConfig.path, PostgreConfig.toTypesafeConfig(dbConfig))
  val profile             = slickConfig.profile
  val database            = slickConfig.db

  override def close(): Unit = database.close()
}
