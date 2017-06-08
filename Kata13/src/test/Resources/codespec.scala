package com.kata13

/**
  * Created by jaideep on 08/06/17.
  */

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

import scala.io.Source

class ReadCodeLinesSpec extends FlatSpec with Matchers {
  "Test " should "to just check implementation of ReadCode Method" in {
    val codeLines = Source.fromResource("code.scala").getLines

    ReadCodeLines.readLines(codeLines) shouldEqual 14
  }

  "Given an input directory" should "give total number of lines from files" in {
    def getListOfFiles(dir: String): List[File] = {
      val directory = new File(dir)
      if (directory.exists && directory.isDirectory) {
        directory.listFiles.filter(_.isFile).toList
      } else {
        List[File]()
      }
    }

    ReadCodeLines.readFromDir(getListOfFiles("src/test/Resources")) shouldEqual 0
  }

}
