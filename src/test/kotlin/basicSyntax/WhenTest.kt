package basicSyntax

import kotlin.test.Test
import kotlin.test.assertEquals

class WhenTest {

    //"when" defines conditional expression with multiple branches,
    //similar to the "switch" statement in C-like languages

    @Test
    fun `simple when expression`() {
        fun describe(obj: Any): String = when (obj) {
            1           -> "one"
            "abc"       -> "text"
            is Long     -> "long"
            !is String  -> "not a string"
            else        -> "unknown"
        }
        assertEquals("one", describe(1))
        assertEquals("text", describe("abc"))
        assertEquals("long", describe(2L))
        assertEquals("not a string", describe(3))
        assertEquals("unknown", describe("other"))
    }

    @Test
    fun `simple when statement`() {
        val obj: Any = 10L
        val result: String
        when (obj) {
            1           -> result = "one"
            "abc"       -> result = "text"
            is Long     -> result = "long"
            !is String  -> result = "not a string"
            else        -> result = "unknown"
        }
        assertEquals("long", result)
    }

    enum class Color {
        RED, GREEN, BLUE
    }

    @Test
    fun `when expression with mandatory else`() {
        fun describeColor(color: Color): String = when (color) {
            Color.RED -> "red color"
            Color.GREEN -> "green color"
            else -> "another color" // 'else' is required because not all enum cases are covered
        }
        assertEquals("another color", describeColor(Color.BLUE))
    }

    @Test
    fun `when expression without mandatory else`() {
        fun describeColor(color: Color): String = when (color) {
            Color.RED -> "red color"
            Color.GREEN -> "green color"
            Color.BLUE -> "blue color" // 'else' is not required because all enum cases are covered
        }
        assertEquals("blue color", describeColor(Color.BLUE))
    }

    @Test
    fun `when with multiple single line cases`() {
        fun specifyColor(obj: Any): String = when (obj) {
            "sun", "sunflower" -> "yellow"
            "sky", "bluescreen" -> "blue"
            "snow" -> "white"
            else -> "unknown"
        }
        assertEquals("yellow", specifyColor("sun"))
        assertEquals("yellow", specifyColor("sunflower"))
        assertEquals("blue", specifyColor("sky"))
        assertEquals("blue", specifyColor("bluescreen"))
        assertEquals("white", specifyColor("snow"))
        assertEquals("unknown", specifyColor("table"))
    }

    @Test
    fun `when with arbitrary expressions`() {
        fun isNumber(num: Number): Boolean = when (num) {
            num.toInt() -> true
            else -> false
        }
        assertEquals(true, isNumber(5))
        assertEquals(false, isNumber(5.3))
    }

    @Test
    fun `when with ranges`() {
        val validNumbers = listOf(23, 50, 100)
        fun isValidNumber(num: Int): Boolean = when (num) {
            in 1..5 -> true
            in validNumbers -> true
            else -> false
        }
        assertEquals(true, isValidNumber(2))
        assertEquals(true, isValidNumber(50))
        assertEquals(false, isValidNumber(51))
    }

    @Test
    fun `when with is`() {
        fun hasPostfix(word: Any) = when (word) {
            is String -> word.endsWith("ple")
            is Int -> "integer"
            else -> false
        }
        assertEquals(true, hasPostfix("example"))
        assertEquals(false, hasPostfix("exam"))
        assertEquals("integer", hasPostfix(5))
    }

    @Test
    fun `when without argument`() {
        val multiplier = 2
        fun checkNumber(num: Int) = when { // 'when' without argument can be used as a replacement for an 'if-else' chain
            num * multiplier == 10 -> true
            else -> false
        }
        assertEquals(true, checkNumber(5))
        assertEquals(false, checkNumber(4))
    }

    fun executeRequest(): Any {
        println("executing...")
        return "example response"
    }

    @Test
    fun `capturing when subject`() {
        val result = when (val response = executeRequest()) {
            is String -> response
            else -> "failure"
        }
        assertEquals("example response", result)
    }

}