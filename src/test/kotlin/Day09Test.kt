import common.Resources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day09Test {
  private fun getRealInput() =
    Resources.Resource.fromFile("day09.txt").asLines()

  val testInput = """
    35
    20
    15
    25
    47
    40
    62
    55
    65
    95
    102
    117
    150
    182
    127
    219
    299
    277
    309
    576
  """.trimIndent().split("\n")

  val testInputPreludeSize = 5
  val realInputPreludeSize = 25

  @Nested
  inner class Part1 {
    @Test
    fun `solves on test input`() {
      val result = Day09.Part1.solve(testInput, testInputPreludeSize)

      assertEquals(127L, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day09.Part1.solve(getRealInput(), realInputPreludeSize)

      assertEquals(731031916L, result)
    }
  }

  @Nested
  inner class Part2 {
    @Test
    fun `solves on test input`() {
      val result = Day09.Part2.solve(testInput, testInputPreludeSize)

      assertEquals(62L, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day09.Part2.solve(getRealInput(), realInputPreludeSize)

      assertEquals(93396727L, result)
    }
  }
}
