package basicSyntax

import kotlin.test.Test
import kotlin.test.assertEquals

class StringTemplatesTest {

    @Test
    fun `simple templates`() {
        val num = 10
        val str = "the number is $num"
        assertEquals("the number is 10", str)
    }

    @Test
    fun `expressions in templates`() {
        val num1 = 10
        val num2 = 20
        val str1 = "the number is ${num1 + num2}"
        val str2 = "${str1.replace("number", "string").replace("is", "isn't")}!"
        assertEquals("the number is 30", str1)
        assertEquals("the string isn't 30!", str2)
    }

}
