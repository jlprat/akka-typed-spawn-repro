package io.github.jlprat.akka

import akka.typed.Behavior
import akka.typed.scaladsl.Actor

object Father {

  sealed trait Command

  case class SpawnChildren(numberOfChildren: Int) extends Command

  def behavior: Behavior[Command] = init()

  def init(): Behavior[Command] = Actor.immutable[Command] { (ctx, msg) =>
      msg match {
        case SpawnChildren(numberOfTables) if numberOfTables > 0 =>
          0.until(numberOfTables).map { i =>
            ctx.system.log.info(s"creating child number $i")
            ctx.spawn(Child.initial, s"child$i")
          }
          Actor.same
      }
  }
}
