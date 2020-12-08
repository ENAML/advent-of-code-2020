import common.Resources
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class Day07Test {
  private fun getRealInput() =
    Resources.Resource.fromFile("day07.txt").asLines()

  val testInput = """
    light red bags contain 1 bright white bag, 2 muted yellow bags.
    dark orange bags contain 3 bright white bags, 4 muted yellow bags.
    bright white bags contain 1 shiny gold bag.
    muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
    shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
    dark olive bags contain 3 faded blue bags, 4 dotted black bags.
    vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
    faded blue bags contain no other bags.
    dotted black bags contain no other bags.
  """.trimIndent().split("\n")

  @Test
  fun `parses input`() {
    val input1 = "light red bags contain 1 bright white bag, 2 muted yellow bags, 3 purple bags."
    val expected1 = Day07.BagRule(
      color = "light red",
      contains = listOf(
        Day07.BagRule.ContainsRule(color = "bright white", count = 1),
        Day07.BagRule.ContainsRule(color = "muted yellow", count = 2),
        Day07.BagRule.ContainsRule(color = "purple", count = 3),
      )
    )
    assertEquals(expected1, Day07.BagRule.parse(input1))

    val input2 = "bright white bags contain 7 shiny gold bag."
    val expected2 = Day07.BagRule(
      color = "bright white",
      contains = listOf(
        Day07.BagRule.ContainsRule(color = "shiny gold", count = 7),
      )
    )
    assertEquals(expected2, Day07.BagRule.parse(input2))

    val input3 = "bright white bags contain 123 shiny gold bags."
    val expected3 = Day07.BagRule(
      color = "bright white",
      contains = listOf(
        Day07.BagRule.ContainsRule(color = "shiny gold", count = 123),
      )
    )
    assertEquals(expected3, Day07.BagRule.parse(input3))

    val input4 = "faded blue bags contain no other bags."
    val expected4 = Day07.BagRule(
      color = "faded blue",
      contains = listOf()
    )
    assertEquals(expected4, Day07.BagRule.parse(input4))
  }

  @Nested
  inner class Part1 {
    @Test
    fun `solves on test input`() {
      val result = Day07.Part1.solve(testInput)

      assertEquals(4, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day07.Part1.solve(getRealInput())

      assertEquals(103, result)
    }
  }

  @Nested
  inner class Part2 {

    @Test
    fun `solves on test input`() {
      val result = Day07.Part2.solve(testInput)

      assertEquals(32, result)
    }

    @Test
    fun `solves on test input, 2`() {
      val input = """
        shiny gold bags contain 2 dark red bags.
        dark red bags contain 2 dark orange bags.
        dark orange bags contain 2 dark yellow bags.
        dark yellow bags contain 2 dark green bags.
        dark green bags contain 2 dark blue bags.
        dark blue bags contain 2 dark violet bags.
        dark violet bags contain no other bags.
      """.trimIndent().split("\n")

      val result = Day07.Part2.solve(input)

      assertEquals(126, result)
    }

    @Test
    fun `solves on real input`() {
      val result = Day07.Part2.solve(getRealInput())

      assertEquals(1469, result)
    }
  }
}