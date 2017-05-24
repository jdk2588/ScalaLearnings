/**
  * Created by jaideep on 24/05/17.
  */
abstract class Player(n: String) {

  val name = n
  val ownboard = new Board()
  val opponentsboard = new Board()

  def SetOwnShips(): Unit = {
//      new Ship(5,"H",'C')
//      new Ship(4,"V",'B')
//      new Ship(3,"H",'S')
//      new Ship(2,"V",'R')
//      new Ship(1,"1",'P')
  }

  def GetOwnShips()


  def OpponentsBoardPosition()
  def OwnBoardPosition()

  def Attack()

  def GetName(): String = name
}
