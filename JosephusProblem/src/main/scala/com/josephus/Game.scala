package com.josephus

/**
  * Created by jaideep on 09/06/17.
  */

trait Game {
  protected val ringStruct: ArrayStorage
  def AliveCount: Int
  def GetNextAlive(position: Int): Person
}