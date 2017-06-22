package com.freqcalc

/**
  * Created by jaideep on 07/06/17.
  */

import java.io.File

import org.scalatest._
import org.scalatest.concurrent.ScalaFutures
import org.scalatest.time.{Seconds, Span}

import scala.concurrent.duration._
import scala.concurrent.Await
import scala.io.Source
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success}

class FreqCalcSpec extends FlatSpec with Matchers with ScalaFutures {

  implicit val defaultPatience =
    PatienceConfig(timeout = Span(20, Seconds), interval = Span(0.1, Seconds))


  "Freqmap method " should "give word frequency count" in {

    val file = Source.fromResource("data.txt")

    val source = file.getLines

    FreqCalc.freqMap(source) shouldBe Map[String,Int]("Apple" -> 3, "Banana" -> 2, "Chickoo" -> 1)
  }

  "Giving a directory path " should "give aggregated word frequency count for all files in that directory" in {

    val allfiles = Utils.ListFilesInDirectory("src/test/resources")
    val sources = allfiles.map(f => Source.fromFile(f.getPath))
    val output = sources.map(s => FreqCalc.freqMap(lines = s.getLines)).foldLeft(Map[String, Int]()) {
      case (mp, each) => each.foldLeft(mp) {
        case (m, (k, v)) => { m + (k -> (v + m.getOrElse(k, 0))) }
      }
    }

    output shouldBe Map[String,Int]("Banana" -> 3, "Chickoo" -> 1, "Oranges" -> 2, "Apple" -> 5, "Pineapple" -> 1)
  }

  "Testing with futures" should "give" in {
    val output = for {
      files <- Utils.FutureFilesList("src/test/resources")
      result <- FreqCalc.processFiles(files)
    } yield {
      result.reduceLeft((r,m) => m.foldLeft(r) {
        case (dict, (k,v)) => dict + (k -> (v + dict.getOrElse(k, 0)))
      })
    }

    whenReady(output) {
      result => result shouldBe Map[String,Int]("Banana" -> 3, "Chickoo" -> 1, "Oranges" -> 2, "Apple" -> 5, "Pineapple" -> 1)
    }
  }


}


