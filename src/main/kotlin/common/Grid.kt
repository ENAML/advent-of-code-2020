package common

data class Grid<T>(
  private val mtx: List<List<T>>
) {
  fun width() = mtx[0].size
  fun height() = mtx.size

  fun has(coord: Coord) = coord.x in 0 until width() && coord.y in 0 until height()

  fun get(coord: Coord): T {
    require(has(coord)) { "Coord out of bounds" }
    return mtx[coord.y][coord.x]
  }

  companion object {
    fun <T> init(width: Int, height: Int, init: (coord: Coord) -> T): Grid<T> {
      val mtx = List(size = height) { y ->
        List(size = width) { x ->
          init(Coord(x, y))
        }
      }
      return Grid(mtx)
    }
  }
}
