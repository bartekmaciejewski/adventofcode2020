package day2

import readFile.FileReader

object PasswordValidation extends App {
  def destructurePasswordEntry(elements: (String, String, String)): (Int, Int, Char, String) = {
    val scope = elements._1.split("-")
    (scope.head.toInt, scope.tail.head.toInt, elements._2.charAt(0), elements._3)
  }

  def frequencyValidation(elements: (String, String, String)): Boolean = {
    def countLetterFrequency(word: String, letter: Char): Int = word.count(_ == letter)
    val (min, max, letter, word) = destructurePasswordEntry(elements)
    countLetterFrequency(word, letter) >= min && countLetterFrequency(word, letter) <= max
  }

  def exactlyOneValidation(elements: (String, String, String)): Boolean = {
    def letterMatchesPosition(word: String, letter: Char, position: Int): Boolean = word.charAt(position - 1) == letter
    val (p1, p2, letter, word) = destructurePasswordEntry(elements)
    letterMatchesPosition(word, letter, p1) != letterMatchesPosition(word, letter, p2)
  }

  val filepath = "\\day2\\policy.txt"
  val listOfPasswords = FileReader(filepath)

  def countValidPass(passwords: Seq[String], validation: ((String, String, String)) => Boolean): Int = {
    val validByFrequency: Seq[Boolean] = for {
      splitPass <-
        passwords
          .map(x =>
            x.split(" ").toList match {
              case List(p1, p2, p3) => validation((p1, p2, p3))
              case _ => false
            }
          )
    } yield splitPass
    validByFrequency.count(_ == true)
  }

  println(countValidPass(listOfPasswords, frequencyValidation))
  println(countValidPass(listOfPasswords, exactlyOneValidation))
}
