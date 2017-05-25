import scala.collection.mutable

/**
  * Created by jaideep on 24/05/17.
  */
class Player(n: String) {

  private val name = n
  private val shipboard = new Board()
  private val attackboard = new Board()

  private var opponentsboard = new Board()

  private var ownships = Map.empty[Char, Ship]

  private var previousattacks = mutable.Map.empty[(Int, Int), String]

  def GetName() = name

  def InitShips(): Map[Char, Ship] = {
      ownships += ('C' -> new Ship(5,'C'),
      'B' -> new Ship(4,'B'),
      'S' -> new Ship(3,'S'),
      'R' -> new Ship(2,'R'),
      'P' -> new Ship(1,'P'))

      ownships
  }

  def PutShip(ship: Ship, y: Int, x: Int, o: String): Unit = {
    shipboard.PlaceShipOnBoard(ship,y,x,o)
  }

  def GetOwnShips() = ownships

  def GiveOwnBoard() = attackboard

  def SetOpponentsBoard(oboard: Board) = {
      opponentsboard = oboard
  }

  def Attack(x: Int, y: Int): Unit = {
    if (previousattacks.contains((x,y))) return

    if (opponentsboard.Check(x, y)) {
      opponentsboard.MarkAttack(x, y)
      previousattacks += ((x,y) -> "Hit")
    } else {
      previousattacks += ((x,y) -> "Miss")
    }
  }

  def GetOpponetsPreviousAttacks(player: Player) = player.GetOwnPreviousAttacks()
  def GetOwnPreviousAttacks() = previousattacks
}
