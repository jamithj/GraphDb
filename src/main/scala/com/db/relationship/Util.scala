package com.db.relationship

import akka.actor.ActorRef

/**
  * Created by jamit on 17/06/2017.
  */
case class AnySearchParameters(a: SearchParameter*) extends SearchParameter {
  override def filter(ar: Any): Boolean = a.foldLeft(false)((acc, item) => acc || item.filter(ar))
}
case class AllSearchParameters(a: SearchParameter*) extends SearchParameter {
  override def filter(ar: Any): Boolean = a.foldLeft(true)((acc, item) => acc && item.filter(ar))
}
case class NotSearchParameter(a: SearchParameter) extends SearchParameter {
  override def filter(ar: Any): Boolean = a.filter(ar)
}

trait SearchParameter {
  def filter(ar: Any): Boolean
  def &&(u: SearchParameter): SearchParameter = AllSearchParameters(this, u)
  def ||(u: SearchParameter): SearchParameter = AnySearchParameters(this, u)
}

object ActorMessages {
  case class VertexActorCreated(actor: ActorRef, vertex: Vertex)
  case class AddVertex(vertex: Vertex)
  case class AddRelationship(actor: ActorRef, edge: Edge)
  case class RemoveRelationship(actor: ActorRef, info: Edge)
  case object Initialized
  case object GetGraph
  case class SendGraph(graph: Graph)
  case class UpdateRelationship(actor:ActorRef, oldRelationship: Edge, newRelationship: Edge)
}
