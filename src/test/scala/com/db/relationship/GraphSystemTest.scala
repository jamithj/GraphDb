package com.db.relationship

import akka.actor.ActorSystem
import akka.testkit.{ImplicitSender, TestKit}
import akka.util.Timeout
import org.scalatest.{BeforeAndAfterAll, MustMatchers, WordSpecLike}

import scala.concurrent.duration._
import concurrent.Await

/**
  * Created by jamit on 18/06/2017.
  */
class GraphSystemTest extends TestKit(ActorSystem("GraphSystem"))
  with ImplicitSender
  with WordSpecLike
  with BeforeAndAfterAll
  with MustMatchers {

  val vertices = Set(Vertex(Business("A123456","ABC Pvt Ltd")),
    Vertex(Person("paul.perera@test.com","Paul", "Perera")),
    Vertex(Person("peter.hull@test.com","Peter", "Hull")),
    Vertex(Business("B123456","BCD Pvt Ltd")),
    Vertex(Person("ben.adler@test.com","Ben", "Adler")))

  val edges = Set(
    Edge((Vertex(Person("paul.perera@test.com","Paul", "Perera")), Vertex(Business("A123456","ABC Pvt Ltd"))), WorksAt),
    Edge((Vertex(Person("ben.adler@test.com","Ben", "Adler")), Vertex(Business("B123456","BCD Pvt Ltd"))), WorksAt),
    Edge((Vertex(Person("paul.perera@test.com","Paul", "Perera")), Vertex(Person("ben.adler@test.com","Ben", "Adler"))), FriendOf),
    Edge((Vertex(Person("peter.hull@test.com","Peter", "Hull")), Vertex(Person("paul.perera@test.com","Paul", "Perera"))), RelativeOf),
    Edge((Vertex(Person("jamith.jayasekara@test.com","Jamith", "Jayasekara")), Vertex(Person("nuwan.vithanage@test.com","Nuwan", "Vithanage"))), RelativeOf)
  )

  val graph = new Graph(vertices, edges)

  //implicit val timeout = Timeout(5 seconds)

  override def afterAll() {
    system.shutdown()
  }

  "System" should {
    "create vertex actors" in {
      val relationshipDbSystem = new RelationshipDbSystem(graph)
      val graphActor = relationshipDbSystem.graphActor
      Thread.sleep(500)
      relationshipDbSystem.initializeNodeActors(graph.vertices)
      Thread.sleep(500)
      val newGraph = relationshipDbSystem.getGraph(graphActor)
      println(newGraph)
    }
  }

}
