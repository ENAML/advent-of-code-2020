import common.Resources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day08Test {
  private fun getRealInput() =
    Resources.Resource.fromFile("day08.txt").asLines()

  val testInput = """
    nop +0
    acc +1
    jmp +4
    acc +3
    jmp -3
    acc -99
    acc +1
    jmp -4
    acc +6
  """.trimIndent().split("\n")

  @Nested
  inner class Part1 {
    @Test
    fun `solves on test input`() {
      val result = Day08.Part1.solve(testInput)

      assertEquals(5, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day08.Part1.solve(getRealInput())

      assertEquals(1217, result)
    }
  }

  @Nested
  inner class Part2 {
    @Test
    fun `solves on test input`() {
      val result = Day08.Part2.solve(testInput)

      assertEquals(8, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day08.Part2.solve(getRealInput())

      assertEquals(501, result)
    }
  }
}
