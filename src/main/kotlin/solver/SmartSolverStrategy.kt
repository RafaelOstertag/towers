package ch.guengel.towers.solver

import ch.guengel.towers.pegs.Pegs

class SmartSolverStrategy<T> : SolverStrategy<T> {
    private var moveNumber = 0
    private var previousPegOfSmallestDisc = -1
    private var currentPegOfSmallestDisc = 0
    private var pegIndices = listOf(0, 1, 2)

    override fun apply(pegs: Pegs<T>) {
        if (moveNumber == 0) {
            val newSmallestDiscPeg = if (pegs[0].getDiscs().size % 2 == 0) 1 else 2
            pegs.moveDisc(0, newSmallestDiscPeg)
            previousPegOfSmallestDisc = 0
            currentPegOfSmallestDisc = newSmallestDiscPeg
            moveNumber++
            return
        }

        if (isSmallestDiscMove()) {
            val newSmallestDiscPeg = computeNextPegIndex()
            pegs.moveDisc(currentPegOfSmallestDisc, newSmallestDiscPeg)
            previousPegOfSmallestDisc = currentPegOfSmallestDisc
            currentPegOfSmallestDisc = newSmallestDiscPeg
        } else {
            val viablePegs = pegIndices.filter { it != currentPegOfSmallestDisc }
            if (pegs.canMove(viablePegs[0], viablePegs[1])) {
                pegs.moveDisc(viablePegs[0], viablePegs[1])
            } else {
                pegs.moveDisc(viablePegs[1], viablePegs[0])
            }
        }
        moveNumber++
    }

    private fun isSmallestDiscMove() = moveNumber % 2 == 0

    private fun computeNextPegIndex() =
        pegIndices.first { it != currentPegOfSmallestDisc && it != previousPegOfSmallestDisc }
}
