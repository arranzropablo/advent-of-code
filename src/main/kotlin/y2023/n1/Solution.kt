package y2023.n1

object Solution {

    fun part1(inputLines: List<String>): Int {
        var firstDigit = 0
        var lastDigit = 0
        var calibrationValue = 0
        inputLines.forEach { line ->
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

        return calibrationValue
    }

    fun part2(lines: List<String>): Int {
        val cleanLines = cleanLines(lines)
        return part1(cleanLines)
    }

    private fun cleanLines(lines: List<String>): List<String> {
        val sb = StringBuilder()
        lines.forEach { line ->
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
            sb.appendLine(modifiedLine)
        }
        return sb.trim().splitToSequence("\n").toList()
    }
}