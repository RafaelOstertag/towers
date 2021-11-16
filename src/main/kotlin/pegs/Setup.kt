package ch.guengel.towers

import ch.guengel.towers.pegs.Disc
import ch.guengel.towers.pegs.Pegs

fun <T> setupPegs(discs: List<Disc<T>>): Pegs<T> = Pegs<T>().apply {
    val firstPeg = get(0)
    discs.sortedByDescending { disc -> disc.size() }.forEach { disc -> firstPeg.push(disc) }
}

