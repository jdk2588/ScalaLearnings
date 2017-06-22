import player._

import scala.runtime.Nothing$

/**
  * Created by jaideep on 25/05/17.
  */

class Game(player1: Player, player2: Player) {

  private val MAX_DAMAGE = 15
  private val p1 = player1
  private val p2 = player2

  def Winner(): Option[Player] = {

    val p1totdamage = p1.TotDamage()

    val p2totdamage = p2.TotDamage()

    if (p1totdamage > p2totdamage) Some(player2)
    else if (p2totdamage > p1totdamage) Some(player1)
    else None
  }

  def CurrentState(): Unit = Winner() match {
    case Some(Player(name)) => println(s"$name is winner")
    case None => println(s"Match draw!")
  }

}





