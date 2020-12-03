/**
 * Day 1 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/1
 */
object Day01 {
  /**
   * Part 1
   */
  object Part1 {
    fun solve(lines: List<String>, sum: Long = 2020L): Long {
      val nums = lines.map { it.toLong() }

      val seen = mutableSetOf<Long>()
      for (num in nums) {
        val d = sum - num
        if (seen.contains(d)) {
          return d * num
        }
        seen.add(num)
      }

      throw Exception("Cannot find result")
    }
  }

  /**
   * Part 2
   */
  object Part2 {
    fun solve(lines: List<String>, sum: Long = 2020L, efficient: Boolean = true): Long {
      val nums = lines.map { it.toLong() }

      return when (!efficient) {
        true -> solveBruteForce(nums, sum)
        false -> solveEfficient(nums, sum)
      }
    }

    private fun solveBruteForce(nums: List<Long>, sum: Long): Long {
      for (i in nums.indices) {
        for (j in i + 1 until nums.size) {
          for (k in j + 1 until nums.size) {
            if (nums[i] + nums[j] + nums[k] == sum) {
              return nums[i] * nums[j] * nums[k]
            }
          }
        }
      }
      throw Exception("Cannot find result")
    }

    private fun solveEfficient(nums: List<Long>, sum: Long): Long {
      val numsSorted = nums.sorted()
      for ((i, iVal) in numsSorted.withIndex()) {
        val d = sum - iVal
        val numsSubList = numsSorted.subList(i + 1, nums.size)
        val pairWithSum = findPairWithSum(
          sortedList = numsSubList,
          sum = d,
        )
        if (pairWithSum != null) {
          return iVal * pairWithSum.first * pairWithSum.second
        }
      }

      throw Exception("Cannot find result")
    }

    private fun findPairWithSum(sortedList: List<Long>, sum: Long): Pair<Long, Long>? {
      var i = 0
      var j = sortedList.lastIndex
      while (i < j) {
        val iVal = sortedList[i]
        val jVal = sortedList[j]
        when {
          iVal + jVal > sum -> {
            j--
          }
          iVal + jVal < sum -> {
            i++
          }
          else -> {
            // they are equal
            return Pair(iVal, jVal)
          }
        }
      }
      return null
    }
  }

}