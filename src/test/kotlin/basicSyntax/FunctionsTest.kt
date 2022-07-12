package basicSyntax

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class FunctionsTest {

    @Test
    fun `void functions`() {
        // Unit is type corresponding to void in Java.
        // Unit is default function return type in Kotlin, it can be omitted in a method signature.
        fun foo() {
            print("bar")
        }
        // Assert that returned value is of type Unit.
        assertIs<Unit>(foo())
    }

    @Test
    fun `functions without parameters`() {
        // The declared return type must match the type of value returned by the function.
        fun foo(): String {
            return "bar"
        }
        assertIs<String>(foo())
        assertEquals("bar", foo())
    }

    @Test
    fun `functions with parameters`() {
        fun sum(a: Int, b: Int): Int {
            return a + b
        }
        assertEquals(15, sum(5, 10))
    }

    @Test
    fun `expressions as function bodies`() {
        fun sum(a: Int, b: Int): Int {
            return a + b
        }
        // A function body can be expression.
        fun sumExpression(a: Int, b: Int): Int = a + b
        assertEquals(25, sum(10, 15))
        assertEquals(25, sumExpression(10, 15))
    }

    @Test
    fun `inferring types of returned values`() {
        fun sum(a: Int, b: Int): Int = a + b
        // Return type is inferred so it can be omitted.
        fun sumTypeInferred(a: Int, b: Int) = a + b
        assertIs<Int>(sum(1, 1))
        assertIs<Int>(sumTypeInferred(1 , 1))
        assertEquals(30, sum(10, 20))
        assertEquals(30, sumTypeInferred(10, 20))
    }

}
