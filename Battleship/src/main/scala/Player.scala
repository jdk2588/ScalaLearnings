import scala.collection.mutable

/**
  * Created by jaideep on 24/05/17.
  */
class Player(n: String) {

  private val name = n
  private val shipboard = new ShipBoard()
  private val attackboard = new AttackBoard()

  private var ownships = Map.empty[Char, Ship]

  def GetName() = name

  def InitShips(): Map[Char, Ship] = {
      ownships += ('C' -> new Ship(5,'C'),
      'B' -> new Ship(4,'B'),
      'S' -> new Ship(3,'S'),
      'R' -> new Ship(2,'R'),
      'P' -> new Ship(1,'P'))

      ownships
  }

  def PutShip(ship: Ship, x: Int, y: Int, o: String): Unit = {
    shipboard.PlaceShipOnBoard(ship,x,y,o)
  }

  def GetOwnShips() = ownships

  def GetAttackBoard() = attackboard
  def GetShipBoard() = shipboard

  def Attack(oaboard: AttackBoard, osboard: ShipBoard, x: Int, y: Int): Unit = {
    if (oaboard.Check(x,y)) {
      println("Already attacked at this point")
      return
    }

    if (osboard.Check(x, y)) {
      osboard.MarkAttack(x, y)
      oaboard.Record(x, y, 'H')
    } else {
      oaboard.Record(x, y, 'M')
    }
  }

}
