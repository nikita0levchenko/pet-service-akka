import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import com.nlevchenko.sccakkaapp.config.AppConfig
import com.nlevchenko.sccakkaapp.database.DatabaseEngine
import com.nlevchenko.sccakkaapp.pet.{PetRoute, PetrRepository}
import com.nlevchenko.sccakkaapp.uptime.{UptimeRepository, UptimeRoute}
import com.typesafe.scalalogging.LazyLogging

object ServiceRun extends App with LazyLogging {
  implicit val system: ActorSystem = ActorSystem()
  import system.dispatcher
  logger.info("Starting the service...")

  AppConfig.create() match {
    case Right(appConfig) =>
      val dbEngine           = new DatabaseEngine(appConfig.postgres)
      val uptimeRepository   = new UptimeRepository(dbEngine)
      val uptimeRoute        = new UptimeRoute(uptimeRepository)
      val petrRepository     = new PetrRepository(dbEngine)
      val petRoute: PetRoute = new PetRoute(petrRepository)
      val allRoutes          = uptimeRoute.route ~ petRoute.route

      Http().newServerAt(appConfig.api.endpoint, appConfig.api.port).bind(allRoutes)
      logger.info("Server is ready")
    case Left(error)      =>
      logger.error("Failed to start the server", error.toString)
  }
}
