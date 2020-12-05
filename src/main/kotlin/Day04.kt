import kotlin.collections.Map

/**
 * Day 4 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/4
 */
object Day04 {
  object Part1 {
    fun solve(lines: List<String>): Int {
      val passportMaps = buildPassportMaps(lines)
      return passportMaps.count { validatePassportMapHasRequiredFields(it) }
    }
  }

  object Part2 {
    fun solve(lines: List<String>): Int {
      val passportMaps = buildPassportMaps(lines)
      return passportMaps.count {
        validatePassportMapHasRequiredFields(it) && validatePassportMapFields(it)
      }
    }
  }

  fun buildPassportMaps(lines: List<String>): List<Map<String, String>> {
    return lines.fold(mutableListOf<MutableMap<String, String>>()) { acc, currentLine ->
      if (acc.isEmpty() || currentLine.isEmpty()) {
        acc.add(mutableMapOf())
      }
      if (currentLine.isNotEmpty()) {
        val currentMap = acc.last()
        val pairs = currentLine.split(" ").map { it.split(":") }
        for (pair in pairs) {
          check(pair.size == 2)
          currentMap[pair[0]] = pair[1]
        }
      }
      acc
    }
  }

  val requiredFields = setOf(
    "byr",
    "iyr",
    "eyr",
    "hgt",
    "hcl",
    "ecl",
    "pid",
    // "cid",
  )

  fun validatePassportMapHasRequiredFields(passportMap: Map<String, String>) =
    requiredFields.all { field -> passportMap.containsKey(field) }

  fun validatePassportMapFields(passportMap: Map<String, String>) =
    passportMap.entries.all { entry -> validatePassportField(entry.key, entry.value) }

  fun validatePassportField(name: String, value: String): Boolean {
    return when (name) {
      // (Birth Year) - four digits; at least 1920 and at most 2002.
      "byr" -> {
        val intValue = value.toInt()
        value.length == 4 && (intValue in 1920..2002)
      }
      // (Issue Year) - four digits; at least 2010 and at most 2020.
      "iyr" -> {
        val intValue = value.toInt()
        value.length == 4 && (intValue in 2010..2020)
      }
      // (Expiration Year) - four digits; at least 2020 and at most 2030.
      "eyr" -> {
        val intValue = value.toInt()
        value.length == 4 && (intValue in 2020..2030)
      }
      // (Height) - a number followed by either cm or in:
      // - If cm, the number must be at least 150 and at most 193.
      // - If in, the number must be at least 59 and at most 76.
      "hgt" -> {
        when (val measurement = value.takeLast(2)) {
          "cm", "in" -> {
            val num = value.dropLast(2).toInt()
            when (measurement) {
              "cm" -> num in 150..193
              "in" -> num in 59..76
              else -> false
            }
          }
          else -> false
        }
      }
      // (Hair Color) - a # followed by exactly six characters 0-9 or a-f.
      "hcl" -> {
        when {
          value.first() == '#' -> {
            value.drop(1).matches("^[a-f0-9]*$".toRegex())
          }
          else -> false
        }
      }
      // (Eye Color) - exactly one of: amb blu brn gry grn hzl oth.
      "ecl" -> {
        value in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
      }
      // (Passport ID) - a nine-digit number, including leading zeroes.
      "pid" -> {
        value.length == 9 && value.matches("^[0-9]*$".toRegex())
      }
      // (Country ID) - ignored, missing or not.
      "cid" -> true
      // else, valid
      else -> true
    }
  }
}