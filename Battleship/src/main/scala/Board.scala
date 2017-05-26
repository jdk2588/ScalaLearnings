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

  protected def getBoardValue(x: Int, y: Int): Char = board(y * board_size + x)


  protected def formatBoard(x: Int, y: Int): String =
    if (y == board_size-1) getBoardValue(x,y).toString + "\n" else getBoardValue(x,y).toString


  def Check(x: Int, y: Int): Boolean = {
    if (getBoardValue(x-1, y-1) != '.') true else false
  }

  def GetBoardState() = boardState

  override def toString: String =
    (0 until board_size).flatMap(i => (0 until board_size).map((j) => formatBoard(i,j))).mkString("")

}

class AttackBoard extends Board {
  def Record(x: Int, y: Int, status: Char): Unit = setBoardValue(status, x-1, y-1)
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


  def MarkAttack(x: Int, y: Int): Unit = boardState(getBoardValue(x-1, y-1)).SetDamage()


}