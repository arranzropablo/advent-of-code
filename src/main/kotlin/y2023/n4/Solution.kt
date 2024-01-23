package y2023.n4

import kotlin.math.pow

object Solution {

    fun part1(inputLines: List<String>): Int {
        var result = 0
        inputLines.forEach { line ->
            var matchingNumbers = 0.0

            val cards = line.split(":")[1].trim().split("|").map(String::trim)
            val winningNumbers = cards[0].split(" ").map(String::trim).filter(String::isNotBlank).map(String::toInt)
            val numbersIHave = cards[1].split(" ").map(String::trim).filter(String::isNotBlank).map(String::toInt)

            winningNumbers.forEach { if (numbersIHave.contains(it)) matchingNumbers++ }

            if (matchingNumbers > 0) result += (2.0.pow(matchingNumbers - 1)).toInt()
        }

        return result
    }

    fun part2(inputLines: List<String>): Int {
        val numberOfCards = (1..inputLines.size).toList().associateWith { 1 }.toMutableMap()
        var currentCard = 0

        for (line in inputLines) {
            currentCard++

            var matchingNumbers = 0

            val cards = line.split(":")[1].trim().split("|").map(String::trim)
            val winningNumbers = cards[0].split(" ").map(String::trim).filter(String::isNotBlank).map(String::toInt)
            val numbersIHave = cards[1].split(" ").map(String::trim).filter(String::isNotBlank).map(String::toInt)

            winningNumbers.forEach { if (numbersIHave.contains(it)) matchingNumbers++ }

            for (i in currentCard + 1 .. (currentCard + matchingNumbers).coerceAtMost(inputLines.size))
                numberOfCards[i] = numberOfCards[i]!! + numberOfCards[currentCard]!!
        }

        return numberOfCards.values.sum()
    }
}