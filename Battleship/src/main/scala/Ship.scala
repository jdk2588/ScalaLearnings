/**
  * Created by jaideep on 24/05/17.
  */

class Ship(size: Int, char: Char)  {
  private val length = size
  private val breadth = 1
  private val reprchar = char
  private var damage = 0

  private var orientation = "H"
  private var posX = 0
  private var posY = 0

  override def toString: String = s"PositionX: ${posX}, PositionY: ${posY}, Orientation: ${orientation}, " +
    s"Length: ${length}, Safe: ${damage}"

  def GetLength(): Int = length

  //Coordinates is one cell of x and y where the ship could be placed horizontal(right of coordinate) or vertical(
  // down side)

  def SetPosition(y: Int, x: Int, o: String): Unit = {
      posX = x
      posY = y

      require(x<=10 && y<=10, "The X and Y coordinate should be <= 10")
      require(List("H", "V") contains o, "Orientation can be either H or V")
      orientation = o
  }

  def GetPosition(): (Int, Int, String) = {
    (posX, posY, orientation)
  }

  def SetDamage() = if (damage <= length) damage += 1
  def GetDamage() = damage

  def GetRepr() = reprchar
}