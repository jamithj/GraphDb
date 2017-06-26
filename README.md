GraphDb
=======
RelationShipSystem is an actor based graph database which is supported to add/remove nodes, add and modify relationships.

This database application accept DSL queries find people and relationships.

Design and Architecture
-----------------------
1. Graph data structure has methods to add, remove nodes(vertices) and edges(relationships) and enriched with accepting query
   parameters.
   
2. RelationshipDbSystem is responsible of creating Graph Actor and NodeActors based on the initial graph.

3. GraphActor is responsible performing all the operations on the graph like add/remove nodes, add and modify relationships
   and initializing and notifying node actors accordingly.

Languages and tools used
------------------------
This application has been developed in scala 2.11.8 and akka 2.3.4

Java:

java version "1.8.0_73"

Java(TM) SE Runtime Environment (build 1.8.0_73-b02)

Java HotSpot(TM) 64-Bit Server VM (build 25.73-b02, mixed mode)

sbt 0.13.11

To Test
-------
Run test in sbt prompt
