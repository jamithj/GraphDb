package com.db.relationship

/**
  * Created by jamit on 17/06/2017.
  */

import org.scalatest.FunSuite
import scala.collection.immutable.Set

class GraphTest extends FunSuite {
  val node = Company("ABC Pvt Ltd")
  val someNodes = Set(Node(Company("ABC Pvt Ltd")),
    Node(Person("paul.perera@test.com","Paul", "Perera")),
    Node(Person("peter.hull@test.com","Peter", "Hull")),
    Node(Company("BCD Pvt Ltd")),
    Node(Person("ben.adler@test.com","Ben", "Adler")))

  val edges = Set(
    (Node(Company("ABC Pvt Ltd")), Node(Person("paul.perera@test.com","Paul", "Perera"))),
    (Node(Company("BCD Pvt Ltd")), Node(Person("ben.adler@test.com","Ben", "Adler"))),
    (Node(Person("paul.perera@test.com","Paul", "Perera")), Node(Person("ben.adler@test.com","Ben", "Adler")))
  )

  val graph = new Graph(someNodes, edges)

  test("should be able to add a node"){
    val newNode = Node(Person("nanya.ugbode@test.com", "Nanya", "Ugbode"))
    val newGraph = graph.addVortex(newNode)
    assert(newGraph.nodes.last == newNode)
  }

  test("should be able to add an edge"){
    val newEdge = Tuple2(Node(Company("ABC Pvt Ltd")), Node(Person("nanya.ugbode@test.com", "Nanya", "Ugbode")))
    val newGraph = graph.addEdge(newEdge)
    assert(newGraph.edges.last == newEdge)
  }
}
