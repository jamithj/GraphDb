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
  def search(query: SearchParameter): Set[Edge] = this.edges.filter(query.filter _)

}

case class AllFriendOf(person: Entity) extends SearchParameter {
  override def filter(in: Any) = in match {
    case g: Graph => g.edges.exists(ed => ed.relationship == FriendOf && ed.vertices._1 == person || ed.vertices._2 == person)
    case _ => false
  }
}

case class AllRelativesOf(person: Entity) extends SearchParameter {
  override def filter(in: Any) = in match {
    case g: Graph => g.edges.exists(ed => ed.relationship == RelativeOf && ed.vertices._1 == person || ed.vertices._2 == person)
    case _ => false
  }
}

case class WorksAtCompany(person: Entity) extends SearchParameter {
  override def filter(in: Any) = in match {
    case g: Graph => g.edges.exists(ed => ed.relationship == WorksAt && ed.vertices._1 == person)
    case _ => false
  }
}
