package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

abstract class Entity {
  def id: String
}
case class Person(email: String, firstName: String, lastName: String) extends Entity {
  override def id: String = email
}
case class Business(IBN:String, name: String) extends Entity {
  override def id: String = IBN
}

abstract class Relationship
case object FriendOf extends Relationship
case object RelativeOf extends Relationship
case object WorksAt extends Relationship






