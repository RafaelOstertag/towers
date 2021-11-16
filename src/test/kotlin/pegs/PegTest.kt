package ch.guengel.towers.pegs

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

internal class PegTest {
    lateinit var peg: Peg<Nothing>

    @BeforeEach
    fun beforeEach() {
        peg = Peg()
    }

    @Test
    fun `should push disc`() {
        val testDisc = TestDisc(1)
        peg.push(testDisc)

        val actual = peg.peek()
        assertEquals(testDisc.size, actual.size())
    }

    @Test
    fun `should not allow stacking bigger discs on smaller`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)

        peg.push(disc1)

        assertThrows<IllegalArgumentException> { peg.push(disc2) }
    }

    @Test
    fun `should stack properly`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)

        peg.push(disc2)
        peg.push(disc1)

        assertEquals(disc1.size, peg.peek().size())
    }

    @Test
    fun `should throw exception on empty peek`() {
        assertThrows<NoSuchElementException> { peg.peek() }
    }

    @Test
    fun `pop works`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)

        peg.push(disc2)
        peg.push(disc1)

        val actual1 = peg.pop()
        assertEquals(disc1.size, actual1.size())
        assertFalse(peg.isEmpty())

        val actual2 = peg.pop()
        assertEquals(disc2.size, actual2.size())
        assertTrue(peg.isEmpty())
    }

    @Test
    fun `pop on empty peg throws`() {
        assertThrows<NoSuchElementException> { peg.pop() }
    }

    @Test
    fun `isEmpty works`() {
        assertTrue(peg.isEmpty())

        peg.push(TestDisc(1))
        assertFalse(peg.isEmpty())
    }

    @Test
    fun `get discs`() {
        val disc1 = TestDisc(1)
        val disc2 = TestDisc(2)

        peg.push(disc2)
        peg.push(disc1)

        val discs = peg.getDiscs()
        assertEquals(2, discs.size)
        assertEquals(1, discs[0].size())
        assertEquals(2, discs[1].size())
    }
}

