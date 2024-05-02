package com.nlevchenko.sccakkaapp.uptime

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.LazyLogging
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

import java.lang.management.ManagementFactory
import scala.concurrent.ExecutionContext
import scala.util.Failure
import scala.util.Success

class UptimeRoute(uptimeRepository: UptimeRepository)(implicit val executionContext: ExecutionContext)
    extends LazyLogging {

  val route: Route = path("uptime") {
    get {
      logger.info("Received request for uptime")
      onComplete(uptimeRepository.getTimestamp()) {
        case Success(now) =>
          val uptime = ManagementFactory.getRuntimeMXBean.getUptime
          complete(Uptime(now, uptime, "OK"))
        case Failure(e)   =>
          complete(StatusCodes.InternalServerError, e.getMessage)
      }
    }
  }
}
