package player

import board._
import ship._

import scala.collection.mutable

/**
  * Created by jaideep on 24/05/17.
  */

class Player(n: String) {

  private val name = n
  private val shipboard = new ShipBoard()
  private val attackboard = new AttackBoard()

  def GetName(): String = name

  def PutShip(ship: Ship, x: Int, y: Int): Unit = {
    shipboard.PlaceShipOnBoard(ship,x,y)
  }

  def GetAttackBoard(): AttackBoard = attackboard
  def GetShipBoard(): ShipBoard = shipboard

  def Attack(oaboard: AttackBoard, osboard: ShipBoard, x: Int, y: Int): Boolean = {
    if (oaboard.Check(x,y)) {
      false
    }

    if (osboard.Check(x, y)) {
      osboard.MarkAttack(x, y)
      oaboard.Record(x, y, Hit)
    } else {
      oaboard.Record(x, y, Miss)
    }
    osboard.Check(x, y)
  }

}
