package ch.guengel.towers.solver

import ch.guengel.towers.pegs.Pegs

class NaiveSolverStrategy<T> : SolverStrategy<T> {
    private var currentPegIndex: Int = 0

    override fun apply(pegs: Pegs<T>) {
        var nextPegIndex = computeNextPegIndex(currentPegIndex)
        while (!pegs.canMove(currentPegIndex, nextPegIndex)) {
            nextPegIndex = computeNextPegIndex(nextPegIndex)
            if (nextPegIndex == currentPegIndex) {
                currentPegIndex = computeNextPegIndex(currentPegIndex)
                nextPegIndex = computeNextPegIndex(currentPegIndex)
            }
        }

        pegs.moveDisc(currentPegIndex, nextPegIndex)
        currentPegIndex = computeNextPegIndex(nextPegIndex)
    }

    private fun computeNextPegIndex(idx: Int) = (idx + 1) % Pegs.NUMBER_OF_PEGS
}
