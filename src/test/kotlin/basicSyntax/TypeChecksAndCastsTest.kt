package basicSyntax

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull
import kotlin.test.assertTrue

class TypeChecksAndCastsTest {

    fun getLength1(obj: Any): Int? {
        if (obj is String) {
            return obj.length // obj is automatically cast to String in this branch
        }
        // obj is still of type Any outside type-checked branch
        return null
    }

    fun getLength2(obj: Any): Int? {
        if (obj !is String) return null // same as !(obj is String)
        return obj.length // obj is automatically cast to String in this branch
    }

    fun getLength3(obj: Any): Int? {
        if (obj is String && obj.length > 0) { // obj is automatically cast to String on the right-hand side of &&
            return obj.length
        }
        return null
    }

    @Test
    fun `is operator`() {
        val obj: Any = "text"
        val anotherObj: Any = 100
        assertEquals(4, getLength1(obj))
        assertEquals(4, getLength2(obj))
        assertEquals(4, getLength3(obj))
        assertNull(getLength1(anotherObj))
        assertNull(getLength2(anotherObj))
        assertNull(getLength3(anotherObj))
    }

    @Test
    fun `is operator with lists`() {
        val list = ArrayList<Int>()
        fun isArrayList(list: List<Any>) = list is ArrayList // smart cast list to ArrayList<Any>
        assertTrue(isArrayList(list))
    }

    @Test
    fun `smart cast with when`() {
        fun check(value: Any) = when (value) {
            is Int -> value / 2 == 5 // value is automatically cast to Int
            is IntArray -> value.size // value is automatically cast to IntArray
            is String -> value.endsWith("xt") // value is automatically cast to String
            else -> "unknown"
        }
        assertEquals(true, check(10))
        assertEquals(false, check(5))
        assertEquals(4, check(intArrayOf(1, 5, 10, 20)))
        assertEquals(true, check("text"))
        assertEquals(false, check("abcd"))
        assertEquals("unknown", check(100L))
    }

    // smart casts can be used under the following conditions:

    // val local variables - always, with the exception of local delegated properties.

    // val properties - if the property is private or internal or if the check is performed in the same module
    // where the property is declared. Smart casts cannot be used on open properties or properties that have custom getters.

    // var local variables - if the variable is not modified between the check and the usage,
    // is not captured in a lambda that modifies it, and is not a local delegated property.

    // var properties - never, because the variable can be modified at any time by other code.

    @Test
    fun `unsafe cast operator`() {
        val empty: String? = null
        // val text: String = empty as String <- NullPointerException
         val text: String? = empty as String?
        assertNull(text)
    }

    @Test
    fun `safe cast operator`() {
        val empty: String? = null
        val text: String? = empty as? String // as? operator returns null on failure
        assertNull(text)
    }

}
