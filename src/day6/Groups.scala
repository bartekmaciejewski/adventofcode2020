package day6

import readFile.InputReader

import java.util
import scala.collection.generic.SeqForwarder
import scala.collection.{AbstractSeq, LinearSeq, SeqProxy, SeqViewLike, immutable, mutable}

object Groups extends App {
  val filepath = "\\day6\\groups.txt"
  val lines = InputReader(filepath)
  val separator = InputReader.LINE_SEPARATOR

  val groups: Seq[String] = lines.split(s"$separator$separator").toSeq
  println(groups)
  
  // make them one-line for each group, make them set with chars, size, product (#1 puzzle)
}
