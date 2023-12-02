package y2022.n1

object Solution {

    fun part1(inputLines: List<String>): Int = getCaloriesPerElf(inputLines).first()

    fun part2(inputLines: List<String>): Int = getCaloriesPerElf(inputLines).subList(0, 3).sum()

    private fun getCaloriesPerElf(inputLines: List<String>): List<Int> {
        val elvesInventory = inputLines.joinToString(separator=",").split(",,")

        return elvesInventory.map { inventory ->
            inventory.split(",").sumOf { it.toInt() }
        }.sortedDescending()
    }



}