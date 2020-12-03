/**
 * Day 2 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/2
 */
object Day02 {
  object Part1 {
    fun solve(lines: List<String>): Long {
      var total = 0L

      for (line in lines) {
        val pw = Password.parse(line)
        if (passwordValid(pw)) {
          total += 1
        }
      }

      return total
    }

    private fun passwordValid(password: Password): Boolean {
      val (value, policy) = password
      var count = 0
      for (c in value) {
        if (c == policy.char) {
          count += 1
        }
      }
      return (count in policy.min..policy.max)
    }
  }

  object Part2 {
    fun solve(lines: List<String>): Long {
      var total = 0L

      for (line in lines) {
        val pw = Password.parse(line)
        if (passwordValid(pw)) {
          total += 1
        }
      }

      return total
    }

    private fun passwordValid(password: Password): Boolean {
      val (value, policy) = password
      val match1 = value[policy.min - 1] == policy.char
      val match2 = value[policy.max - 1] == policy.char
      return match1 xor match2
    }
  }
}

private data class Password(
  val value: String, val policy: PasswordPolicy
) {
  companion object {
    fun parse(input: String): Password {
      val splitInput = input.split("-", " ", ": ")
      val min = splitInput[0].toInt()
      val max = splitInput[1].toInt()
      val letter = splitInput[2][0]
      val value = splitInput[3]

      return Password(value, policy = PasswordPolicy(letter, min, max))
    }
  }
}

private data class PasswordPolicy(
  val char: Char, val min: Int, val max: Int
)

