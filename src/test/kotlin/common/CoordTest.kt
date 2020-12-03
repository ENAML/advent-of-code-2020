package common

import kotlin.test.Test
import kotlin.test.assertEquals

class CoordTest {
  @Test
  fun `should create coord`() {
    val testX = 3
    val testY = 5

    val coord = Coord(testX, testY)

    assertEquals(coord.x, testX)
    assertEquals(coord.y, testY)
  }

  @Test
  fun `plus adds coord`() {
    val x1 = 5
    val y1 = 2
    val x2 = -3
    val y2 = 6

    assertEquals(
      Coord(x1, y1) + Coord(x2, y2),
      Coord(x1 + x2, y1 + y2)
    )
  }

  @Test
  fun `minus subtracts coord`() {
    val x1 = -1
    val x2 = 2
    val y1 = 3
    val y2 = -4

    assertEquals(
      Coord(x1, y1) - Coord(x2, y2),
      Coord(x1 - x2, y1 - y2)
    )
  }

  @Test
  fun `times with scalar multiplies coord`() {
    assertEquals(
      Coord(2, -3) * 5,
      Coord(/* 2 * 5 = */ 10, /* -3 * 5 = */ -15)
    )
  }

  @Test
  fun `unaryMinus inverts coord`() {
    assertEquals(
      -Coord(2, -3),
      Coord(-2, 3)
    )
  }
}
