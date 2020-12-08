package common

fun List<String>.splitByEmptyLines(): List<List<String>> {
  check(this.isNotEmpty())
  val lineGroups = mutableListOf<MutableList<String>>(mutableListOf())
  for (line in this) {
    if (line.isEmpty()) {
      lineGroups.add(mutableListOf())
    } else {
      lineGroups.last().add(line)
    }
  }
  return lineGroups.map { it.toList() }.toList()
}
