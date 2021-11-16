package ch.guengel.towers.pegs

class Pegs<T> {
    private val pegList: List<Peg<T>>

    init {
        pegList = ArrayList<Peg<T>>(NUMBER_OF_PEGS).apply {
            (0 until NUMBER_OF_PEGS).forEach { _ -> add(Peg()) }
        }
    }

    operator fun get(index: Int): Peg<T> = pegList[index]

    /**
     * Find disc with given size.
     *
     * @return peg number of disc, or `-1` if disc is not on top of any peg.
     */
    fun findDisc(size: Int): Int {
        for (pegIndex in 0 until NUMBER_OF_PEGS) {
            val peg = pegList[pegIndex]
            if (!peg.isEmpty() && peg.peek().size() == size) {
                return pegIndex
            }
        }
        return -1
    }

    fun canMove(sourcePegIndex: Int, destinationPegIndex: Int): Boolean {
        if (sourcePegIndex == destinationPegIndex) {
            return false
        }

        val sourcePeg = pegList[sourcePegIndex]
        val destinationPeg = pegList[destinationPegIndex]

        if (sourcePeg.isEmpty()) {
            return false
        }

        if (!destinationPeg.isEmpty() && sourcePeg.peek().size() > destinationPeg.peek().size()) {
            return false
        }

        return true
    }

    fun moveDisc(sourcePegIndex: Int, destinationPegIndex: Int) {
        if (sourcePegIndex == destinationPegIndex) {
            throw IllegalArgumentException("Source and destination peg are identical")
        }

        val sourcePeg = pegList[sourcePegIndex]
        val destinationPeg = pegList[destinationPegIndex]

        if (sourcePeg.isEmpty()) {
            throw IllegalArgumentException("Source peg is empty")
        }

        if (!destinationPeg.isEmpty() && sourcePeg.peek().size() > destinationPeg.peek().size()) {
            throw IllegalArgumentException("Disc on peg $destinationPegIndex is smaller than disc on $sourcePegIndex")
        }

        destinationPeg.push(sourcePeg.pop())
    }

    companion object {
        const val NUMBER_OF_PEGS = 3
    }
}
