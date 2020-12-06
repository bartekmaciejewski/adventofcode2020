package day5

import readFile.InputReader

import java.util
import scala.collection.generic.SeqForwarder
import scala.collection.{AbstractSeq, LinearSeq, SeqProxy, SeqViewLike, immutable, mutable}

object SeatNumber extends App {
  val filepath = "\\day5\\seat.txt"
  val lines = InputReader(filepath)
  val separator = InputReader.LINE_SEPARATOR

  val seats: Seq[String] = lines.split(s"$separator").toSeq
  def find(`0`: Char, `1`: Char)(code: String): Int = { // reusable currying
    code.map {
      case `0` => 0
      case `1` => 1
    } // maps to binary
//    https://docs.scala-lang.org/tour/multiple-parameter-lists.html
    .foldLeft(0)(_ << 1 | _) // applies two-parameter function to all elements from collection, starting from 0 and
    // accumulating result
    // bitwise operators explained: https://www.tutorialspoint.com/scala/scala_operators.htm
    // 1111 == 1 * 2^3 + 1 * 2^2 + 1 * 2^1 + 1 * 2^0 == 15
  }


  val a = find('F', 'B')("BFBFBBF")
  // 0101001
  println(a)
  val seatsIDs = seats.map(_.splitAt(7)).map(x => find('F', 'B')(x._1) * 8 + find('L', 'R')(x._2))
  println(seatsIDs.sorted)
  println(seatsIDs.max)
  var currentValue = seatsIDs.sorted.head
  var searchedValue = 0
  seatsIDs.sorted.filter(x => {
    if (x == currentValue) {
      currentValue += 1
      true
    } else {
      searchedValue = x - 1
      currentValue += 2
      false
    }
  })
  println(searchedValue)
  
  // row * 8 + column = x
}
