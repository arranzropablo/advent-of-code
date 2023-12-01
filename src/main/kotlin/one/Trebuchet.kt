package one

import java.io.File

object Trebuchet {
    private const val basePath = "src/main/resources/one"
    private const val inputFilePath = "$basePath/calibration-input"
    private const val cleanFilePath = "$basePath/clean-calibration-input"

    fun getCalibrationValuesSumPartOne() {
        getCalibrationValuesSum(inputFilePath)
    }

    fun getCalibrationValuesSumPartTwo() {
        cleanInputFile(inputFilePath, cleanFilePath)
        getCalibrationValuesSum(cleanFilePath)
    }

    private fun cleanInputFile(originalInput: String, cleanInput: String) {
        val cleanFile = File(cleanInput)
        if (cleanFile.exists()) {
            cleanFile.delete()
        }
        cleanFile.createNewFile()
        File(originalInput).forEachLine { line ->
            var modifiedLine = line
            //this way it doesnt matter that one is replace before eight, for example, because if there's
            //some overlapping as in eighthree it will result in eighthree -> eighthree3three -> eight8eigthree3three
            //took the idea from https://www.reddit.com/r/adventofcode/comments/1885c33/comment/kbjaygo/?utm_source=share&utm_medium=web2x&context=3
            //I tried doing it with a recursive function replacing from the beginning the substrings but was having a problem somewhere
            modifiedLine = modifiedLine.replace("one", "one1one")
            modifiedLine = modifiedLine.replace("two", "two2two")
            modifiedLine = modifiedLine.replace("three", "three3three")
            modifiedLine = modifiedLine.replace("four", "four4four")
            modifiedLine = modifiedLine.replace("five", "five5five")
            modifiedLine = modifiedLine.replace("six", "six6six")
            modifiedLine = modifiedLine.replace("seven", "seven7seven")
            modifiedLine = modifiedLine.replace("eight", "eight8eight")
            modifiedLine = modifiedLine.replace("nine", "nine9nine")
            cleanFile.appendText("$modifiedLine\n")
        }
    }

    private fun getCalibrationValuesSum(inputFile: String) {
        var firstDigit = 0
        var lastDigit = 0
        var calibrationValue = 0
        File(inputFile).forEachLine { line ->
            for (i in 0 .. line.lastIndex) {
                if (line[i].isDigit()) {
                    firstDigit = line[i].digitToInt()
                    break
                }
            }
            for (i in line.lastIndex downTo 0) {
                if (line[i].isDigit()) {
                    lastDigit = line[i].digitToInt()
                    break
                }
            }
            val valueToAdd = "$firstDigit$lastDigit".toInt()
            calibrationValue += valueToAdd
        }
        println(calibrationValue)
    }
}