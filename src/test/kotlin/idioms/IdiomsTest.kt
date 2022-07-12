package idioms

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class IdiomsTest {

    // provides a Customer class with the following functionality:
    // getters (and setters in case of vars) for all properties
    // equals()
    // hashCode()
    // toString()
    // copy()
    // component1(), component2(), ..., for all properties
    data class Customer(val name: String, val email: String)

    @Test
    fun `DTO tests`() {
        val customer1 = Customer("John", "john.doe@example.com")
        val customer2 = customer1.copy()
        assertEquals("Customer(name=John, email=john.doe@example.com)", customer1.toString())
        assertEquals("Customer(name=John, email=john.doe@example.com)", customer2.toString())
        assertEquals(-1044601271, customer1.hashCode())
        assertEquals(-1044601271, customer2.hashCode())
        assertEquals(customer1, customer2)
    }

    @Test
    fun `default parameters for function`() {
        fun foo(a: String = "default", b: Int = 0) = a.uppercase().plus(b)
        assertEquals("DEFAULT0", foo())
        assertEquals("ABCD0", foo("abcd"))
        assertEquals("ABCD10", foo("abcd", 10))
    }

    @Test
    fun `filter a list`() {
        val list = listOf(-500, -20, -5, 1, 2, 3, 4, 5, 10, 50, 100, 500)
        val negatives = list.filter { it < 0 } // equivalent to { x -> x < 0 }
        assertEquals(3, negatives.size)
        assertEquals(-500, negatives[0])
        assertEquals(-20, negatives[1])
        assertEquals(-5, negatives[2])
    }

    @Test
    fun `check presence of an element in a collection`() {
        val names = listOf("John", "Joe", "Peter")
        fun isNameInList(name: String) = name in names // if ("John" in names) true else false
        assertTrue(isNameInList("Joe"))
        assertFalse(isNameInList("Andrew"))
    }

    @Test
    fun `string interpolation`() {
        val name = "John"
        val greeting = "Hi, my name is $name!"
        assertEquals("Hi, my name is John!", greeting)
    }

    abstract class Animal
    class Dog : Animal()
    class Cat : Animal()

    @Test
    fun `instance checks`() {
        fun checkAnimal(animal: Animal) = when (animal) {
            is Dog -> "This is dog"
            is Cat -> "This is cat"
            else -> "This is unknown animal"
        }
        val animal1 = Dog()
        val animal2 = Cat()
        assertEquals("This is dog", checkAnimal(animal1))
        assertEquals("This is cat", checkAnimal(animal2))
    }

    @Test
    fun `read-only list`() {
        val emptyReadOnlyList = emptyList<Int>()
        val readOnlyList = listOf(1, 2, 3)
        // emptyReadOnlyList.add(4) <- compilation error: the list is read-only
        // readOnlyList.add(4) <- compilation error: the list is read-only
        assertTrue(emptyReadOnlyList.isEmpty())
        assertEquals(3, readOnlyList.size)
    }

    @Test
    fun `read-only map`() {
        val emptyReadOnlyMap = emptyMap<String, Int>()
        val readOnlyMap = mapOf("id1" to 1, "id2" to 2, "id3" to 3)
        // emptyReadOnlyMap.put("id4" to 4) <- compilation error: the map is read-only
        // readOnlyMap.put("id4" to 4) <- compilation error: the map is read-only
        assertTrue(emptyReadOnlyMap.isEmpty())
        assertEquals(3, readOnlyMap.size)
    }

    @Test
    fun `access a map entry`() {
        val map = mutableMapOf("id1" to 1, "id2" to 2, "id3" to 3)
        map["id3"] = 10
        assertEquals(2, map["id2"])
        assertEquals(10, map["id3"])
    }

    @Test
    fun `traverse a map`() {
        val map = mapOf("John" to 30, "Joe" to 45, "Peter" to 25)
        val names = mutableListOf<String>()
        val ages = mutableListOf<Int>()
        for ((k, v) in map) { // k and v can be any convenient names, such as name and age
            names.add(k)
            ages.add(v)
        }
        assertEquals(3, names.size)
        assertEquals(3, ages.size)
        assertEquals("Joe", names[1])
        assertEquals(45, ages[1])
    }

    @Test
    fun `iterate over range`() {
        val results = MutableList(5) { mutableListOf<Int>() } // initialized with 4 empty lists
        for (x in 1..10) results[0].add(x) // closed range: includes 10
        for (x in 1 until 10) results[1].add(x) // half-open range: does not include 10
        for (x in 2..10 step 2) results[2].add(x)
        for (x in 10 downTo 1) results[3].add(x)
        (1..10).forEach { results[4].add(it) }
        assertEquals(10, results[0].last())
        assertEquals(9, results[1].last())
        assertEquals(2, results[2][0])
        assertEquals(4, results[2][1])
        assertEquals(10, results[2].last())
        assertEquals(10, results[3].first())
        assertEquals(1, results[3].last())
        assertEquals(1, results[4].first())
        assertEquals(10, results[4].last())
    }

    @Test
    fun `extension functions`() {
        fun String.spaceToAsterisk() = this.replace(' ', '*')
        assertEquals("My*name*is*John!", "My name is John!".spaceToAsterisk())
    }

}
