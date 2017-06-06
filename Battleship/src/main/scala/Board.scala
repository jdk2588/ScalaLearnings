/**
  * Created by jaideep on 24/05/17.
  */
package board

import scala.collection.mutable
import ship._

trait CharRepr

case object Carrier extends CharRepr {
  override def toString: String = "C"
}
case object Battlechar extends CharRepr {
  override def toString: String = "B"
}
case object Submarine extends CharRepr {
  override def toString: String = "S"
}
case object Cruiser extends CharRepr {
  override def toString: String = "R"
}
case object Patrol extends CharRepr {
  override def toString: String = "P"
}
case object Dot extends CharRepr {
  override def toString: String = "."
}
case object Hit extends CharRepr {
  override def toString: String = "H"
}
case object Miss extends CharRepr {
  override def toString: String = "M"
}

class Board {
  protected val board_size = 10

  protected var board: Array[CharRepr] = Array.ofDim[CharRepr](board_size*board_size)

  protected val boardState = mutable.Map.empty[CharRepr, Ship]

  protected def setBoardValue(repr: CharRepr, x: Int, y: Int): Unit = {
    board(y * board_size + x) = repr
  }

  (0 until board_size).flatMap(i => (0 until board_size).map((j) => setBoardValue(Dot,i,j)))


  protected def getBoardValue(x: Int, y: Int): CharRepr = board(y * board_size + x)


  protected def formatBoard(x: Int, y: Int): String =
    if (y == board_size-1) getBoardValue(x,y).toString + "\n" else getBoardValue(x,y).toString


  def BoardValue(x: Int, y: Int): CharRepr = getBoardValue(x-1,y-1)

  def Check(x: Int, y: Int): Boolean = {
    if (getBoardValue(x-1, y-1) != Dot) true else false
  }

  def GetBoardState(): mutable.Map[CharRepr, Ship] = boardState

  override def toString: String =
    (0 until board_size).flatMap(i => (0 until board_size).map((j) => formatBoard(i,j))).mkString("")

}

class AttackBoard extends Board {
  def Record(x: Int, y: Int, status: CharRepr): Unit = setBoardValue(status, x-1, y-1)
}

class ShipBoard extends Board {

  def PlaceShipOnBoard(ship: Ship,x: Int,y: Int): Unit = {

    require ((ship.orientation == Vertical && ship.GetLength() + x - 1 < board_size) ||
      (ship.orientation == Horrizontal && ship.GetLength() + y - 1 < board_size),
      s"${ship.GetRepr()} not appropriate for placing the ship")


    for (v <- 0 until ship.GetLength()) {

      require ((ship.orientation == Vertical && getBoardValue(x + v - 1, y - 1) == Dot) ||
        (ship.orientation == Horrizontal && getBoardValue(x - 1, y + v - 1) == Dot),
      s"${x-1},${y-1} already occupied, cant place this ship with length ${ship.GetLength()}")

      if (ship.orientation == Horrizontal) setBoardValue(ship.GetRepr(), x - 1, y + v - 1)
      else if (ship.orientation == Vertical) setBoardValue(ship.GetRepr(), x + v - 1, y - 1)
    }

      boardState += (ship.GetRepr() -> ship)
  }

  def MarkAttack(x: Int, y: Int): Unit = boardState(getBoardValue(x-1, y-1)).SetDamage()

}

