package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

import scala.collection.immutable.Set

case class Vertex(entity: Entity) extends Entity
case class Edge(vertices: (Vertex, Vertex), relationship: Relationship) extends Relationship
case class Graph(vertices:Set[Vertex], edges:Set[Edge]) extends Entity {
  def addVortex(node:Vertex) = new Graph(vertices + node, edges)
  def addEdge(edge: Edge) = new Graph(vertices + edge.vertices._1 + edge.vertices._2, edges + edge)

}
