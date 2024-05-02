package com.nlevchenko.sccakkaapp.uptime

import java.time.LocalDateTime

final case class Uptime(now: LocalDateTime, uptime: Long, status: String)