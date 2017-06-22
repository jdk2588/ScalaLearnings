package com.freqcalc

import java.io.{File, FileInputStream}

import com.freqcalc.FreqCalc.delimiter

import scala.concurrent.{Await, Future}
import scala.io._
import scala.util.Success

/**
  * Created by jaideep on 07/06/17.
  */

import scala.concurrent.duration._
import scala.concurrent.ExecutionContext.Implicits.global



object FreqCalc {

  val delimiter = "\\W+"

  def freqMap(lines: Iterator[String]): Map[String, Int] = {

   /* val mappedWords: Array[(String, Int)] = lines.toArray.flatMap((l: String) => l.split(delimiter).map((word: String) => (word, 1)))

    val frequencies = {
      mappedWords.groupBy((e) => e._1).map { case (key, elements) => elements.reduce((x, y) => (y._1, x._2 + y._2)) }
    }

    frequencies*/

    //val mappedWords: Iterator[Array[String]] = lines.map((l: String) => l.split(delimiter))

    //mappedWords.foreach(x => x.foreach(y => println))

//    val frequencies = lines.flatMap(x => x.split(" ")).toStream.groupBy(x => x).mapValues(x => x.length)


    /* val frequencies = lines.foldLeft(Map[String,Int]()){
      case (mp,line) =>
      line.split(delimiter).foldLeft(mp){
        case (m,word) =>
        m.lift(word).fold(m + (word -> 1))(c => m + (word -> (c+1)))
      }
    }*/

    val frequencies = lines.flatMap(l => l.split(delimiter)).foldLeft(Map.empty[String,Int])({
      (count, word) => { count + (word -> (count.getOrElse(word, 0) + 1))}
    })

    frequencies
}

  def getWordCount(file: File): Future[Map[String, Int]] = {
    Future {
      freqMap(Source.fromFile(file.getPath).getLines)
    }
  }

  def processFiles(files: Seq[File]): Future[Seq[Map[String,Int]]] = {
    Future.sequence(files.map(getWordCount))
  }

}