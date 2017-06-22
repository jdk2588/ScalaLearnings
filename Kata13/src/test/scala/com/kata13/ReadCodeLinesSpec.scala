package com.kata13

/**
  * Created by jaideep on 08/06/17.
  */

import java.io.File

import org.scalatest.{FlatSpec, Matchers}

import scala.io.{BufferedSource, Source}

class ReadCodeLinesSpec extends FlatSpec with Matchers {

  def withReadFile(source: BufferedSource)(testCode: (Iterator[String]) => Any): Unit = {
    try {
      val ans: Iterator[String] = source.getLines
      testCode(ans)
    }
    finally source.close()
  }

  def getListOfFiles(dir: String): List[File] = {
    val directory = new File(dir)
    if (directory.exists && directory.isDirectory) {
      directory.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  "Test " should "to just check implementation of ReadCode Method" in {
    withReadFile(Source.fromResource("code.scala")) {
      (codeLines) => ReadCodeLines.readLines(codeLines) shouldEqual 14
    }
  }

  "Given an input directory" should "give total number of lines of code for that directory" in {
    var s = 0
    getListOfFiles("src/test/Resources").foreach((f: File) => {
      withReadFile(Source.fromFile(f.getPath)) {
        (codeLines) => { s += ReadCodeLines.readLines(codeLines) }
      }
    })

    s shouldEqual 35
   }

}
