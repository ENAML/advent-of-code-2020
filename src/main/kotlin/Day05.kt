/**
 * Day 5 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/5
 */
import java.lang.Exception

data class SeatPosition(val row: Int, val col: Int)

object Day05 {
  val numRows = 128
  val numCols = 8

  object Part1 {
    fun solve(lines: List<String>): Int {
      return findMaxSeatId(lines)
    }
  }

  object Part2 {
    fun solve(lines: List<String>): Int {
      val seatPositions = lines.map { findPosition(it) }.sortedWith { a, b ->
        computeSeatId(a) - computeSeatId(b)
      }
      for (i in 1 until seatPositions.size) {
        val prevId = computeSeatId(seatPositions[i - 1])
        val curId = computeSeatId(seatPositions[i])
        if (curId != prevId + 1) {
          return prevId + 1
        }
      }
      throw Exception("cannot find empty seat")
    }
  }

  fun findMaxSeatId(seatStrs: List<String>): Int {
    check(seatStrs.isNotEmpty())
    return seatStrs.map { computeSeatId(it) }.maxOrNull()!!
  }

  fun computeSeatId(seatStr: String): Int {
    val position = findPosition(seatStr)
    return computeSeatId(position)
  }

  fun computeSeatId(position: SeatPosition): Int {
    return position.row * 8 + position.col
  }

  fun findPosition(seatStr: String) =
    SeatPosition(row = findRow(seatStr), col = findCol(seatStr))

  fun findRow(seatStr: String): Int {
    var low = 0
    var high = numRows - 1
    for (c in seatStr) {
      val mid = (low + high) / 2
      when (c) {
        // F: take lower half
        'F' -> high = mid
        // B: take upper half
        'B' -> low = mid + 1
      }
      if (low == high)
        return low
    }
    throw Exception("cannot find row")
  }

  fun findCol(seatStr: String): Int {
    var low = 0
    var high = numCols - 1
    for (c in seatStr) {
      val mid = (low + high) / 2
      when (c) {
        // L: take lower half
        'L' -> high = mid
        // R: take upper half
        'R' -> low = mid + 1
      }
      if (low == high)
        return low
    }
    throw Exception("cannot find column")
  }

}