package basicSyntax

import kotlin.test.Test
import kotlin.test.assertEquals

class ForLoopsTest {

    @Test
    fun `simple for loop`() {
        val numbers = listOf(5, 10, 15)
        var sum = 0
        // Type of number variable is inferred by type of numbers collection.
        for (number in numbers) {
            sum += number
        }
        assertEquals(30, sum)
    }

    @Test
    fun `for loop with indices`() {
        val names = listOf("John", "Thomas", "Lucas")
        val indexedNames = mutableListOf<String>()
        // Collection<*>.indices returns an IntRange of the valid indices for this collection.
        for (index in names.indices) {
            indexedNames.add("${index + 1}. ${names[index]}")
        }
        assertEquals("1. John", indexedNames[0])
        assertEquals("2. Thomas", indexedNames[1])
        assertEquals("3. Lucas", indexedNames[2])
    }

}
