import common.Resources
import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {
  private fun getInput() = Resources.Resource.fromFile("day02_1.txt").asLines()

  @Test
  fun `Part 1 - test 1`() {
    val input = """
      1-3 a: abcde
      1-3 b: cdefg
      2-9 c: ccccccccc
    """.trimIndent().split("\n")

    val result = Day02.Part1.solve(input)

    assertEquals(2, result)
  }

  @Test
  fun `Part 2 - real input`() {
    val input = getInput()

    val result = Day02.Part1.solve(input)

    assertEquals(528, result)
  }

  @Test
  fun `Part 2 - test 1`() {
    val input = """
      1-3 a: abcde
      1-3 b: cdefg
      2-9 c: ccccccccc
    """.trimIndent().split("\n")

    val result = Day02.Part2.solve(input)

    assertEquals(1, result)
  }

  @Test
  fun `Part 1 - real input`() {
    val input = getInput()

    val result = Day02.Part2.solve(input)

    assertEquals(497, result)
  }
}

