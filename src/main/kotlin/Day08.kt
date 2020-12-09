/**
 * Day 8 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/8
 */
object Day08 {
  data class Instruction(val type: String, val arg: String) {
    companion object {
      fun parse(input: String): Instruction {
        val splitted = input.split(" ")
        return Instruction(type = splitted[0], arg = splitted[1])
      }
    }
  }

  object Part1 {
    fun solve(lines: List<String>): Int {
      val result = runInstructions(instructions = lines.map { Instruction.parse(it) })
      assert(result.looped)
      return result.acc
    }
  }

  object Part2 {
    // Ugly, brute force solution. Can probably do better with a graph/backtracking/memoizing.
    fun solve(lines: List<String>): Int {
      val instructions = lines.map { Instruction.parse(it) }
      for ((idx, instruction) in instructions.withIndex()) {
        val newType = when (instruction.type) {
          "jmp" -> "nop"
          "nop" -> "jmp"
          else -> instruction.type
        }
        if (newType != instruction.type) {
          val newInstructions = instructions
            .toMutableList()
            .also { it[idx] = instruction.copy(type = newType) }
            .toList()
          val result = runInstructions(newInstructions)
          if (!result.looped) {
            return result.acc
          }
        }
      }
      throw Exception("Not found")
    }
  }

  fun runInstructions(instructions: List<Instruction>): RunResult {
    val seen = mutableSetOf<Int>()
    var acc = 0
    var idx = 0
    while (idx !in seen && idx in instructions.indices) {
      seen.add(idx)
      instructions[idx].also {
        when (it.type) {
          "acc" -> {
            acc += it.arg.toInt()
            idx += 1
          }
          "jmp" -> {
            idx += it.arg.toInt()
          }
          "nop" -> {
            idx += 1
          }
        }
      }
    }
    return RunResult(acc, looped = idx in instructions.indices)
  }

  data class RunResult(val acc: Int, val looped: Boolean)
}
