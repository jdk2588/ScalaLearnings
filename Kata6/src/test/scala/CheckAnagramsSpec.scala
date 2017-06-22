/**
  * Created by jaideep on 06/06/17.
  */

package com.anagrams.spec

import java.io.File
import java.nio.charset.CodingErrorAction

import scala.collection.mutable
import com.anagrams.main.GetAnagramsList
import org.scalatest._

import scala.collection.mutable.ListBuffer
import scala.io.{Codec, Source}


abstract class UnitSpec extends FlatSpec with Matchers with OptionValues with Inside with Inspectors


class CheckAnagramsSpec extends UnitSpec {

  val decoder = Codec.UTF8.decoder.onMalformedInput(CodingErrorAction.IGNORE)

  def withReadFile(testCode: (Iterator[String]) => Any): Unit = {
    val file = File.createTempFile("Hello", "World")

    //create the fixture
    val source = Source.fromFile("src/test/resources/wordlist.txt")(decoder)

    try {
      val ans: Iterator[String] = for (l <- source.getLines) yield l
      testCode(ans)
    }
    finally source.close()
  }

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

  "given an input string crepitus" should "find all possible anagrams in a list" in {
    val anagramlist: ListBuffer[String] = GetAnagramsList.checkAnagramsonInput("crepitus", ListBuffer("apple", "banana", "pie", "cuprites", "pictures", "piecrust"))
    anagramlist shouldEqual ListBuffer("cuprites", "pictures", "piecrust")
  }

  "Testing with file as loan fixture" should "where each string is passed line by line" in withReadFile {
    (iter) => iter foreach((x: String) => GetAnagramsList.takeOne(x))
      GetAnagramsList.ghashmap("1420208938445") shouldEqual ListBuffer("crepitus", "cuprites", "pictures", "piecrust")
  }


}
