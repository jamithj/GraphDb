package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

import scala.collection.immutable.Set

case class Vertex(entity: Entity)
case class Edge(vertices: (Vertex, Vertex), relationship: Relationship) extends Relationship
case class Graph(vertices:Set[Vertex], edges:Set[Edge]){
  def addVortex(vertex:Vertex) = new Graph(vertices + vertex, edges)
  def removeVortex(vertex:Vertex) = new Graph(vertices - vertex, edges.filter(edge => edge.vertices._1 != vertex && edge.vertices._1 != vertex))
  def addEdge(edge: Edge) = new Graph(vertices + edge.vertices._1 + edge.vertices._2, edges + edge)
  def removeEdge(edge: Edge) = new Graph(vertices.filterNot(isIsolatedVertex _), edges - edge)
  def search(query: SearchParameter): Set[Edge] = edges.filter(query.filter _)
  def isIsolatedVertex(vertex: Vertex): Boolean = !edges.exists(ed => ed.vertices._1 == vertex || ed.vertices._2 == vertex)
  def getBusinesses = edges.filter(_.relationship == WorksAt).groupBy(_.vertices._2)
  def searchBusiness(query: SearchParameter): Seq[Vertex] = getBusinesses.filter(query.filter _).keys.toSeq
}

case class AllFriendOf(person: Entity) extends SearchParameter {
  override def filter(in: Any): Boolean = in match {
    case ed: Edge => ed.relationship == FriendOf && (ed.vertices._1.entity == person || ed.vertices._2.entity == person)
    case _ =>false
  }
}

case class AllRelativesOf(person: Entity) extends SearchParameter {
  override def filter(in: Any): Boolean = in match {
    case ed: Edge => ed.relationship == RelativeOf && ed.vertices._1.entity == person || ed.vertices._2.entity == person
    case _ => false
  }
}

case class WorksAtCompany(person: Entity) extends SearchParameter {
  override def filter(in: Any): Boolean = in match {
    case ed: Edge => ed.relationship == WorksAt && ed.vertices._1.entity == person
    case _ => false
  }
}

case class BusinessWithEmployeesMoreThan(numOfEmployees: Int) extends SearchParameter {
  override def filter(in: Any): Boolean = in match {
    case bum: Map[Vertex, Set[Edge]] => bum.exists(_._2.toList.length > numOfEmployees)
    case _ => false
  }
}
