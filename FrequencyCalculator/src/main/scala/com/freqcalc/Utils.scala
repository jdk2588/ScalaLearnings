package com.freqcalc

import java.io.File

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by jaideep on 22/06/17.
  */
object Utils {
  def ListFilesInDirectory(pathname: String): List[File] = {

    //create the fixture
    val directory = new File(pathname)
    if (directory.exists && directory.isDirectory) {
      directory.listFiles.filter(_.isFile).toList
    } else {
      List[File]()
    }
  }

  def FutureFilesList(pathname: String): Future[Seq[File]] =
    Future {
      ListFilesInDirectory(pathname)
    }

}
