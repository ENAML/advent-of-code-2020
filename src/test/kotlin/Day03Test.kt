import common.Coord
import common.Resources
import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {
  private fun getRealInput() = Resources.Resource.fromFile("day03_1.txt").asLines()

  private val testInput1 = """
      ..##.......
      #...#...#..
      .#....#..#.
      ..#.#...#.#
      .#...##..#.
      ..#.##.....
      .#.#.#....#
      .#........#
      #.##...#...
      #...##....#
      .#..#...#.#
    """.trimIndent().split("\n")

  private val part2Slopes = listOf(
    Coord(x = 1, y = 1),
    Coord(x = 3, y = 1),
    Coord(x = 5, y = 1),
    Coord(x = 7, y = 1),
    Coord(x = 1, y = 2),
  )

  @Test
  fun `Part 1 - test 1`() {
    val slope = Coord(x = 3, y = 1)

    val result = Day03.Part1.solve(testInput1, slope)

    assertEquals(7, result)
  }

  @Test
  fun `Part 1 - real input`() {
    val input = getRealInput()
    val slope = Coord(x = 3, y = 1)

    val result = Day03.Part1.solve(input, slope)

    assertEquals(262, result)
  }

  @Test
  fun `Part 2 - test input`() {
    val result = Day03.Part2.solve(testInput1, part2Slopes)

    assertEquals(2L * 7L * 3L * 4L * 2L, result)
  }

  @Test
  fun `Part 2 - real input`() {
    val input = getRealInput()
    val slopes = listOf(
      Coord(x = 1, y = 1),
      Coord(x = 3, y = 1),
      Coord(x = 5, y = 1),
      Coord(x = 7, y = 1),
      Coord(x = 1, y = 2),
    )

    val result = Day03.Part2.solve(input, slopes)

    assertEquals(2698900776L, result)
  }
}
