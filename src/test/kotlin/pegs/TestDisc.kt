package ch.guengel.towers.pegs

internal class TestDisc(val size: Int) : Disc<Nothing> {
    override fun size(): Int = size

    override fun get(): Nothing {
        throw NotImplementedError()
    }

}
