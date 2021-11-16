package ch.guengel.towers.pegs

import java.util.*

private const val TOP_MOST_ELEMENT = 0

class Peg<T> {
    private val stack: MutableList<Disc<T>> = LinkedList()

    fun peek(): Disc<T> {
        if (stack.isEmpty()) {
            throw NoSuchElementException("Peg has no discs")
        }
        return stack.first()
    }

    fun push(disc: Disc<T>) {
        if (stack.isNotEmpty() && peek().size() < disc.size()) {
            throw IllegalArgumentException("Disc to be added to peg is greater than top most element")
        }

        stack.add(TOP_MOST_ELEMENT, disc)
    }

    fun pop(): Disc<T> {
        if (stack.isEmpty()) {
            throw NoSuchElementException("Peg has no discs")
        }
        return stack.removeFirst()
    }

    fun isEmpty(): Boolean = stack.isEmpty()

    fun getDiscs(): List<Disc<T>> = stack
}
