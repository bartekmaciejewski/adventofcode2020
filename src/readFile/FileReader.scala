package readFile

import scala.io.Source

object FileReader {
  val ROOT_PATH: String = System.getProperty("user.dir") + "\\src"

  def apply(filepath: String): Seq[String] = {
    val bufferedSource = Source.fromFile(ROOT_PATH + filepath)
    val lines = (for (line <- bufferedSource.getLines()) yield line).toList
    bufferedSource.close
    lines
  }
}
