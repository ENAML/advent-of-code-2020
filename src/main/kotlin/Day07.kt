import java.util.Queue
import kotlin.collections.Map

/**
 * Day 7 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/7
 */
object Day07 {
  data class BagRule(val color: String, val contains: List<ContainsRule>) {
    data class ContainsRule(val color: String, val count: Int)

    companion object {
      fun parse(input: String): BagRule {
        val bagRegex = """ (bags?)""".toRegex()
        val periodRegex = """\.""".toRegex()

        val cleanedInput = input.replace(bagRegex, "").replace(periodRegex, "")
        val splitted = cleanedInput.split(" contain ")

        val color = splitted[0]
        val contains = when (val containsStr = splitted[1]) {
          "no other" -> listOf()
          else -> {
            containsStr.split(",")
              .filter { it.isNotEmpty() }
              .map {
                val numRegex = """\d+""".toRegex()
                val count = numRegex.find(it)!!.groupValues[0].toInt()
                val color = it.replace(numRegex, "").trim()
                ContainsRule(color, count)
              }
          }
        }

        return BagRule(color, contains)
      }
    }
  }

  object Part1 {
    fun solve(lines: List<String>): Int {
      return countBagsThatCanHoldBagWithColor(lines, "shiny gold")
    }

    fun createContainedByMapFromInput(lines: List<String>) =
      createContainedByMap(lines.map { BagRule.parse(it) })

    fun createContainedByMap(rules: List<BagRule>): MutableMap<String, MutableSet<String>> {
      val map = mutableMapOf<String, MutableSet<String>>()
      for (rule in rules) {
        for (containsRule in rule.contains) {
          val containedBy = map[containsRule.color] ?: mutableSetOf()
          containedBy.add(rule.color)
          map[containsRule.color] = containedBy
        }
      }
      return map
    }

    fun countBagsThatCanHoldBagWithColor(lines: List<String>, color: String): Int {
      val containedByMap = createContainedByMapFromInput(lines)

      var count = 0
      val visited = mutableSetOf<String>()
      val queue = ArrayDeque<String>()
      queue.add(color)

      while (queue.isNotEmpty()) {
        val cur = queue.removeFirst()
        if (visited.contains(cur)) {
          continue
        }
        visited.add(cur)
        count += 1
        containedByMap[cur]?.forEach {
          if (!visited.contains(it)) {
            queue.add(it)
          }
        }
      }

      return count - 1 // subtract one for the initial color
    }
  }

  object Part2 {
    fun solve(lines: List<String>): Int {
      val rules = lines.map { BagRule.parse(it) }
      return countBagsThatBagMustContain(rules, "shiny gold")
    }

    fun createBagsMap(rules: List<BagRule>): Map<String, BagRule> {
      val builder = mutableMapOf<String, BagRule>()
      for (rule in rules) {
        builder[rule.color] = rule
      }
      return builder.toMap()
    }

    fun countBagsThatBagMustContain(rules: List<BagRule>, color: String): Int {
      val bagsMap = createBagsMap(rules)

      fun recFn(color: String): Int {
        var count = 0
        for (containsRule in bagsMap[color]!!.contains) {
          count += containsRule.count
          count += containsRule.count * recFn(containsRule.color)
        }
        return count
      }

      return recFn(color)
    }
  }
}