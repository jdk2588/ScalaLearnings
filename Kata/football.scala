import scala.collection.mutable.ArrayBuffer

object Football {

    def getData(filename: String)(sanitizefunc: String => Boolean): List[String] = {
        scala.io.Source.fromFile(new java.io.File(filename)).getLines.filter(sanitizefunc).toList.tail
    }

    val lines = getData("football.dat") {
        l => !l.contains("--") 
    }
    var storage = lines.map(l => l.trim.split("\\s+"))

    val teamname = storage.map(y => y(1))
    val scoredfor = storage.scanLeft(0)((x,y) => y(6).toInt).tail
    val scoredagainst = storage.scanLeft(0)((x,y) => y(8).toInt).tail

    val smallscore = (day, temp1, temp2).zipped.map((a, b, c) => (a, Math.abs(b-c))).sortBy(_._2).head
}
