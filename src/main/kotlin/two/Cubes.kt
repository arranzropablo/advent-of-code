package one

import java.io.File

object Cubes {

    private const val maxRed = 12
    private const val maxBlue = 14
    private const val maxGreen = 13

    private const val basePath = "src/main/resources/two"
    private const val inputFilePath = "$basePath/input"

    fun getCubesResult() {
        var amount = 0
        File(inputFilePath).forEachLine { line ->
            val gameId = line.split(":")[0].split(" ")[1].toInt()
            val sets = line.split(":")[1].split(";").map { it.trim() }
            var validSet = true

            first@ for (set in sets) {
                val cubeAmounts = set.split(",").map { it.trim() }

                for (cubeAmount in cubeAmounts) {
                    val num = cubeAmount.split(" ")[0].toInt()
                    val color = cubeAmount.split(" ")[1]
                    if (color == "blue" && num > maxBlue ||
                        color == "red" && num > maxRed ||
                        color == "green" && num > maxGreen) {
                        validSet = false
                        break@first
                    }
                }
            }
            //if some cubeamount is false it comes here directly for that line, else add up the id
            if (validSet == true) {
                amount += gameId
            }
        }
        print(amount)
    }
}