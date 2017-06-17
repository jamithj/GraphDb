package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

import org.scalatest.FunSuite
import scala.collection.immutable.Set

class GraphTest extends FunSuite {
  val vertices = Set(Vertex(Company("ABC Pvt Ltd")),
    Vertex(Person("paul.perera@test.com","Paul", "Perera")),
    Vertex(Person("peter.hull@test.com","Peter", "Hull")),
    Vertex(Company("BCD Pvt Ltd")),
    Vertex(Person("ben.adler@test.com","Ben", "Adler")))

  val edges = Set(
    Edge((Vertex(Company("ABC Pvt Ltd")), Vertex(Person("paul.perera@test.com","Paul", "Perera"))), FriendOf),
    Edge((Vertex(Person("ben.adler@test.com","Ben", "Adler")), Vertex(Company("BCD Pvt Ltd"))), WorksAt),
    Edge((Vertex(Person("paul.perera@test.com","Paul", "Perera")), Vertex(Person("ben.adler@test.com","Ben", "Adler"))), FriendOf)
  )

  val graph = new Graph(vertices, edges)

  test("should be able to add a node"){
    val newNode = Vertex(Person("nanya.ugbode@test.com", "Nanya", "Ugbode"))
    val newGraph = graph.addVortex(newNode)
    assert(newGraph.vertices.last == newNode)
  }

  test("should be able to add an edge"){
    val newEdge = Edge((Vertex(Person("nanya.ugbode@test.com", "Nanya", "Ugbode")), Vertex(Company("ABC Pvt Ltd"))), WorksAt)
    val newGraph = graph.addEdge(newEdge)
    assert(newGraph.edges.last == newEdge)
  }
}
