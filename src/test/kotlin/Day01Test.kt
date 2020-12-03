import common.Resources
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
  @Test
  fun thingsShouldWork() {
    assertEquals(true, true)
  }

  @Test
  fun `Part 1 - test`() {
    val input = listOf(
      "0",
      "5",
      "20",
      "17",
      "2000",
      "2020",
    )
    val result = Day01.Part1.solve(input)

    assertEquals(2000 * 20, result)
  }

  @Test
  fun `Part 1 - real input`() {
    val input = Resources.Resource.fromFile("day01_1.txt").asLines()
    val result = Day01.Part1.solve(input)

    assertEquals(73371, result)
  }

  @Test
  fun `Part 2 - test 1`() {
    val sum = 24L
    val input = listOf(
      "12", "3", "4", "1", "6", "9"
    )

    val result = Day01.Part2.solve(input, sum)

    assertEquals(12 * 3 * 9, result)
  }

  @Test
  fun `Part 2 - test 2`() {
    val sum = 9L
    val input = listOf(
      "1", "2", "3", "4", "5"
    )

    val result = Day01.Part2.solve(input, sum)

    assertEquals(5 * 3 * 1, result)
  }

  @Test
  fun `Part 2 - real input`() {
    val input = Resources.Resource.fromFile("day01_1.txt").asLines()
    val result = Day01.Part2.solve(input)

    assertEquals(127642310, result)
  }
}