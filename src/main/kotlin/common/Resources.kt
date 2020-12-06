package common

import java.io.File
import java.net.URI

internal object Resources {
//  fun resourceAsString(fileName: String, delimiter: String = ""): String =
//    resourceAsList(fileName).reduce { a, b -> "$a$delimiter$b" }
//
//  fun resourceAsList(fileName: String): List<String> {
//    val uri = fileNameToUri(fileName)
//    return File(uri).readLines()
//  }

  data class Resource(
    private val lines: List<String>
  ) {
    /**
     * Returns resource as list of strings, one representing each line.
     */
    fun asLines(): List<String> = lines

    /**
     * Returns resource as a single string, joining lines with a delimiter.
     */
    fun asString(delimiter: String = ""): String {
      return lines.joinToString(separator = delimiter)
    }

    companion object {
      /**
       * Creates a resource by reading in lines from a file.
       */
      fun fromFile(fileName: String): Resource {
        val uri = fileNameToUri(fileName)
        val file = File(uri)
        val lines = file.readLines()
        return Resource(lines)
      }

      /**
       * Creates a resource from a String. For testing and debugging purposes.
       */
      fun fromString(input: String): Resource {
        return Resource(input.split("\n"))
      }
    }
  }
}

private fun fileNameToUri(fileName: String): URI {
  return Resources.javaClass.classLoader.getResource(fileName)?.toURI()
    ?: throw IllegalArgumentException("Cannot find Resource: $fileName")
}

