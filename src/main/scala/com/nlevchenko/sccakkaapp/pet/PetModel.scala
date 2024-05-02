package com.nlevchenko.sccakkaapp.pet

import slick.jdbc.PostgresProfile.api._

object PetModel {


  class PetTable(tag: Tag)
    extends Table[Pet](tag, Some("public"), "Pet") {
    def id = column[Long]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def status = column[String]("status")
    def tags = column[String]("tags")

    override def * =
      (id, name, status, tags) <> (Pet.tupled, Pet.unapply)
  }

  lazy val petTable: TableQuery[PetTable] = TableQuery[PetTable]


}
