package common

data class Coord(val x: Int, val y: Int) {
  operator fun plus(other: Coord) = Coord(x = this.x + other.x, y = this.y + other.y)

  operator fun minus(other: Coord) = Coord(x = this.x - other.x, y = this.y - other.y)

  operator fun times(value: Int) = Coord(x = this.x * value, y = this.y * value)

  operator fun times(other: Coord) = Coord(x = this.x * other.x, y = this.y * other.y)

  operator fun unaryMinus() = times(-1)

  fun adjacentCoords(): List<Coord> = DIRS.map { other -> this + other }
}

val DIRS: List<Coord> = listOf(
  // top row
  Coord(x = -1, y = -1),
  Coord(x = +0, y = -1),
  Coord(x = +1, y = -1),
  // middle row
  Coord(x = -1, y = +0),
  Coord(x = +1, y = +0),
  // bottom row
  Coord(x = -1, y = +1),
  Coord(x = +0, y = +1),
  Coord(x = +1, y = +1)
)
