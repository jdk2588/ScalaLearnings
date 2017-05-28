/**
  * Created by jaideep on 25/05/17.
  */
object Simulation extends App {

    val player1 = new Player("Jaideep")


    for ((_,ship) <- player1.InitShips()) {
      //      Taking inputs from user
      //      println(s"${player1.GetName()}, Enter y, x and [H,V] orientation of ship ${ship.GetRepr()}. Give each input and press enter.")
      //      var y,x,o = scala.io.StdIn.readLine()
      ship.GetRepr() match {
        case Carrier => player1.PutShip(ship,3,4, Horrizontal)
        case Cruiser => player1.PutShip(ship,6,2,Vertical)
        case Battleship => player1.PutShip(ship,5,5,Vertical)
        case Submarine => player1.PutShip(ship,7,6, Horrizontal)
        case Patrol => player1.PutShip(ship,8,8, Vertical)
      }
    }

    val player2 = new Player("Anurag")

    for ((_,ship) <- player2.InitShips()) {
      //      println(s"${player2.GetName()}, Enter y, x and orientation of ship ${ship.GetRepr()}. Give each input and press enter.")
      //      var a,b,o1 = scala.io.StdIn.readLine()
      ship.GetRepr() match {
        case Carrier => player2.PutShip(ship,2,3,Horrizontal)
        case Cruiser => player2.PutShip(ship,7,7,Vertical)
        case Battleship => player2.PutShip(ship,5,3,Vertical)
        case Submarine => player2.PutShip(ship,3,6,Horrizontal)
        case Patrol => player2.PutShip(ship,4,2,Vertical)
      }
    }

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
    /*player1.Attack(p2ab,p2sb,2,3)
    player1.Attack(p2ab,p2sb,2,4)
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
    player1.Attack(p2ab,p2sb,8,7)*/

    println(p1ab)
    println(p1sb)
    println(p2ab)
    println(p2sb)
    game.CurrentState()

}
