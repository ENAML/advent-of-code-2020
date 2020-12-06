import common.Resources
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.Assertions.assertTrue
//import org.junit.jupiter.api.Assertions.assertFalse
//import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day04Test {
  private fun getRealInput() = Resources.Resource.fromFile("day04_1.txt").asLines()

  val testInput = """
  ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
  byr:1937 iyr:2017 cid:147 hgt:183cm

  iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
  hcl:#cfa07d byr:1929

  hcl:#ae17e1 iyr:2013
  eyr:2024
  ecl:brn pid:760753108 byr:1931
  hgt:179cm

  hcl:#cfa07d eyr:2025 pid:166559648
  iyr:2011 ecl:brn hgt:59in
""".trimIndent().split("\n")

  @Nested
  inner class Part1 {
    @Test
    fun `test 1`() {
      val expectedValidPassports = 2

      val result = Day04.Part1.solve(testInput)

      assertEquals(expectedValidPassports, result)
    }

    @Test
    fun `with real data`() {
      val input = getRealInput()
      val expectedValidPassports = 264

      val result = Day04.Part1.solve(input)

      assertEquals(expectedValidPassports, result)
    }
  }

  @Nested
  inner class Part2 {
    @Test
    fun `passport field validation`() {
      assertTrue(Day04.validatePassportField("byr", "2002"))
      assertFalse(Day04.validatePassportField("byr", "2003"))

      assertTrue(Day04.validatePassportField("hgt", "60in"))
      assertTrue(Day04.validatePassportField("hgt", "190cm"))
      assertFalse(Day04.validatePassportField("hgt", "190in"))
      assertFalse(Day04.validatePassportField("hgt", "190"))

      assertTrue(Day04.validatePassportField("hcl", "#123abc"))
      assertFalse(Day04.validatePassportField("hcl", "#123abz"))
      assertFalse(Day04.validatePassportField("hcl", "123abc"))

      assertTrue(Day04.validatePassportField("ecl", "brn"))
      assertFalse(Day04.validatePassportField("ecl", "wat"))

      assertTrue(Day04.validatePassportField("pid", "000000001"))
      assertFalse(Day04.validatePassportField("pid", "0123456789"))
    }

    @Test
    fun `with all invalid data`() {
      val input = """
      eyr:1972 cid:100
      hcl:#18171d ecl:amb hgt:170 pid:186cm iyr:2018 byr:1926

      iyr:2019
      hcl:#602927 eyr:1967 hgt:170cm
      ecl:grn pid:012533040 byr:1946

      hcl:dab227 iyr:2012
      ecl:brn hgt:182cm pid:021572410 eyr:2020 byr:1992 cid:277

      hgt:59cm ecl:zzz
      eyr:2038 hcl:74454a iyr:2023
      pid:3556412378 byr:2007
    """.trimIndent().split("\n")

      val result = Day04.Part2.solve(input)

      assertEquals(0, result)
    }

    @Test
    fun `with all valid data`() {
      val input = """
      pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
      hcl:#623a2f

      eyr:2029 ecl:blu cid:129 byr:1989
      iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

      hcl:#888785
      hgt:164cm byr:2001 iyr:2015 cid:88
      pid:545766238 ecl:hzl
      eyr:2022

      iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
    """.trimIndent().split("\n")

      val result = Day04.Part2.solve(input)

      assertEquals(4, result)
    }

    @Test
    fun `with real data`() {
      val input = getRealInput()
      val expectedValidPassports = 224

      val result = Day04.Part2.solve(input)

      assertEquals(expectedValidPassports, result)
    }
  }


}