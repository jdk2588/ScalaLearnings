name := "Kata6"

version := "1.0"

scalaVersion := "2.12.2"

libraryDependencies += "org.scalatest" % "scalatest_2.12" % "3.2.0-SNAP6"

def ifisacomment(codeline: String): Boolean = {
     codeline match {
       case MatchComment(codeline) => {println(codeline); true}
       case _ => false
     }
   }

textUserTyped match {
  case MoviesZipRE( zip) = > getSearchResults( zip)
  case MoviesNearCityStateRE( city, state) = > getSearchResults( city,


val date = """(^\/\*|^\+).*""".r

 "/*This is a good" match {
  case date(year) => s"$year was a good year for PLs."
}
