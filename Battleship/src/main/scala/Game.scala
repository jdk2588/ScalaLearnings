/**
  * Created by jaideep on 25/05/17.
  */


class Game(player1: Player, player2: Player) {

  private val MAX_DAMAGE = 15
  private val p1 = player1
  private val p2 = player2

  def CurrentState() = {

    val p1totdamage = p1.GetShipBoard().GetBoardState().values.map(
      (s) => if (s.GetDamage() != 0) s.GetDamage() else 0).sum

    val p2totdamage = p2.GetShipBoard().GetBoardState().values.map(
      (s) => if (s.GetDamage() != 0) s.GetDamage() else 0).sum


    //Winner has lower damage score
    if (p1totdamage == MAX_DAMAGE || p2totdamage == MAX_DAMAGE) {
      if (p1totdamage > p2totdamage) println(s"Game finished, won by ${p2.GetName()}")
      else if (p2totdamage > p1totdamage) println(s"Game finished, won by ${p1.GetName()}")
    } else {
      if (p1totdamage > p2totdamage) println(s"Game is still on, ${p2.GetName()} is winning")
      else if (p2totdamage > p1totdamage) println(s"Game is still on, ${p1.GetName()} is winning")
      else if (p2totdamage == p1totdamage) println(s"Game is still on, both have done equal damage")
    }
  }

}
