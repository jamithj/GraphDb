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
    Edge((Vertex(Person("paul.perera@test.com","Paul", "Perera")), Vertex(Company("ABC Pvt Ltd"))), WorksAt),
    Edge((Vertex(Person("ben.adler@test.com","Ben", "Adler")), Vertex(Company("BCD Pvt Ltd"))), WorksAt),
    Edge((Vertex(Person("paul.perera@test.com","Paul", "Perera")), Vertex(Person("ben.adler@test.com","Ben", "Adler"))), FriendOf),
    Edge((Vertex(Person("peter.hull@test.com","Peter", "Hull")), Vertex(Person("paul.perera@test.com","Paul", "Perera"))), RelativeOf)
  )

  val graph = new Graph(vertices, edges)

  test("should be able to add a node"){
    val newNode = Vertex(Person("nanya.ugbode@test.com", "Nanya", "Ugbode"))
    val newGraph = graph.addVortex(newNode)
    assert(newGraph.vertices.contains(newNode))
  }

  test("should be able to add an edge"){
    val newEdge = Edge((Vertex(Person("nanya.ugbode@test.com", "Nanya", "Ugbode")), Vertex(Company("ABC Pvt Ltd"))), WorksAt)
    val newGraph = graph.addEdge(newEdge)
    assert(newGraph.edges.contains(newEdge))
  }

  test("should be able to find all friends of a person"){
    val person = Person("paul.perera@test.com","Paul", "Perera")
    val allFriends = graph.search(AllFriendOf(person))
    assert(allFriends.contains(Edge((Vertex(Person("paul.perera@test.com","Paul", "Perera")), Vertex(Person("ben.adler@test.com","Ben", "Adler"))), FriendOf)))
  }

  test("should be able to find all relatives of a person") {
    System.out.println(graph)
    val person = Person("paul.perera@test.com", "Paul", "Perera")
    val allRelatives = graph.search(AllRelativesOf(person))
    assert(allRelatives.contains(Edge((Vertex(Person("peter.hull@test.com","Peter", "Hull")), Vertex(Person("paul.perera@test.com","Paul", "Perera"))), RelativeOf)))
  }

  test("should be able to find the company a person works at") {
    val person = Person("paul.perera@test.com","Paul", "Perera")
    val companyWorkAt = graph.search(WorksAtCompany(person))
    assert(companyWorkAt.contains(Edge((Vertex(Person("paul.perera@test.com","Paul", "Perera")), Vertex(Company("ABC Pvt Ltd"))), WorksAt)))
  }
}
