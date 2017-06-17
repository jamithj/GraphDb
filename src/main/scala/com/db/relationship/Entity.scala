package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

abstract class Entity

  case class Person(email: String, firstName: String, lastName: String) extends Entity
  case class Company(name: String) extends Entity



