package com.josephus

/**
  * Created by jaideep on 10/06/17.
  */

class SimpleGame(n: Int) extends Game {

  protected val ringStruct: ArrayStorage = ArrayRing(n)

  def AliveCount: Int = ringStruct.GetDataStructure.map((p) => { if (p.isAlive) 1 else 0}).sum

  def GetNextAlive(position: Int): Person = {

    var temp_pos = position+1

    if (position == n-1) temp_pos = 0

    while (!ringStruct.GetDataStructure(temp_pos).isAlive) {
      if (temp_pos == n-1) temp_pos = 0 else temp_pos += 1
    }

    ringStruct.GetDataStructure(temp_pos)
  }

  def GetLastSurvivingPerson: Int = {
    var p = ringStruct.GetDataStructure(0)

    p.getSword

    while (AliveCount > 1) {
      p.killNext(GetNextAlive(p.identify))

      val sword_holder = GetNextAlive(p.identify)
      p.giveSword(sword_holder)
      p = sword_holder
    }
    p.identify + 1
  }

}

class GameWithArray(n: Int, k: Int) {

  protected val ringStruct: ArrayStorage = ArrayRing(n)

  def AliveCount: Int = ringStruct.GetDataStructure.map((p) => { if (p.isAlive) 1 else 0}).sum

  def GetLastSurvivingPerson: Int = {

    var count = 0
    while (AliveCount > 1) {
      for ( i <- 1 to k-1 ) {
        count += 1
      }

      if (count >= AliveCount) count = count % AliveCount

      ringStruct.GetDataStructure.remove(count)
    }

    ringStruct.GetDataStructure(0).identify + 1
  }
}

class GameWithVector(n: Int, k: Int) {

  protected val ringStruct: VectorStorage = VectorRing(n)

  def AliveCount: Int = ringStruct.GetDataStructure.map((p) => { if (p.isAlive) 1 else 0}).sum

  def GetLastSurvivingPerson: Int = {

    var count = 0
    while (AliveCount > 1) {
      for ( i <- 1 until k ) {
        count += 1
      }

      if (count >= AliveCount) count = count % AliveCount

      ringStruct.GetDataStructure.remove(count)
    }

    ringStruct.GetDataStructure(0).identify + 1
  }
}