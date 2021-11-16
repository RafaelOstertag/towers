package ch.guengel.towers.print

import ch.guengel.towers.pegs.Pegs

interface PegsPrintStrategy<T> {
    fun print(pegs: Pegs<T>)
}
