package day3

import scala.annotation.tailrec
import readFile.FileReader

import scala.collection.mutable.ArrayBuffer

object Route extends App {
  val filepath = "\\day3\\area.txt"
  val lines = FileReader(filepath)

//  // #1 some idea (how to iterate with index in scala)
//  for ((x, i) <- lines.view.zipWithIndex) println("String #" + i + " is " + x)
//
//  @tailrec
//  def printArray(i: Int, rows: Array[String]) {
//    if (i < rows.length) {
//      println("String #" + i + " is " + rows(i))
//      printArray(i + 1, rows)
//    }
//  }
//  printArray(0, Array("first", "second", "third"))

  // #2
  def isTree(rowNumber: Int, rowContent: String, rightSlop: Int): Boolean = {
    val place = rowNumber * rightSlop + rightSlop
    val calculatedPlace = (place % rowContent.length - 1) + 1
    rowContent.charAt(calculatedPlace) == '#'
  }

  var result = ArrayBuffer[Long]()

  var tempResult = ArrayBuffer[Boolean]()
  for ((x, i) <- lines.tail.view.zipWithIndex) tempResult.append(isTree(i, x, 1))
  result.append(tempResult.count(_ == true))

  tempResult = ArrayBuffer[Boolean]()
  for ((x, i) <- lines.tail.view.zipWithIndex) tempResult.append(isTree(i, x, 3))
  result.append(tempResult.count(_ == true))

  tempResult = ArrayBuffer[Boolean]()
  for ((x, i) <- lines.tail.view.zipWithIndex) tempResult.append(isTree(i, x, 5))
  result.append(tempResult.count(_ == true))

  tempResult = ArrayBuffer[Boolean]()
  for ((x, i) <- lines.tail.view.zipWithIndex) tempResult.append(isTree(i, x, 7))
  result.append(tempResult.count(_ == true))

//  tempResult = ArrayBuffer[Boolean]()
//  for ((x, i) <- lines.tail.view.zipWithIndex) tempResult.append(isTree(i, x, 7))
//  result.append(tempResult.count(_ == true))
  // seemed nice solution but truly what I need is frame to move on chars map (strictly - chars array)

  // #3
  val mapped = FileReader(filepath).toArray.map(_.toCharArray)
  println(singleSlop(mapped))
  println(multiSlop(mapped))

  def singleSlop(map: Array[Array[Char]]): Long = {
    val slops = Seq((1, 3))
    treeCount(map, slops)
  }

  def multiSlop(map: Array[Array[Char]]): Long = {
    val slops = Seq((1, 1), (1, 3), (1, 5), (1, 7), (2, 1))
    treeCount(map, slops)
  }

  def treeCount(map: Array[Array[Char]], slops: Seq[(Int, Int)]): Long =
    slops.map { // maps over all slops
      case (yIncr: Int, xIncr: Int) => // destructures seq to x, y
        var x, y = 0 // initial values for frame
        var count: Long = 0 // tree count
        while (y < map.length) { // till the end of map
          if (map(y)(x) == '#') count = count + 1 // increment tree count if spotted
          x = (x + xIncr) % map.head.length // time to update each frame value (x, y)
          y = y + yIncr
        }
        count // return count as mapped value
    }.product // multiple all counts

}
