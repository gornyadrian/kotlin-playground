import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ConditionalExpressionsTest {

    @Test
    fun `standard conditional expression`() {
        val num = 10
        val result: Boolean
        if (num > 5) {
            result = true
        } else {
            result = false
        }
        assertTrue(result)
    }

    @Test
    fun `'if' statement used as expression`() {
        val num1 = 5
        val num2 = 15
        fun maxOf(a: Int, b: Int) = if (a > b) a else b
        assertEquals(15, maxOf(num1, num2))
        assertEquals(15, maxOf(num2, num1))
    }

}
