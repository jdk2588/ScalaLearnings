/**
  * Created by jaideep on 06/06/17.
  */
package com.anagrams.main

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

object GetAnagramsList {

  val mapping: Map[Char, BigInt] = Map(
    'a' -> 2, 'b' -> 3, 'c' -> 5, 'd' -> 7,
    'e' -> 11, 'f' -> 13, 'g' -> 17, 'h' -> 19,
    'i' -> 23, 'j' -> 29, 'k' -> 31, 'l' -> 37,
    'm' -> 41, 'n' -> 43, 'o' -> 47, 'p' -> 53,
    'q' -> 59, 'r' -> 61, 's' -> 67, 't' -> 71,
    'u' -> 73, 'v' -> 79, 'w' -> 83, 'x' -> 89,
    'y' -> 97, 'z' -> 101)


  /*def findanagramsinlist(l: ListBuffer[String]): ListBuffer[String] = {
    l.map(s => hashFunc(s))
    hashmap
  }*/

  def groupSameTypeAnagrams(l: ListBuffer[String]): mutable.HashMap[String, ListBuffer[String]] = {
    val hashmap: mutable.HashMap[String, ListBuffer[String]] = mutable.HashMap[String, ListBuffer[String]]()

    for (n <- l) if (hashmap.contains(hashFunc(n))) hashmap(hashFunc(n)) += n
    else hashmap(hashFunc(n)) = ListBuffer(n)
    hashmap
  }

  def hashFunc(str: String): String = {
    val num = str.toCharArray.map((x: Char) => mapping(x.toLower)).reduceLeft((x: BigInt, y: BigInt) => x * y).toString
    num
  }

}