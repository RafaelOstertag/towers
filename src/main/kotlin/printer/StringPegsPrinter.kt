package ch.guengel.towers.print

import ch.guengel.towers.pegs.Pegs

class StringPegsPrinter(private val numberOfDiscs: Int) : PegsPrintStrategy<String> {
    private val maxPadding = " ".repeat(numberOfDiscs - 1)
    private val bottom = "=".repeat(numberOfDiscs * 2 - 1)

    override fun print(pegs: Pegs<String>) {
        println("$maxPadding|$maxPadding $maxPadding|$maxPadding $maxPadding|")
        val firstPegDiscs =
            padList(numberOfDiscs, pegs[0].getDiscs().map { padDisc(it.get()) })
        val secondPegDiscs =
            padList(numberOfDiscs, pegs[1].getDiscs().map { padDisc(it.get()) })
        val thirdPegDiscs =
            padList(numberOfDiscs, pegs[2].getDiscs().map { padDisc(it.get()) })

        (0 until numberOfDiscs).forEach {
            println("${firstPegDiscs[it]} ${secondPegDiscs[it]} ${thirdPegDiscs[it]}")
        }

        println("$bottom $bottom $bottom")
    }

    private fun padList(size: Int, list: List<String>): List<String> {
        val filler = (0 until (size - list.size)).map { "$maxPadding|$maxPadding" }
        return filler + list
    }

    private fun padDisc(disc: String): String {
        val padding = " ".repeat(numberOfDiscs - (disc.length / 2) - 1)
        return padding + disc + padding
    }
}
