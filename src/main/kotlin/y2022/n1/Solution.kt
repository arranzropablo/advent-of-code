package y2022.n1

object Solution {

    fun part1(inputLines: List<String>): Int {
        var maxCal = 0
        val elvesInventory = inputLines.joinToString(separator=",").split(",,")

        elvesInventory.forEach { inventory ->
            val caloriesAmount = inventory.split(",").sumOf { it.toInt() }
            if (caloriesAmount > maxCal)
                maxCal = caloriesAmount
        }
        return maxCal
    }

    fun part2(inputLines: List<String>): Unit {
    }

}