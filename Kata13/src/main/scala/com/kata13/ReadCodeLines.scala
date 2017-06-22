package com.kata13

/**
  * Created by jaideep on 08/06/17.
  */


object StringUtils {
  implicit class CustomStringImprovements(val codeline: String) {

    private val MatchCommentRE = "(^\\/\\*|^/+|\\*/|\\*).*".r

    def ifisacomment: Boolean = {
      codeline match {
        case MatchCommentRE(codeline) => true
        case _ => false
      }
    }
  }
}


import java.io.File

import StringUtils._

import scala.io.Source

object ReadCodeLines {

  def readLines(codeLines: Iterator[String]): Int = {
    var count = 0
    codeLines.filter((l) => { l.trim.length != 0}).foreach((l) => { if (!l.trim.ifisacomment) count += 1 })
    count
  }

  def readFromDir(dir: List[File]): Int = {
    dir.map((f: File) => readLines(Source.fromFile(f).getLines)).sum
  }
}
