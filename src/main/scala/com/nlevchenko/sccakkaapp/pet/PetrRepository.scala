package com.nlevchenko.sccakkaapp.pet

import com.nlevchenko.sccakkaapp.database.DatabaseEngine

import scala.concurrent.Future

class PetrRepository(dbEngine: DatabaseEngine) {

  import dbEngine.database
  import dbEngine.profile.api._

  def fetchPets(): Future[Seq[Pet]] =
    database.run(PetModel.petTable.result)

  def fetchPet(petId: Long): Future[Seq[Pet]] =
    database.run(PetModel.petTable.filter(_.id === petId).result)

  def insertPet(pet: Pet): Future[Int] =
    database.run(PetModel.petTable += pet)

  def deletePet(petId: Long): Future[Int] =
    database.run(PetModel.petTable.filter(_.id === petId).delete)

  def updatePet(petId: Long, newPet: Pet): Future[Int] =
    database.run(PetModel.petTable.filter(_.id === petId).update(newPet))

}
