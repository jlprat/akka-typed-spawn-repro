package io.github.jlprat.akka

import akka.typed.Behavior
import akka.typed.scaladsl.Actor

object Child {

  sealed trait Action

  def initial: Behavior[Action] = Actor.immutable[Action] { (_, msg) =>
    msg match {
      case _ =>
        Actor.empty
    }
  }

}
