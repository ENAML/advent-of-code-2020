import common.Resources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day06Test {
  private fun getRealInput() =
    Resources.Resource.fromFile("day06_1.txt").asLines()

  val testInput = """
    abc

    a
    b
    c

    ab
    ac

    a
    a
    a
    a

    b
  """.trimIndent().split("\n")

  @Test
  fun `splits lines correctly`() {
    println()
    val lineGroups = testInput.splitByEmptyLines()

    assertEquals(5, lineGroups.size)
  }

  @Nested
  inner class Part1 {
    @Test
    fun `counts questions which ANYONE answered yes within a group`() {
      val groupAnswers = """
          abcx
          abcy
          abcz
        """.trimIndent().split("\n")

      val result = Day06.Part1.countQuestionsWhichAnyoneAnsweredYes(groupAnswers)

      assertEquals(6, result)
    }

    @Test
    fun `solves on test input`() {
      val expected = 3 + 3 + 3 + 1 + 1 // = 11

      val result = Day06.Part1.solve(testInput)

      assertEquals(expected, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day06.Part1.solve(getRealInput())

      assertEquals(6742, result)
    }
  }

  @Nested
  inner class Part2 {
    @Test
    fun `counts questions which EVERYONE answered yes within a group`() {
      val groupAnswers = """
          abcx
          abcy
          abcz
        """.trimIndent().split("\n")

      val result = Day06.Part2.countQuestionsWhichEveryoneAnsweredYes(groupAnswers)

      assertEquals(/* a, b, c */ 3, result)
    }

    @Test
    fun `solves on test input`() {
      val expected = 3 + 0 + 1 + 1 + 1 // = 6

      val result = Day06.Part2.solve(testInput)

      assertEquals(expected, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day06.Part2.solve(getRealInput())

      assertEquals(3447, result)
    }
  }
}