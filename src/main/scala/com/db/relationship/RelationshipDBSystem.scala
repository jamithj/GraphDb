package com.db.relationship

import akka.actor.{ActorRef, ActorSystem, Props}
import com.db.relationship.ActorMessages._
import akka.pattern.ask
import scala.concurrent.duration._
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits._

/**
  * Created by jamit on 18/06/2017.
  */
object RelationshipDbSystem extends App

case class RelationshipDbSystem(graph: Graph){
   val relationshipDbSystem = ActorSystem("RelationshipDbSystem")
   val graphActor = relationshipDbSystem.actorOf(Props(new GraphActor(graph)), "graphActor")

    def initializeNodeActors(vertices: Set[Vertex]) = vertices.foreach(vertex => (graphActor ? VertexActorCreated(createNodeActor(vertex), vertex))(10 second))
    def createNodeActor(vertex: Vertex) = relationshipDbSystem.actorOf(Props(new VertexActor(vertex, graphActor.path)), s"vertexActor${vertex.entity.id}")
    def addNode(node: Vertex) = (graphActor ? AddVertex(node))(10 second)
    def getGraph(garphActor: ActorRef): Future[Graph] = (graphActor ? GetGraph)(5 millisecond).mapTo[Graph]
    def graphSearch(query: SearchParameter):Future[Set[Edge]] =
      for (
      graph <- getGraph(graphActor)
      ) yield {
        graph.search(query)
      }
  def businesSearch(query: SearchParameter):Future[Seq[Vertex]] =
    for (
      graph <- getGraph(graphActor)
    ) yield {
      graph.searchBusiness(query)
    }
    def shutdown = relationshipDbSystem.shutdown()
}