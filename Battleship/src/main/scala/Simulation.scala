/**
  * Created by jaideep on 25/05/17.
  */
import player.Player
import ship.{Ship, Horrizontal, Vertical}

object Simulation extends App {

    val player1 = new Player("Jaideep")

    player1.PutShip(Ship.carrier(Horrizontal),3,4)
    player1.PutShip(Ship.battle(Vertical),5,5)
    player1.PutShip(Ship.cruiser(Vertical),6,2)
    player1.PutShip(Ship.submarine(Horrizontal),7,6)
    player1.PutShip(Ship.patrol(Vertical),8,8)

    val player2 = new Player("Anurag")

    player2.PutShip(Ship.carrier(Horrizontal),2,3)
    player2.PutShip(Ship.battle(Vertical),5,3)
    player2.PutShip(Ship.cruiser(Vertical),7,7)
    player2.PutShip(Ship.submarine(Horrizontal),3,6)
    player2.PutShip(Ship.patrol(Vertical),4,2)

    val game = new Game(player1, player2)

    val p1ab = player1.GetAttackBoard()
    val p1sb = player1.GetShipBoard()

    val p2ab = player2.GetAttackBoard()
    val p2sb = player2.GetShipBoard()

    player1.Attack(p2ab,p2sb,4,2)
    player2.Attack(p1ab,p1sb,4,2)
    player1.Attack(p2ab,p2sb,5,5)
    player2.Attack(p1ab,p1sb,6,3)
    player1.Attack(p2ab,p2sb,1,1)
    player2.Attack(p1ab,p1sb,6,2)
//
//    //Uncomment to see a finished state of game won by Player 1
    player1.Attack(p2ab,p2sb,1,1)
    player1.Attack(p2ab,p2sb,2,3)
  /*  player1.Attack(p2ab,p2sb,2,4)
    player1.Attack(p2ab,p2sb,2,5)
    player1.Attack(p2ab,p2sb,2,6)
    player1.Attack(p2ab,p2sb,2,7)
    player1.Attack(p2ab,p2sb,3,6)
    player1.Attack(p2ab,p2sb,3,7)
    player1.Attack(p2ab,p2sb,3,8)
    player1.Attack(p2ab,p2sb,5,3)
    player1.Attack(p2ab,p2sb,6,3)
    player1.Attack(p2ab,p2sb,7,3)
    player1.Attack(p2ab,p2sb,8,3)
    player1.Attack(p2ab,p2sb,8,7)
    player1.Attack(p2ab,p2sb,7,7)*/

    println(p1ab)
    println(p1sb)
    println(p2ab)
    println(p2sb)
    game.CurrentState()

}
