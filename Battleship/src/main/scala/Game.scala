import player._

import scala.runtime.Nothing$

/**
  * Created by jaideep on 25/05/17.
  */

trait GameState {
  val player: Player
  val score: Int
}

case class Finished(player: Player, score: Int) extends GameState {
  override def toString: String = player.GetName() + " won the game with damage of " + score.toString
}

case class StillGoing(player: Player, score: Int) extends GameState {
  override def toString: String = player.GetName() + " won the game with scorediff " + score.toString
}

case class EqualState(player: Player, score: Int) extends GameState {
  override def toString: String = "Both players at equal score: " + score.toString
}


class Game(player1: Player, player2: Player) {

  private val MAX_DAMAGE = 15
  private val p1 = player1
  private val p2 = player2

  def CurrentState(): (Player, Int) = {

    val p1totdamage = p1.GetShipBoard().GetBoardState().values.map(
      (s) => if (s.GetDamage() != 0) s.GetDamage() else 0).sum

    val p2totdamage = p2.GetShipBoard().GetBoardState().values.map(
      (s) => if (s.GetDamage() != 0) s.GetDamage() else 0).sum

    if (p1totdamage == MAX_DAMAGE || p2totdamage == MAX_DAMAGE) (
      if (p1totdamage > p2totdamage) player2 else player1, p1totdamage min p2totdamage
    )

    else if (p1totdamage > p2totdamage) (player2, p1totdamage-p2totdamage)
    else if (p2totdamage > p1totdamage) (player1, p2totdamage-p1totdamage)
    else (player1, p2totdamage)

  }

}




