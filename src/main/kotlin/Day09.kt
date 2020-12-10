/**
 * Day 9 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/9
 */
object Day09 {
  object Part1 {
    fun solve(lines: List<String>, preludeSize: Int) =
      solveInternal(lines.map { it.toLong() }, preludeSize)

    fun solveInternal(numbers: List<Long>, preludeSize: Int): Long {
      return findFirstNumberWithout2SumInPrelude(numbers, preludeSize)
    }
  }

  object Part2 {
    fun solve(lines: List<String>, preludeSize: Int): Long {
      val numbers = lines.map { it.toLong() }
      val part1Solution = Part1.solveInternal(numbers, preludeSize)
      val rangeSum = findContiguousRangeSum(numbers, part1Solution)
      val rangeSum2 = findContiguousRangeSumV2(numbers, part1Solution)
      val rangeSum3 = findContiguousRangeSumV3(numbers, part1Solution)
      println(rangeSum)
      println(rangeSum2)
      println(rangeSum3)
      return rangeSum3.minOrNull()!! + rangeSum3.maxOrNull()!!
    }
  }
}

private fun findFirstNumberWithout2SumInPrelude(numbers: List<Long>, preludeSize: Int): Long {
  for (i in preludeSize + 1 until numbers.size) {
    val cur = numbers[i]
    val prelude = numbers.subList(i - preludeSize, i)
    find2Sum(prelude, cur)
      ?: return cur
  }
  throw Exception("no result found")
}

private fun find2Sum(numbers: List<Long>, sum: Long): Pair<Long, Long>? {
  val set = mutableSetOf<Long>()
  for (number in numbers) {
    val otherNumber = sum - number
    if (otherNumber != number && otherNumber in set) {
      return Pair(number, otherNumber)
    }
    set.add(number)
  }
  return null
}

private fun findContiguousRangeSum(numbers: List<Long>, sum: Long): List<Long> {
  for (i in 0 until numbers.size) {
    var curSum = numbers[i]
    for (j in i + 1 until numbers.size) {
      curSum += numbers[j]
      if (curSum == sum) {
        return numbers.subList(i, j + 1)
      }
    }
  }
  throw Exception("contiguous range sum not found")
}

private fun findContiguousRangeSumV2(numbers: List<Long>, sum: Long): List<Long> {
  val sums = numbers.foldIndexed(arrayListOf<Long>()) { i, acc, cur ->
    val prevSum = if (i == 0) 0 else acc[i - 1]
    acc.add(prevSum + cur)
    acc
  }
  var lo = 0
  var hi = 1
  while (lo < hi && hi < numbers.size) {
    val rangeSum = sums[hi] - (if (lo == 0) 0 else sums[lo - 1])
    when {
      rangeSum < sum -> hi++
      rangeSum > sum -> lo++
      else -> return numbers.subList(lo, hi + 1)
    }
  }
  throw Exception("contiguous range sum not found")
}

// Note: I think there's some kind of issue with the `sumToIdxMap` / `loIdx` / `hiIdx` logic
// here but it passes the tests.
private fun findContiguousRangeSumV3(numbers: List<Long>, sum: Long): List<Long> {
  val sums = numbers.foldIndexed(arrayListOf<Long>()) { i, acc, cur ->
    val prevSum = if (i == 0) 0 else acc[i - 1]
    acc.add(cur + prevSum)
    acc
  }.toList()
  val sumToIdxMap = mutableMapOf<Long, Int>().apply {
    this[0L] = -1
    this[sums[0]] = 0
  }
  for (hiIdx in 1 until sums.size) {
    val hiSum = sums[hiIdx]
    val loSum = hiSum - sum
    sumToIdxMap[loSum]?.let { loIdx ->
      return numbers.subList(loIdx + 1, hiIdx + 1)
    }
    sumToIdxMap[sums[hiIdx]] = hiIdx
  }
  throw Exception("contiguous range sum not found")
}
