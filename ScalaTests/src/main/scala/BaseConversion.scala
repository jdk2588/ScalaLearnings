/**
  * Created by jaideep on 03/06/17.
  */
package com.decimalbinary

import scala.annotation.tailrec

object BaseConversion {


  def decimalToBinary(decimal: Decimal): Binary = Binary(toBinary(BigInt(decimal.number),""))

  @tailrec
  private def toBinary(num: BigInt, acc: String): String = {
    if (num < 2) num + acc
    else toBinary(num/2, (num mod 2).toString + acc)
  }

  def binaryToDecimal(binary: Binary): Decimal = {
    Decimal(binary.number.reverse.zipWithIndex.map{case (s: Char,i: Int) => (math.pow(2,i)*s.toString.toInt).toInt}.sum.toString)
  }

  val hexTable = Array('0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F')

  def decimalToHexaDecimal(decimal: Decimal): Hexadecimal = Hexadecimal(toHexa(BigInt(decimal.number),""))

  @tailrec
  private def toHexa(num: BigInt, acc: String): String = {
    if (num < 16) hexTable((num mod 16).toInt).toString + acc
    else toHexa(num/16, hexTable((num mod 16).toInt).toString + acc)
  }

  def hexadecimalToDecimal(hexa: Hexadecimal): Decimal = {
    Decimal(hexa.number.toCharArray.reverse.zipWithIndex.map{case (s: Char,i: Int) => (math.pow(16,i)*hexTable.indexOf(s)).toInt}.sum.toString)
  }

}
