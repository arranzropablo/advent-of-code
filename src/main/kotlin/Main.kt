import java.io.File

fun main() {
    val problemYear = 2022
    val problemNumber = 2
    val part = 1
    val basePath = "src/main/resources/y$problemYear/n$problemNumber"
    val inputFilePath = "$basePath/input"

    val inputLines = File(inputFilePath).readLines()

    val fullClassName = "y$problemYear.n$problemNumber.Solution"
    val cls = Class.forName(fullClassName)
    val kotlinClass = cls.kotlin
    val result = cls.getMethod("part$part", List::class.java).invoke(kotlinClass.objectInstance, inputLines)

    println(result.toString())
}