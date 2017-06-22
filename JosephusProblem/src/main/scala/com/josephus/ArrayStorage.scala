package com.josephus

import scala.collection.mutable.ArrayBuffer

/**
  * Created by jaideep on 10/06/17.
  */

trait Storage

trait ArrayStorage {
  protected var datastructure: ArrayBuffer[Person]
  def GetDataStructure: ArrayBuffer[Person]
}

case class ArrayRing(n: Int) extends Storage with ArrayStorage {
  protected var datastructure = ArrayBuffer[Person]()

  (0 until n).foreach((x) => { datastructure = datastructure :+ new Person(x) })

  override def toString: String = datastructure.toString

  def GetDataStructure: ArrayBuffer[Person] = datastructure

}

trait VectorStorage {
  protected var datastructure: Vector[Person]
  def GetDataStructure: Vector[Person]
}

case class VectorRing(n: Int) extends Storage with VectorStorage {
  protected var datastructure = Vector[Person]()

  (0 until n).foreach((x) => { datastructure = datastructure :+ new Person(x) })

  override def toString: String = datastructure.toString

  def GetDataStructure: Vector[Person] = datastructure

}

