package y2023.n2

object Solution {

    private const val maxRed = 12
    private const val maxBlue = 14
    private const val maxGreen = 13

    fun part1(inputLines: List<String>): Int {
        var amount = 0
        inputLines.forEach { line ->
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
        return amount
    }

    fun part2(inputLines: List<String>): Int {
        var result = 0
        inputLines.forEach { line ->
            val sets = line.split(":")[1].split(";").map { it.trim() }
            var minRed = 0
            var minBlue = 0
            var minGreen = 0

            for (set in sets) {
                val cubeAmounts = set.split(",").map { it.trim() }

                for (cubeAmount in cubeAmounts) {
                    val num = cubeAmount.split(" ")[0].toInt()
                    val color = cubeAmount.split(" ")[1]

                    if (color == "blue" && num > minBlue) {
                        minBlue = num
                    }
                    if (color == "red" && num > minRed) {
                        minRed = num
                    }
                    if (color == "green" && num > minGreen) {
                        minGreen = num
                    }
                }
            }
            result += (minBlue*minRed*minGreen)
        }
        return result
    }
}