package ch.guengel.towers.solver

import ch.guengel.towers.pegs.Pegs

interface SolverStrategy<T> {
    fun apply(pegs: Pegs<T>)
}
