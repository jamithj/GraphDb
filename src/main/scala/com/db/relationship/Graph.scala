package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

import scala.collection.immutable.Set
import scala.collection.immutable.Stream.Empty

case class Vertex(entity: Entity) extends Entity
case class Edge(vertices: (Vertex, Vertex), relationship: Relationship) extends Relationship
case class Graph(vertices:Set[Vertex], edges:Set[Edge]) extends Entity{
  def addVortex(node:Vertex) = new Graph(vertices + node, edges)
  def addEdge(edge: Edge) = new Graph(vertices + edge.vertices._1 + edge.vertices._2, edges + edge)
  def search(query: SearchParameter): Set[Edge] = edges.filter(query.filter _)

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
