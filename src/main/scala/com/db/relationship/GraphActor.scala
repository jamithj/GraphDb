package com.db.relationship

import akka.actor.{Actor, ActorLogging}
import com.db.relationship.ActorMessages._
import scala.collection.mutable
import akka.actor.ActorRef
import scala.concurrent.duration._
import scala.concurrent.Future
import akka.pattern.pipe



/**
  * Created by jamit on 18/06/2017.
  */
class GraphActor(graph: Graph) extends Actor with ActorLogging{
  val nodeActors = mutable.Map.empty[ActorRef, Vertex]
  implicit val dis = context.dispatcher
  var newGraph = graph

  preStart()

  def receive: Receive = {
    case VertexActorCreated(nodeActor, vertex) =>
      context.watch(nodeActor)
      nodeActors += (nodeActor -> vertex)
      log.info("Vertex actor created: ", nodeActor)
      nodeActor ! Initialized

    case AddVertex(vertex) =>
      newGraph = newGraph.addVortex(vertex)
      log.info(s"Vertex $vertex is created by: ", sender())
      self ! VertexActorCreated(self, vertex)

    case AddRelationship(actorRef, edge) =>
      val updatedGraph = newGraph.addEdge(edge)
      newGraph = updatedGraph
      log.info(s"Relationship $edge is created by: ", sender())

    case UpdateRelationship(actorRef, oldRelationship, newRelationship) =>
      newGraph = newGraph.removeEdge(oldRelationship).addEdge(newRelationship)
      log.info(s"Updated the relationship $oldRelationship to new relationship $newRelationship: ", sender())

    case GetGraph =>
      Future{sender ! SendGraph(graph)} pipeTo sender()

    case msg => log.error(s"Bad message $msg received to ${self.path} from sender: ", sender())
  }
}
