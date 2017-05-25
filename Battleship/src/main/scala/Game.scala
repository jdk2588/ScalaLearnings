import scala.collection.mutable

/**
  * Created by jaideep on 25/05/17.
  */


class Game(player1: Player, player2: Player) {

  private val MAX_DAMAGE = 15
  private val p1 = player1
  private val p2 = player2

  def CurrentState() = {

    var finished = false
    var p2totdamage = 0
    var p1totdamage = 0

    for ((_,ship) <- p1.GetShipBoard().GetBoardState() if ship.GetDamage() != 0) {
      p1totdamage += ship.GetDamage()
    }

    for ((_,ship) <- p2.GetShipBoard().GetBoardState() if ship.GetDamage() != 0) {
      p2totdamage += ship.GetDamage()
    }

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
