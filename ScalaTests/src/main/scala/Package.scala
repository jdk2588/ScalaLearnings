/**
  * Created by jaideep on 03/06/17.
  */
package com
package object decimalbinary {
  trait Number {
    def number: String
    require(number forall {c => Character.isDigit(c) || Seq('A','B','C','D','E','F').contains(c)},
      "Unable to convert number to String")

    override def toString: String = super.toString
  }
  case class Decimal(number: String) extends Number

  case class Binary(number: String) extends Number

  case class Hexadecimal(number: String) extends Number

}
