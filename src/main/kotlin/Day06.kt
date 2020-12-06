/**
 * Day 4 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/4
 */
object Day06 {
  object Part1 {
    fun solve(lines: List<String>): Int {
      return lines.splitByEmptyLines().map { countQuestionsWhichAnyoneAnsweredYes(it) }.sum()
    }

    fun countQuestionsWhichAnyoneAnsweredYes(groupAnswers: List<String>): Int {
      return groupAnswers
        .map { it.toCharArray().toSet() }
        .reduce { acc, it -> acc.union(it) }
        .size
    }
  }

  object Part2 {
    fun solve(lines: List<String>): Int {
      return lines.splitByEmptyLines().map { countQuestionsWhichEveryoneAnsweredYes(it) }.sum()
    }

    fun countQuestionsWhichEveryoneAnsweredYes(groupAnswers: List<String>): Int {
      return groupAnswers
        .map { it.toCharArray().toSet() }
        .reduce { acc, it -> acc.intersect(it) }
        .size
    }
  }
}

fun List<String>.splitByEmptyLines(): List<List<String>> {
  check(this.isNotEmpty())
  val lineGroups = mutableListOf<MutableList<String>>(mutableListOf())
  for (line in this) {
    if (line.isEmpty()) {
      lineGroups.add(mutableListOf())
    } else {
      lineGroups.last().add(line)
    }
  }
  return lineGroups.map { it.toList() }.toList()
}
