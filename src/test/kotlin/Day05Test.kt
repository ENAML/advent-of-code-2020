import common.Resources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day05Test {
  private fun getRealInput() =
    Resources.Resource.fromFile("day05_1.txt").asLines()

  val testSeatStr = "FBFBBFFRLR"

  @Test
  fun `finds row`() {
    assertEquals(44, Day05.findRow(testSeatStr))
  }

  @Test
  fun `finds column`() {
    assertEquals(5, Day05.findCol(testSeatStr))
  }

  @Test
  fun `computes seat id`() {
    assertEquals(357, Day05.computeSeatId(testSeatStr))
    assertEquals(567, Day05.computeSeatId("BFFFBBFRRR"))
    assertEquals(119, Day05.computeSeatId("FFFBBBFRRR"))
    assertEquals(820, Day05.computeSeatId("BBFFBBFRLL"))
  }

  @Nested
  inner class Part1 {
    @Test
    fun `works with real data`() {
      val input = getRealInput()

      assertEquals(947, Day05.Part1.solve(input))
    }
  }

  @Nested
  inner class Part2 {
    @Test
    fun `works with real data`() {
      val input = getRealInput()

      assertEquals(636, Day05.Part2.solve(input))
    }
  }
}