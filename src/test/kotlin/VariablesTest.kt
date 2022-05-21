import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class VariablesTest {

    @Test
    fun `local variables`() {
        var num: Int = 5
        num = 10
        // Variables using 'var' keyword can be reassigned.
        assertEquals(10, num)
    }

    @Test
    fun `read-only local variables`() {
        val num: Int = 5
        // num = 10 <- Compilation error, variables using 'val' keyword cannot be reassigned.
    }

    @Test
    fun `inference of variable types`() {
        val num1: Int = 5
        // The type is inferred from the value.
        val num2 = 10
        assertIs<Int>(num1)
        assertIs<Int>(num2)
    }

    @Test
    fun `deferring of assignment`() {
        val num1: Int
        num1 = 5 // No compilation error, because assignment of variable is deferred.
        // num1 = 10 <- Compilation error, because value can be assigned only once.
        // val num2 <- Compilation error, because variable type is required when no initializer is provided.
        assertEquals(5, num1)
    }

    val PI = 3.14
    var num = 7

    @Test
    fun `top level variables`() {
        // Variables can be declared at the top level.
        assertEquals(3.14, PI)
        assertEquals(7, num)
        num += 2
        assertEquals(9, num)
    }

}
