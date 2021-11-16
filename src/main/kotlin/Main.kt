package ch.guengel.towers

import ch.guengel.towers.print.StringPegsPrinter
import ch.guengel.towers.solver.NaiveSolverStrategy
import ch.guengel.towers.solver.SmartSolverStrategy
import ch.guengel.towers.solver.SolverStrategy
import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType
import kotlin.math.pow

enum class Solver(val strategy: SolverStrategy<String>) {
    NAIVE(NaiveSolverStrategy<String>()),
    SMART(SmartSolverStrategy<String>())
}

fun main(args: Array<String>) {
    val parser = ArgParser("Tower of Hanoi")
    val numberOfDiscs by parser.option(
        ArgType.Int,
        fullName = "discs",
        shortName = "n",
        description = "number of discs"
    )
    val solver by parser.option(
        ArgType.Choice<Solver>(),
        fullName = "solver",
        shortName = "s",
        description = "Solver to use"
    )
    val delay by parser.option(ArgType.Int, fullName = "delay", shortName = "d", "delay in ms between moves")

    parser.parse(args)

    run(numberOfDiscs ?: 3, solver ?: Solver.SMART, delay ?: 750)
}

private fun run(numberOfDiscs: Int, solver: Solver, delay: Int) {
    val discs = (1..numberOfDiscs).map { StringDisc(it) }

    val pegs = setupPegs(discs)
    val solverStrategy = solver.strategy
    val pegsPrintStrategy = StringPegsPrinter(discs.size)

    var move = 0
    println("move #${move}")
    pegsPrintStrategy.print(pegs)
    println()
    while (!pegs.get(0).isEmpty() || !pegs.get(1).isEmpty()) {
        solverStrategy.apply(pegs)
        move++

        println("move #${move}")
        pegsPrintStrategy.print(pegs)
        println()

        Thread.sleep(delay.toLong())
    }

    println("Optimal number of moves ${2.0.pow(discs.size).toInt() - 1}; required number of moves $move")
}

