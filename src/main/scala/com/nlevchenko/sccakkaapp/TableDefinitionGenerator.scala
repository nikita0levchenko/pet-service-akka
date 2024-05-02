package com.nlevchenko.sccakkaapp

import com.nlevchenko.sccakkaapp.pet.PetModel._
import slick.jdbc.PostgresProfile.api._


object TableDefinitionGenerator extends App {

  val tables = List(
    petTable
  )


  /*
  AtUnitBuRecord -> (...params)
   */
  val ddl = tables.map(_.schema).reduce(_ ++ _)

  println(ddl.createIfNotExistsStatements.mkString(";\n") + ";")

}
