package y2022.n2

object Solution {

    val elfRock = "A"
    val elfPaper = "B"
    val elfScissors = "C"

    val meRock = "X"
    val mePaper = "Y"
    val meScissors = "Z"

    val rockScore = 1
    val paperScore = 2
    val scissorsScore = 3

    val lostScore = 0
    val drawScore = 3
    val winScore = 6

    fun part1(inputLines: List<String>): Int {
        var result = 0
        inputLines.forEach { line ->
            val elf = line.split(" ")[0]
            val me = line.split(" ")[1]

            result += when (elf) {
                elfRock ->
                    when {
                        me == meRock ->
                            drawScore + rockScore
                        me == mePaper ->
                            winScore + paperScore
                        me == meScissors ->
                            lostScore + scissorsScore
                        else -> 0
                    }
                elfPaper ->
                    when (me) {
                        meRock ->
                            lostScore + rockScore
                        mePaper ->
                            drawScore + paperScore
                        meScissors ->
                            winScore + scissorsScore
                        else -> 0
                    }
                elfScissors ->
                    when (me) {
                        meRock ->
                            winScore + rockScore
                        mePaper ->
                            lostScore + paperScore
                        meScissors ->
                            drawScore + scissorsScore
                        else -> 0
                    }
                else -> 0
            }
        }
        return result
    }

    fun part2(inputLines: List<String>): Unit {

    }

}