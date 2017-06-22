package com.josephus

/**
  * Created by jaideep on 10/06/17.
  */

class Person(val identify: Int) {
  override def toString: String = s"${identify.toString} is ${if (alive) "alive" else "dead"}"

  protected var alive = true
  protected var sword = false

  def markDead = alive = false

  def isAlive: Boolean = alive

  def getSword = sword = true

  def giveSword(person: Person) = { sword = false ; person.getSword }

  def hasSword: Boolean = sword

  def killNext(person: Person) = person.markDead

}
