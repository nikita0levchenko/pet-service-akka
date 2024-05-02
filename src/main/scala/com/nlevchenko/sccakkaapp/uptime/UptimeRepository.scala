package com.nlevchenko.sccakkaapp.uptime

import com.nlevchenko.sccakkaapp.database.DatabaseEngine
import slick.jdbc.GetResult

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import scala.concurrent.Future

class UptimeRepository(dbEngine: DatabaseEngine) {

  import dbEngine.database
  import dbEngine.profile.api._

  implicit private val getNow = GetResult(r => parseStringToLocalDateTime(r.nextString()))

  def getTimestamp(): Future[LocalDateTime] =
    database.run(sql"select current_timestamp::timestamp".as[LocalDateTime].head)

  private def parseStringToLocalDateTime(str: String): LocalDateTime = {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSSSSS")
    LocalDateTime.parse(str, formatter)
  }
}