package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

import scala.collection.immutable.Set

case class Node(entity: Entity) extends Entity
case class Graph(nodes:Set[Node], edges:Set[(Node,Node)]) extends Entity {
  def addVortex(node:Node) = new Graph(nodes + node, edges)
  def addEdge(edge: (Node, Node)) = new Graph(nodes + edge._1 + edge._2, edges + edge)

}
