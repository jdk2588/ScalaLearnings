package com.josephus

import org.scalatest._

/**
  * Created by jaideep on 09/06/17.
  */


class GameSpec extends FlatSpec with Matchers {

  "With n=10 and k=2" should "give result as 5" in {

    val game = new GameWithArray(1000,6)
    val lastsurvivor = game.GetLastSurvivingPerson
    println(lastsurvivor)
//    lastsurvivor shouldEqual 4
  }

  "The design structure" should "give ring" in {
//    val game = new Game(10)
   // game.GetRing.toString shouldBe Vector(1,2,3,4,5,6,7,8,9,10).toString
//    val lastsurvivor = game.GetLastSurvivingPerson
//    println(lastsurvivor)

    //    lastsurvivor shouldEqual 4
  }
}
