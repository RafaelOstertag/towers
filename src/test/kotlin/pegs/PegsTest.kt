package ch.guengel.towers.pegs

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PegsTest {
    lateinit var pegs: Pegs<Nothing>

    @BeforeEach
    fun beforeEach() {
        pegs = Pegs()
    }

    @Test
    fun `should construct`() {
        (0 until Pegs.NUMBER_OF_PEGS).forEach {
            val actual = pegs.get(it)
            assertTrue(actual.isEmpty())
        }

        assertThrows<IndexOutOfBoundsException> { pegs[Pegs.NUMBER_OF_PEGS] }
    }

    @Test
    fun `should find disc`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)
        val disc3 = TestDisc(3)
        val disc4 = TestDisc(4)

        pegs[0].push(disc4)
        pegs[0].push(disc3)
        pegs[1].push(disc2)
        pegs[2].push(disc1)

        assertEquals(-1, pegs.findDisc(4))
        assertEquals(0, pegs.findDisc(3))
        assertEquals(1, pegs.findDisc(2))
        assertEquals(2, pegs.findDisc(1))

        assertEquals(-1, pegs.findDisc(5))
        assertEquals(-1, pegs.findDisc(-1))
    }

    @Test
    fun `canMove works`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)
        val disc3 = TestDisc(3)
        val disc4 = TestDisc(4)

        pegs[0].push(disc4)
        pegs[0].push(disc3)
        pegs[1].push(disc2)
        pegs[2].push(disc1)

        assertFalse(pegs.canMove(0, 0))
        assertFalse(pegs.canMove(0, 1))
        assertFalse(pegs.canMove(0, 1))

        assertTrue(pegs.canMove(2, 1))
        assertTrue(pegs.canMove(2, 0))
        assertTrue(pegs.canMove(1, 0))
    }

    @Test
    fun `canMove works with empty pegs`() {
        assertFalse(pegs.canMove(0, 1))
        assertFalse(pegs.canMove(0, 2))
        assertFalse(pegs.canMove(1, 2))
    }

    @Test
    fun `canMove does not allow to move on same peg`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)
        val disc3 = TestDisc(3)
        val disc4 = TestDisc(4)

        pegs[0].push(disc4)
        pegs[0].push(disc3)
        pegs[1].push(disc2)
        pegs[2].push(disc1)

        assertFalse(pegs.canMove(0, 0))
        assertFalse(pegs.canMove(1, 1))
        assertFalse(pegs.canMove(2, 2))
    }

    @Test
    fun `canMove fails on invalid peg indices`() {
        assertThrows<IndexOutOfBoundsException> { pegs.canMove(0, Pegs.NUMBER_OF_PEGS) }
        assertThrows<IndexOutOfBoundsException> { pegs.canMove(Pegs.NUMBER_OF_PEGS, 0) }
    }

    @Test
    fun `move disc must move to empty peg`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)
        pegs[0].push(disc2)
        pegs[0].push(disc1)

        pegs.moveDisc(0, 1)

        assertFalse(pegs[0].isEmpty())
        assertFalse(pegs[1].isEmpty())
        assertTrue(pegs[2].isEmpty())

        assertEquals(2, pegs[0].peek().size())
        assertEquals(1, pegs[1].peek().size())
    }

    @Test
    fun `move disc must move onto bigger smaller disc`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)
        pegs[0].push(disc2)
        pegs[1].push(disc1)

        assertThrows<IllegalArgumentException> { pegs.moveDisc(0, 1) }

        assertFalse(pegs[0].isEmpty())
        assertFalse(pegs[1].isEmpty())
        assertTrue(pegs[2].isEmpty())

        assertEquals(2, pegs[0].peek().size())
        assertEquals(1, pegs[1].peek().size())
    }

    @Test
    fun `move must not move from empty peg`() {
        val disc1 = TestDisc(1)
        pegs[1].push(disc1)

        assertThrows<IllegalArgumentException> { pegs.moveDisc(0, 1) }

        assertTrue(pegs[0].isEmpty())
        assertFalse(pegs[1].isEmpty())
        assertTrue(pegs[2].isEmpty())

        assertEquals(1, pegs[1].peek().size())
    }

    @Test
    fun `move must not move to non-existing peg`() {
        val disc1 = TestDisc(1)
        pegs[1].push(disc1)

        assertThrows<IndexOutOfBoundsException> { pegs.moveDisc(1, 4) }

        assertTrue(pegs[0].isEmpty())
        assertFalse(pegs[1].isEmpty())
        assertTrue(pegs[2].isEmpty())

        assertEquals(1, pegs[1].peek().size())
    }

    @Test
    fun `move must not move from non-existing peg`() {
        val disc1 = TestDisc(1)
        pegs[1].push(disc1)

        assertThrows<IndexOutOfBoundsException> { pegs.moveDisc(4, 1) }

        assertTrue(pegs[0].isEmpty())
        assertFalse(pegs[1].isEmpty())
        assertTrue(pegs[2].isEmpty())

        assertEquals(1, pegs[1].peek().size())
    }

    @Test
    fun `move disc must not move to same peg`() {
        assertThrows<IllegalArgumentException> { pegs.moveDisc(0, 0) }
    }
}
