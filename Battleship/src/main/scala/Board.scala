/**
  * Created by jaideep on 24/05/17.
  */
import scala.collection.mutable

class Board {
  protected val board_size = 10

  protected var board = Array.fill(board_size*board_size)('.')

  protected var boardState = mutable.Map.empty[Char, Ship]

  protected def setBoardValue(repr: Char, x: Int, y: Int) = {
    board(y * board_size + x) = repr
  }

  protected def getBoardValue(x: Int, y: Int): Char = {
    board(y * board_size + x)
  }

  def Check(x: Int, y: Int): Boolean = {
    val bVal = getBoardValue(x-1, y-1)
    if (bVal != '.') true else false
  }

  def GetBoardState() = boardState

  override def toString: String = {
    var res = ""
    for (i <- 0 until board_size) {
      for (j <- 0 until board_size) {
        res += getBoardValue(i, j)
      }
      res += "\n"
    }
    res
  }
}

class AttackBoard extends Board {
  def Record(x: Int, y: Int, status: Char): Unit = {
    setBoardValue(status, x-1, y-1)
  }
}

class ShipBoard extends Board {

  def PlaceShipOnBoard(ship: Ship,x: Int,y: Int,orientation: String): Unit = {

    ship.SetPosition(x,y,orientation)

    require ((orientation == "V" && ship.GetLength() + x - 1 < board_size) ||
      (orientation == "H" && ship.GetLength() + y - 1 < board_size),
      s"${ship.GetRepr()} not appropriate for placing the ship")


    for (v <- 0 until ship.GetLength()) {

      require ((orientation == "V" && getBoardValue(x + v - 1, y - 1) == '.') ||
        (orientation == "H" && getBoardValue(x - 1, y + v - 1) == '.'),
      s"${x-1},${y-1} already occupied, cant place this ship with length ${ship.GetLength()}")

      if (orientation == "H") setBoardValue(ship.GetRepr(), x - 1, y + v - 1)
      else if (orientation == "V") setBoardValue(ship.GetRepr(), x + v - 1, y - 1)
    }

      boardState += (ship.GetRepr() -> ship)
  }


  def MarkAttack(x: Int, y: Int): Unit = {
    val bVal = getBoardValue(x-1, y-1)
    boardState(bVal).SetDamage()
  }

}