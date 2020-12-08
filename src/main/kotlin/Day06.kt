import common.splitByEmptyLines

/**
 * Day 6 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/6
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

