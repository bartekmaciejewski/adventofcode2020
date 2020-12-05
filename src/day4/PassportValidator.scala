package day4

import readFile.InputReader

import java.util

object PassportValidator extends App {
  val filepath = "\\day4\\passports.txt"
  val passports = InputReader(filepath)
  val separator = InputReader.LINE_SEPARATOR

  val separated: Seq[Seq[(String, String)]] = passports
    .split(s"$separator$separator") // split by blank lines
    .map(
      _.split(separator) // split by EOL
        .flatMap(_.split(" ")) // flatten
        .map(_.split(":") match { // create array
          case Array(field, value) => (field, value) // split to pair key, value for each passport
        })
        .toSeq
    )
    .toSeq

  val requireFields = Set("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
  val optional = "cid"

  def hasRequiredFields(passport: Seq[(String, String)], required: Set[String]): Boolean =
    passport.map(_._1).toSet.intersect(required) == required

  val validSchema = for {
    withRequiredFields <- separated if hasRequiredFields(withRequiredFields, requireFields)
  } yield withRequiredFields

  println(separated) // : Seq[Seq[(String, String)]]
  println(validSchema.size)
}
