package com.db.relationship

import akka.actor.{Actor, ActorLogging, ActorPath, ActorRef}
import com.db.relationship.ActorMessages.Initialized
import scala.concurrent.duration._

/**
  * Created by jamit on 18/06/2017.
  */

class VertexActor(vertex: Vertex, graphActorPath: ActorPath) extends Actor with ActorLogging{
  val max_time_to_live = 3600 seconds
  var initializeMe: Option[String] = None

  def initialized: Receive = {
    case Initialized => initializeMe foreach { sender() ! _ }
  }

  def receive: Receive = {
    case Initialized =>
      context.become(initialized, discardOld = true)
      log.info(s"Vertex Actor ${self.path} is initialized")

    case msg => log.error(s"Bad message $msg received to ${self.path} from sender: ", sender())
  }
}

