package io.github.jlprat.akka

import akka.typed.ActorSystem
import akka.typed.testkit.EffectfulActorContext
import io.github.jlprat.akka.Father.SpawnChildren
import org.scalatest.{FlatSpec, Matchers}

class FatherSpec extends FlatSpec with Matchers {

  "Father init behavior" should "listen to spawn children message" in {
    val system = ActorSystem.create("father-system", Father.init())
    val ctx = new EffectfulActorContext[Father.Command]("father-test", Father.init(), 100, system)

    ctx.run(SpawnChildren(2))
    ctx.getAllEffects().size shouldBe 2

  }

}
