package ch.guengel.towers.pegs

import ch.guengel.towers.setupPegs
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class SetupKtTest {

    @Test
    fun `should setup correctly`() {
        val discs = listOf(TestDisc(2), TestDisc(1), TestDisc(0))

        val pegs = setupPegs(discs)
        (1..2).forEach {
            assertTrue(pegs.get(it).isEmpty())
        }

        val firstPeg = pegs.get(0)

        var disc = firstPeg.pop()
        assertEquals(0, disc.size())
        disc = firstPeg.pop()
        assertEquals(1, disc.size())
        disc = firstPeg.pop()
        assertEquals(2, disc.size())

    }
}
