import common.Coord
import common.Grid

/**
 * Day 3 challenge.
 *
 * Description here: https://adventofcode.com/2020/day/3
 */
object Day03 {

  object Part1 {
    fun solve(lines: List<String>, slope: Coord, init: Coord = Coord(x = 0, y = 0)): Int {
      val map = createMapFromInput(lines)
      var treeCount = 0
      var current = init
      while (map.getTile(current) != null) {
        val tile = map.getTile(current)!!
        treeCount += when (tile) {
          Tile.EMPTY -> 0
          Tile.TREE -> 1
        }
        current += slope
      }
      return treeCount
    }
  }

  object Part2 {
    fun solve(lines: List<String>, slopes: List<Coord>, init: Coord = Coord(x = 0, y = 0)): Long {
      return slopes
        .map { slope -> Part1.solve(lines, slope, init).toLong() }
        .reduce { a, b -> a * b }
    }
  }

  fun createMapFromInput(lines: List<String>): Map {
    return Map(
      grid = Grid.init(width = lines[0].length, height = lines.size) { coord ->
        val c = lines[coord.y][coord.x]
        when (c) {
          '.' -> Tile.EMPTY
          '#' -> Tile.TREE
          else -> throw Exception("Unknown char '$c'")
        }
      })
  }
}

enum class Tile {
  EMPTY,
  TREE,
}

data class Map(val grid: Grid<Tile>) {
  fun getTile(coord: Coord): Tile? {
    if (coord.y !in 0 until grid.height()) {
      return null
    }
    val mapX = coord.x % grid.width()
    return grid.get(Coord(x = mapX, y = coord.y))
  }
}


