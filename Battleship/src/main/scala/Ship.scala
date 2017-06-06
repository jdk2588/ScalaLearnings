
/**
  * Created by jaideep on 24/05/17.
  */
package ship

import board._

trait Orient

case object Horrizontal extends Orient {
  override def toString: String = "Horrizontal"
}

case object Vertical extends Orient {
  override def toString: String = "Vertical"
}


abstract class RollingStock {
  val name: String
}

abstract class Ship extends RollingStock {

  require(List(Horrizontal, Vertical) contains orientation, "Orientation can be either Horrizontal or Vertical")

  protected val length: Int
  protected val breadth = 1

  protected var damage: Int = 0

  val reprchar: CharRepr

  val orientation: Orient

  def GetLength(): Int = length

  def SetDamage(): Unit = if (damage <= length) damage += 1
  def GetDamage(): Int = damage

  def GetRepr(): CharRepr = reprchar

  override def toString: String = s"Orientation: $orientation, " + s"Length: $length, Safe: $damage"

}


//Companion object of Ship
object Ship {
  private class CarrierShip(val orientation: Orient) extends Ship {
    val name = "Carrier ship"
    val reprchar: CharRepr = Carrier
    val length = 5

  }

  private class BattleShip(val orientation: Orient) extends Ship {
    val name = "Battle ship"
    val reprchar: CharRepr = Battlechar
    val length = 4
  }

  private class SubmarineShip(val orientation: Orient) extends Ship {
    val name = "Submarine ship"
    val reprchar: CharRepr = Submarine
    val length = 3
  }

  private class CruiserShip(val orientation: Orient) extends Ship {
    val name = "Cruiser ship"
    val reprchar = Cruiser
    val length = 2
  }

  private class PatrolShip(val orientation: Orient) extends Ship {
    val name = "Patrol ship"
    val reprchar = Patrol
    val length = 1
  }


  def carrier(orient: Orient): Ship = new CarrierShip(orient)
  def battle(orient: Orient): Ship = new BattleShip(orient)
  def submarine(orient: Orient): Ship = new SubmarineShip(orient)
  def cruiser(orient: Orient): Ship = new CruiserShip(orient)
  def patrol(orient: Orient): Ship = new PatrolShip(orient)
}
