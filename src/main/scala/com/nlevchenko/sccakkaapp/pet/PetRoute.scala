package com.nlevchenko.sccakkaapp.pet

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route
import com.typesafe.scalalogging.LazyLogging
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

import scala.concurrent.ExecutionContext
import scala.util.Failure
import scala.util.Success

class PetRoute(petrRepository: PetrRepository)(implicit val executionContext: ExecutionContext) extends LazyLogging {

  val route: Route = pathPrefix("pets") { //http://127.0.0.1:8080/pets
    concat(
      pathEnd {
        logger.info("Received request for pets to show")
        onComplete(petrRepository.fetchPets()) {
          case Success(pets) => complete(pets)
          case Failure(e)    =>
            complete(StatusCodes.InternalServerError, e.getMessage)
        }
      },
      pathPrefix("getPet") { //http://127.0.0.1:8080/pets/getPet?id=1
        logger.info("Received request for pet to show")
        parameters("id".as[Long]) { id =>
          onComplete(petrRepository.fetchPet(id)) {
            case Success(pet) => complete(s"Your pet: ${pet.head}")
            case Failure(e)   =>
              complete(StatusCodes.InternalServerError, e.getMessage)
          }
        }
      },
      // http://127.0.0.1:8080/pets/add?id=5&name=newName&status=red&tags=SOMETAG
      pathPrefix("add") { //http://127.0.0.1:8080/pets/add?id=4&name=someName2&status=green2&tags=notags1
        logger.info("Received request for pet to add")
        parameters("id".as[Long], "name", "status", "tags") { (id, name, status, tags) =>
          val petToAdd = Pet(id, name, status, tags)
          onComplete(petrRepository.insertPet(petToAdd)) {
            case Success(_) => complete("Pet added")
            case Failure(e) =>
              complete(StatusCodes.InternalServerError, e.getMessage)
          }
        }
      },
      pathPrefix("delete") { //http://127.0.0.1:8080/pets/delete?id=5
        logger.info("Received request for pet to delete")
        parameters("id".as[Long]) { id =>
          onComplete(petrRepository.deletePet(id)) {
            case Success(_) => complete("Pet deleted")
            case Failure(e) =>
              complete(StatusCodes.InternalServerError, e.getMessage)
          }
        }
      },
      pathPrefix("update") { //http://127.0.0.1:8080/pets/update?id=3&name=NewName&status=NewBlack1&tags=NEWsomeTag2
        logger.info("Received request for pet to update")
        parameters("id".as[Long], "name", "status", "tags") { (id, name, status, tags) =>
          val petToUpdate = Pet(id, name, status, tags)
          onComplete(petrRepository.updatePet(id, petToUpdate)) {
            case Success(_) => complete("Pet updated")
            case Failure(e) =>
              complete(StatusCodes.InternalServerError, e.getMessage)
          }
        }
      }
    )
  }

}
