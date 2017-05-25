/**
  * Created by jaideep on 24/05/17.
  */
import scala.collection.mutable


class Board {

  private val board_size = 10

  private var board = Array.fill(board_size*board_size)('.')

  var boardState = mutable.Map.empty[Char, Ship]


  private def setBoardValue(repr: Char, x: Int, y: Int) = {
      board(y * board_size + x) = repr
  }

  private def getBoardValue(x: Int, y: Int): Char = {
    board(y * board_size + x)
  }


  def PlaceShipOnBoard(ship: Ship,y: Int,x: Int,orientation: String): Unit = {

    ship.SetPosition(y,x,orientation)

    println(ship.GetLength()+x-1)
    require ((orientation == "Vertical" && ship.GetLength() + x - 1 < board_size) ||
      (orientation == "Horizontal" && ship.GetLength() + y - 1 < board_size),
      s"${ship.GetRepr()} not appropriate for placing the ship")


    for (v <- 0 until ship.GetLength()) {

      require ((orientation == "Vertical" && getBoardValue(x + v - 1, y - 1) == '.') ||
        (orientation == "Horizontal" && getBoardValue(x - 1, y + v - 1) == '.'),
      s"${x-1},${y-1} already occupied, cant place this ship with length ${ship.GetLength()}")

      if (orientation == "Horizontal") setBoardValue(ship.GetRepr(), x - 1, y + v - 1)
      else if (orientation == "Vertical") setBoardValue(ship.GetRepr(), x + v - 1, y - 1)
    }

      boardState += (ship.GetRepr() -> ship)
  }

  def Check(x: Int, y: Int): Boolean = {
    val bVal = getBoardValue(x-1, y-1)
    if (bVal != '.') true else false
  }

  def MarkAttack(x: Int, y: Int): Unit = {
    val bVal = getBoardValue(x-1, y-1)
    boardState(bVal).SetDamage()
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
