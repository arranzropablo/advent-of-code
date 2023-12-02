package y2022.n3

object Solution {

    private val weighedChars = (('a'..'z') + ('A'..'Z')).mapIndexed { idx, value -> value to idx + 1 }.toMap()

    fun part1(inputLines: List<String>): Int {
        var result = 0
        inputLines.forEach { line ->
            val bags = line.chunked(line.length/2)
            val firstBagOccurrences = mutableMapOf<Char, Int>()

            bags[0].forEach { item ->
                firstBagOccurrences[item] = 1
            }

            for (item in bags[1]) {
                if (firstBagOccurrences.getOrDefault(item, 0) == 1) {
                    result += weighedChars[item]!!
                    break
                }
            }
        }
        return result
    }

    fun part2(inputLines: List<String>): Int {
        var result = 0
        inputLines.chunked(3).forEach { group ->
            val groupOccurences = mutableMapOf<Char, Int>()
            group.forEachIndexed { idx, line ->
                line.forEach { item ->
                    if (groupOccurences.getOrDefault(item, 0) == idx) {
                        groupOccurences[item] = idx+1
                    }
                }
            }
            val repeatedItem = groupOccurences.filter { (_, value) -> value == 3 }.keys.first()
            result += weighedChars[repeatedItem]!!
        }
        return result
    }

}