/**
  * Created by jaideep on 06/06/17.
  */
import Simulation.{player1, player2}
import board.{AttackBoard, Board, Carrier, ShipBoard}
import player.Player
import org.scalatest.{BeforeAndAfter, FlatSpec}
import ship.{Horrizontal, Ship, Vertical}

class BattleshipSpec extends UnitSpec with BeforeAndAfter {

  var player1: Player = null
  var player2: Player = null
  var game: Game = null
  var p1ab: AttackBoard = null
  var p1sb: ShipBoard = null
  var p2ab: AttackBoard = null
  var p2sb: ShipBoard = null

  before {
    player1 = new Player("Player1")
    player2 = new Player("Player2")
    game = new Game(player1, player2)
    p1ab = player1.GetAttackBoard()
    p2ab = player2.GetAttackBoard()
    p1sb = player1.GetShipBoard()
    p2sb = player2.GetShipBoard()
    putshipplayer1()
    putshipplayer2()
 }

  "Carrier Ships should be placed" should "correct for player1 " in {
    //Test for values
    p1sb.BoardValue(1,3) shouldEqual Carrier
    p1sb.BoardValue(1,4) shouldEqual Carrier
    p1sb.BoardValue(1,5) shouldEqual Carrier
    p1sb.BoardValue(1,6) shouldEqual Carrier
    p1sb.BoardValue(1,7) shouldEqual Carrier
  }

  it should "correct for player2 " in {
    //Test for values
    p2sb.BoardValue(2,3) shouldEqual Carrier
    p2sb.BoardValue(2,4) shouldEqual Carrier
    p2sb.BoardValue(2,5) shouldEqual Carrier
    p2sb.BoardValue(2,6) shouldEqual Carrier
    p2sb.BoardValue(2,7) shouldEqual Carrier
  }

  "Attack by player 1" should "be a hit " in {
    player1.Attack(p2ab,p2sb,2,3) shouldEqual true
    player1.Attack(p2ab,p2sb,1,3) shouldEqual false
  }

  "Attack by player 2" should "be a hit " in {
    player2.Attack(p1ab,p1sb,8,8) should be (true)
  }

  "Game state" should "be where player1 is winning " in {
    player1.Attack(p2ab,p2sb,2,3) shouldEqual true
    player1.Attack(p2ab,p2sb,1,3) shouldEqual false
    player2.Attack(p1ab,p1sb,8,8) should be (true)
    player1.Attack(p2ab,p2sb,4,2) shouldEqual true
    player2.Attack(p1ab,p1sb,5,4) should not be  true

    game.CurrentState()
  }

  def putshipplayer1() = {
    player1.PutShip(Ship.carrier(Horrizontal),1,3)
    player1.PutShip(Ship.battle(Vertical),5,5)
    player1.PutShip(Ship.cruiser(Vertical),6,2)
    player1.PutShip(Ship.submarine(Horrizontal),7,6)
    player1.PutShip(Ship.patrol(Vertical),8,8)
  }

  def putshipplayer2() = {
    player2.PutShip(Ship.carrier(Horrizontal),2,3)
    player2.PutShip(Ship.battle(Vertical),5,3)
    player2.PutShip(Ship.cruiser(Vertical),7,7)
    player2.PutShip(Ship.submarine(Horrizontal),3,6)
    player2.PutShip(Ship.patrol(Vertical),4,2)
  }

}
