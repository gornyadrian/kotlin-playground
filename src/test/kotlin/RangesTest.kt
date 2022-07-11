import kotlin.test.Test
import kotlin.test.assertEquals

class RangesTest {

    @Test
    fun `simple range example`() {
        val i = 3
        val isInRange: Boolean
        if (i in 1..5) { // equivalent of (1 <= i && i <= 5)
            isInRange = true
        } else {
            isInRange = false
        }
        assertEquals(true, isInRange)
    }

    @Test
    fun `range in expression`() {
        fun isInRange(num: Int) = num in 1..20
        assertEquals(true, isInRange(5))
        assertEquals(false, isInRange(21))
    }

    @Test
    fun `range in when expression`() {
        val list = listOf("John", "Alice", "Peter")
        fun getNameAtIndex(index: Int) = when (index) {
            in list.indices -> list[index]
            else -> "index out of bound"
        }
        assertEquals("Peter", getNameAtIndex(2))
        assertEquals("index out of bound", getNameAtIndex(3))
    }

    @Test
    fun `range in for loop`() {
        var sum = 0
        for (x in 1..5) { // 1, 2, 3, 4, 5
            sum++
        }
        assertEquals(5, sum)
    }

    @Test
    fun `range in for loop with step`() {
        var sum = 0
        for (x in 1..5 step 2) { // 1, 3, 5
            sum++
        }
        assertEquals(3, sum)
    }

    @Test
    fun `range in for loop with step and downTo`() {
        var text = ""
        for (x in 5 downTo 1 step 2) { // 5, 3, 1
            text += x
        }
        assertEquals(531, text.toInt())
    }

    @Test
    fun `range in for loop with reversed`() {
        var text = ""
        val intRange = 1..5
        for (x in intRange.reversed()) { // equivalent to 5 downTo 1
            text += x
        }
        assertEquals(54321, text.toInt())
    }

    @Test
    fun `range in for loop without last element`() {
        var text = ""
        for (x in 1 until 5) { // 1, 2, 3, 4
            text += x
        }
        assertEquals(1234, text.toInt())
    }

    @Test
    fun `range with string`() {
        val word = "John"
        var reversed = ""
        for (char in word.reversed()) {
            reversed += char
        }
        assertEquals("nhoJ", reversed)
    }

}