- Entities: Person, Business

- Relations: FriendOf, RelativeOf, WorksAt



- Example Queries:

1. All relatives of Person(X)

2. List the relatives of an every person who works at Business(Y)

3. List all business with more than Z employees

4. List every person who has friends with employed relatives



- Requirements:

1. Solution should take the form of a simple graph database built without the use of existing graph database frameworks

2. Each node should be represented as an actor so that we might spread our nodes across multiple hosts

3. Solution should implement unit tests in the scalatest in the framework and can be executed by running the ‘sbt test’ command.

4. It should be possible to add/remove nodes and change the relations between them at run time.

5. Develop basic DSL to perform queries