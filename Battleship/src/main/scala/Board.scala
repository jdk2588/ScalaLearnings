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


  def SetAShip(ship: Ship): Unit = {

    val  l = ship.GetLength()
    var (x, y, orientation) = ship.GetPosition()

    if ((orientation == "Vertical" && l+x-1 > board_size) || (orientation == "Horizontal" && l+y-1 > board_size))  {
      println(s"${ship.GetRepr()} not appropriate for placing the ship")
      return
    }

    for (_ <- 0 to l - 1) {
      if (getBoardValue(x-1, y-1) != '.') {
         println(s"${x-1},${y-1} already occupied, cant place this ship with length ${l}")
         return
      }
      else if (orientation == "Horizontal") {
        setBoardValue(ship.GetRepr(), x - 1, y - 1)
        y += 1
      }
      else if (orientation == "Vertical") {

        setBoardValue(ship.GetRepr(), x - 1, y - 1)
        x += 1
      }
    }

      boardState += (ship.GetRepr() -> ship)
  }

  def Attack(x: Int, y: Int): Unit = {
    val bVal = getBoardValue(x-1, y-1)
    if (bVal != '.') {
      boardState(bVal).SetDamage()
      println("Hit")
    } else {
      println("Miss")
    }
  }

  def GetBoardState() = {
      boardState
  }

  override def toString: String = {
    var res = ""
    for (i <- 0 to board_size-1) {
      for (j <- 0 to board_size-1) {
        res += getBoardValue(i, j)
      }
      res += "\n"
    }
    res
  }

}
