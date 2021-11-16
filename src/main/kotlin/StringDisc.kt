package ch.guengel.towers

import ch.guengel.towers.pegs.Disc

class StringDisc(private val size: Int) : Disc<String> {
    private val value = "#".repeat(size * 2 - 1)
    override fun size(): Int = size
    override fun get(): String = value
}
