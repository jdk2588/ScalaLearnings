/**
  * Created by jaideep on 06/06/17.
  */

package com.anagrams.spec

import java.nio.charset.CodingErrorAction

import scala.collection.mutable
import com.anagrams.main.GetAnagramsList
import org.scalatest._

import scala.collection.mutable.ListBuffer
import scala.io.{Codec, Source}


abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors


class CheckAnagramsSpec extends UnitSpec {

//  val decoder = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)

//  val source = Source.fromFile("/wordlist.txt")(decoder).getLines
  //    val ans = for (line <- source) yield line.replace("'","").replace("'","")

  "hash value returned for a given string 'crepitus'" should "return 1420208938445" in {
    GetAnagramsList.hashFunc("crepitus") should be ("1420208938445")
  }

  "base test check" should "find anagrams of words given in a given list" in {

    val anagramlist: mutable.HashMap[String, ListBuffer[String]] = GetAnagramsList.groupSameTypeAnagrams(ListBuffer("crepitus", "cuprites", "pictures", "piecrust"))
    anagramlist("1420208938445") shouldEqual ListBuffer("crepitus", "cuprites", "pictures", "piecrust")
  }

  "with no words passed" should "return empty list" in {
    val anagramlist: mutable.HashMap[String, ListBuffer[String]] = GetAnagramsList.groupSameTypeAnagrams(ListBuffer())
    anagramlist shouldEqual mutable.HashMap[String, ListBuffer[String]]()
  }

}
