import scala.collection.mutable

/**
  * Created by jaideep on 24/05/17.
  */
class Player(n: String) {

  private val name = n
  private val shipboard = new ShipBoard()
  private val attackboard = new AttackBoard()

  private var ownships = Map.empty[CharRepr, Ship]

  def GetName() = name

  def InitShips(): Map[CharRepr, Ship] = {
      ownships += (Carrier -> new Ship(5,Carrier),
      Battleship -> new Ship(4, Battleship),
      Submarine -> new Ship(3,Submarine),
      Cruiser -> new Ship(2,Cruiser),
      Patrol -> new Ship(1,Patrol))

      ownships
  }

  def PutShip(ship: Ship, x: Int, y: Int, o: Orient): Unit = {
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
      oaboard.Record(x, y, Hit)
    } else {
      oaboard.Record(x, y, Miss)
    }
  }

}
