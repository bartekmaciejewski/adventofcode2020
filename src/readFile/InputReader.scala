package readFile

import scala.io.Source

object InputReader {
  val ROOT_PATH: String = System.getProperty("user.dir") + "\\src"
  val LINE_SEPARATOR: String = "\n"

  def apply(path: String): String = Source.fromFile(s"$ROOT_PATH$path").getLines().mkString(LINE_SEPARATOR)
}
