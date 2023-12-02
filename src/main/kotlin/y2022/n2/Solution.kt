package y2022.n2

object Solution {

    val elfRock = "A"
    val elfPaper = "B"
    val elfScissors = "C"

    val meRock = "X"
    val mePaper = "Y"
    val meScissors = "Z"

    val wantedLose = "X"
    val wantedDraw = "Y"
    val wantedWin = "Z"

    val rockScore = 1
    val paperScore = 2
    val scissorsScore = 3

    val loseScore = 0
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
                            loseScore + scissorsScore
                        else -> 0
                    }
                elfPaper ->
                    when (me) {
                        meRock ->
                            loseScore + rockScore
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
                            loseScore + paperScore
                        meScissors ->
                            drawScore + scissorsScore
                        else -> 0
                    }
                else -> 0
            }
        }
        return result
    }

    fun part2(inputLines: List<String>): Int {
        var result = 0
        inputLines.forEach { line ->
            val elf = line.split(" ")[0]
            val wantedResult = line.split(" ")[1]

            result += when (elf) {
                elfRock ->
                    when(wantedResult) {
                        wantedLose ->
                            loseScore + scissorsScore
                        wantedDraw ->
                            drawScore + rockScore
                        wantedWin ->
                            winScore + paperScore
                        else -> 0
                    }
                elfPaper ->
                    when(wantedResult) {
                        wantedLose ->
                            loseScore + rockScore
                        wantedDraw ->
                            drawScore + paperScore
                        wantedWin ->
                            winScore + scissorsScore
                        else -> 0
                    }
                elfScissors ->
                    when(wantedResult) {
                        wantedLose ->
                            loseScore + paperScore
                        wantedDraw ->
                            drawScore + scissorsScore
                        wantedWin ->
                            winScore + rockScore
                        else -> 0
                    }
                else -> 0
            }
        }
        return result
    }

}