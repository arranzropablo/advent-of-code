package y2023.n3

object Solution {

    fun part1(inputLines: List<String>): Int {
        val numbersMap = mutableMapOf<Pair<Int, Int>, Int>()
        val symbolsPositions = mutableListOf<Pair<Int, Int>>()
        var currentNumber: Int = 0
        var result: Int = 0

        for (row in inputLines.indices) {
            val characters = inputLines[row].toCharArray().toList()
            //reset to 0 when line ends with digit
            currentNumber = 0
            for (col in characters.indices) {
                val char = characters[col]
                if (char.isDigit()) {
                    if (currentNumber == 0) {
                        //si es -1 es size
                        val offset = characters.subList(col, characters.size).indexOfFirst { !it.isDigit() }
                        val numberUntil = if (offset == -1) characters.size else col+offset
                        currentNumber = characters.subList(col, numberUntil).joinToString("").toInt()
                    }
                    numbersMap[Pair(row, col)] = currentNumber
                } else if (char != '.') {
                    symbolsPositions.add(Pair(row, col))
                    currentNumber = 0
                } else {
                    currentNumber = 0
                }
            }
        }
        symbolsPositions.forEach { pos ->
            val numNW = numbersMap[pos.copy(first = pos.first - 1, second = pos.second - 1)] ?: 0
            val numN = numbersMap[pos.copy(first = pos.first - 1)] ?: 0
            val numNE = numbersMap[pos.copy(first = pos.first - 1, second = pos.second + 1)] ?: 0
            val numW = numbersMap[pos.copy(second = pos.second - 1)] ?: 0
            val numE = numbersMap[pos.copy(second = pos.second + 1)] ?: 0
            val numSW = numbersMap[pos.copy(first = pos.first + 1, second = pos.second - 1)] ?: 0
            val numS = numbersMap[pos.copy(first = pos.first + 1)] ?: 0
            val numSE = numbersMap[pos.copy(first = pos.first + 1, second = pos.second + 1)] ?: 0

            result += numNW
            if (numN != numNW) result += numN
            if (numNE != numN) result += numNE
            result += numW
            result += numE
            result += numSW
            if (numS != numSW) result += numS
            if (numSE != numS) result += numSE
        }

        return result
    }

    fun part2(inputLines: List<String>): Int {
        val numbersMap = mutableMapOf<Pair<Int, Int>, Int>()
        val gearPositions = mutableListOf<Pair<Int, Int>>()
        var currentNumber: Int = 0
        var result: Int = 0

        for (row in inputLines.indices) {
            val characters = inputLines[row].toCharArray().toList()
            //reset to 0 when line ends with digit
            currentNumber = 0
            for (col in characters.indices) {
                val char = characters[col]
                if (char.isDigit()) {
                    if (currentNumber == 0) {
                        //si es -1 es size
                        val offset = characters.subList(col, characters.size).indexOfFirst { !it.isDigit() }
                        val numberUntil = if (offset == -1) characters.size else col+offset
                        currentNumber = characters.subList(col, numberUntil).joinToString("").toInt()
                    }
                    numbersMap[Pair(row, col)] = currentNumber
                } else if (char == '*') {
                    gearPositions.add(Pair(row, col))
                    currentNumber = 0
                } else {
                    currentNumber = 0
                }
            }
        }
        gearPositions.forEach { pos ->
            val numNW = numbersMap[pos.copy(first = pos.first - 1, second = pos.second - 1)] ?: 0
            val numN = numbersMap[pos.copy(first = pos.first - 1)] ?: 0
            val numNE = numbersMap[pos.copy(first = pos.first - 1, second = pos.second + 1)] ?: 0
            val numW = numbersMap[pos.copy(second = pos.second - 1)] ?: 0
            val numE = numbersMap[pos.copy(second = pos.second + 1)] ?: 0
            val numSW = numbersMap[pos.copy(first = pos.first + 1, second = pos.second - 1)] ?: 0
            val numS = numbersMap[pos.copy(first = pos.first + 1)] ?: 0
            val numSE = numbersMap[pos.copy(first = pos.first + 1, second = pos.second + 1)] ?: 0

            val validNumbers = arrayOf(
                numNW,
                if (numN != numNW) numN else 0,
                if (numNE != numN) numNE else 0,
                numW,
                numE,
                numSW,
                if (numS != numSW) numS else 0,
                if (numSE != numS) numSE else 0,
            ).filter { it != 0 }

            if (validNumbers.size == 2) result += (validNumbers.first() * validNumbers.last())

        }

        return result
    }
}