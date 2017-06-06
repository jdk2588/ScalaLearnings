/**
  * Created by jaideep on 05/06/17.
  */

import org.scalatest._
import matchers._

trait CustomMatchers {
  class FileEndsWithExtensionMatcher(expectedExtension: String) extends Matcher[java.io.File] {
    def apply(left: java.io.File) = {
      val name = left.getName

      MatchResult(
        name.endsWith(expectedExtension),
        s"""File $name did not end with extension $expectedExtension""",
        s"""File $name ended with extension $expectedExtension"""
      )
    }
  }

  def endWithExtension(expectedExtension: String) = new FileEndsWithExtensionMatcher(expectedExtension)
}


