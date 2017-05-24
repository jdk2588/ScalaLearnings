/**
  * Created by jaideep on 24/05/17.
  */


class Ship(size: Int, char: Char)  {
  private val length = size
  private val breadth = 1
  private val reprchar = char
  private var damagelen = length

  private var orientation = "horizontal"
  private var posX = 0
  private var posY = 0

  override def toString: String = s"PositionX: ${posX}, PositionY: ${posY}, Orientation: ${orientation}, " +
    s"Length: ${length}, Safe: ${damagelen}"

  def GetLength(): Int = length

  //Coordinates is one cell of x and y where the ship could be placed horizontal(right of coordinate) or vertical(
  // down side)

  def SetPosition(y: Int, x: Int, o: String): Unit = {
      posX = x
      posY = y

      require(x<=10 && y<=10, "The X and Y coordinate should be <= 10")
      require(List("Horizontal", "Vertical") contains o, "Orientation can be either Horizontal or Vertical")
      orientation = o
  }

  def GetPosition(): (Int, Int, String) = {
    (posX, posY, orientation)
  }

  def SetDamage() = damagelen -= 1
  def GetDamage() = damagelen

  def GetRepr() = reprchar


}

object Ship {
  def main(args: Array[String]): Unit = {
    //Player 1 Ship config
    val carrier = new Ship(5, 'C')
    carrier.SetPosition(3,4,"Horizontal")

    val cruiser = new Ship(2, 'R')
    cruiser.SetPosition(9,2,"Vertical")

    val battleship = new Ship(4, 'B')
    battleship.SetPosition(5,5,"Vertical")

    val submarine = new Ship(3, 'S')
    submarine.SetPosition(3,8,"Vertical")

    val patrol = new Ship(1, 'P')
    patrol.SetPosition(8,8,"Vertical")

    val board1 = new Board()

    board1.SetAShip(carrier)
    board1.SetAShip(cruiser)
    board1.SetAShip(battleship)
    board1.SetAShip(submarine)
    board1.SetAShip(patrol)

    //Player 1 Ship config
    val carrier2 = new Ship(5, 'C')
    carrier2.SetPosition(2,3,"Horizontal")

    val cruiser2 = new Ship(2, 'R')
    cruiser2.SetPosition(7,7,"Vertical")

    val battleship2 = new Ship(4, 'B')
    battleship2.SetPosition(7,3,"Vertical")

    val submarine2 = new Ship(3, 'S')
    submarine2.SetPosition(3,8,"Horizontal")

    val patrol2 = new Ship(1, 'P')
    patrol2.SetPosition(6,2,"Vertical")

    val board2 = new Board()

    board2.SetAShip(carrier2)
    board2.SetAShip(cruiser2)
    board2.SetAShip(battleship2)
    board2.SetAShip(submarine2)
    board2.SetAShip(patrol2)


    println(board1)
    println(board2)

    board1.Attack(8,8)
    println(board1.GetBoardState())
  }
}