package day5

import readFile.InputReader

import java.util

object SeatNumber extends App {
  val filepath = "\\day5\\seat.txt"
  val seats = InputReader(filepath)
  val separator = InputReader.LINE_SEPARATOR

  val seatsSplit: Seq[String] = seats.split(s"$separator").toSeq
  
  println(seatsSplit)
  
  // count row/col seatID
}
