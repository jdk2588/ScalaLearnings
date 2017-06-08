package com.kata13

/**
  * Created by jaideep on 08/06/17.

  Hello
  */


object StringUtils {
  implicit class CustomStringImprovements(val c: String) {
    /*hello*/def/*code*/ifisacomment: Boolean = if (c.startsWith("/") || c.startsWith("*")) true else false
  }
}

import StringUtils._

object ReadCodeLines {

  def readLines(codeLines: Iterator[String]): Int = {
    var count = 0
    codeLines.filter((l) => { l.trim.length != 0}).foreach((l) => { if (!l.trim.substring(0,1).ifisacomment) count += 1 })
    count
  }
}
