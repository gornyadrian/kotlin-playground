import kotlin.test.*

class CollectionsTest {

    // default implementation of List is ArrayList

    // default implementation of Set is LinkedHashSet - preserves the order of elements insertion,
    // an alternative implementation - HashSet - says nothing about the elements order and requires less memory

    // default implementation of Map is LinkedHashMap - preserves the order of elements insertion,
    // an alternative implementation - HashMap - says nothing about the elements order

    @Test
    fun `iterate over collection`() {
        val fruits = listOf("apple", "banana", "cherry")
        var result = ""
        for (fruit in fruits) {
            result += fruit.first()
        }
        assertEquals("abc", result)
    }

    @Test
    fun `default set (LinkedHashSet) ordering`() {
        val firstSet = setOf(1, 2, 3, 4, 5)
        val secondSet = setOf(5, 4, 3, 2, 1)
        assertEquals(firstSet, secondSet)
        assertEquals(firstSet.first(), secondSet.last())
        assertEquals(firstSet.last(), secondSet.first())
    }

    @Test
    fun `check if collection contains item using in operator`() {
        val animals = setOf("lion", "panda", "panther")
        fun isPandaInSet() = when {
            "panda" in animals -> true
            else -> false
        }
        assertEquals(true, isPandaInSet())
    }

    @Test
    fun `lambda expressions with collections`() {
        val animals = listOf("lion", "panda", "panther", "cheetah", "fish", "pig")
        var result = ""
        animals
            .filter { it.startsWith("p") }
            .sortedBy { it }
            .map { it.uppercase() }
            .forEach { result += it }
        assertEquals("PANDAPANTHERPIG", result)
    }

    @Test
    fun `val with mutable collection`() {
        val numbers = mutableListOf(1, 2, 3, 5)
        numbers.add(10) // OK
        // numbers = mutableListOf(50, 100) - compilation error, you cannot reassign object reference
        assertEquals(10, numbers[4])
    }

    open class Person(val name: String, val age: Int) {
        override fun toString() = name
    }

    class Cyborg(name: String) : Person(name, 1000)

    @Test
    fun `list with inherited items`() {
        val people: List<Person> = listOf(
            Person("John", 45),
            Person("Peter", 25),
            Cyborg("Rob")
        )
        assertEquals("John", people[0].name)
        assertEquals("Rob", people[2].name)
    }

    @Test
    fun `partition of set`() {
        val people: Set<Person> = setOf(
            Person("John", 45),
            Person("Peter", 25),
            Cyborg("Rob")
        )
        val partition = people.partition { it.age > 26 }
        assertContains(partition.first.map { it.age }, 45)
        assertContains(partition.first.map { it.age }, 1000)
        assertContains(partition.second.map { it.age }, 25)
    }

    @Test
    fun `filter items of specific type`() {
        val people: Set<Person> = setOf(
            Person("John", 45),
            Person("Peter", 25),
            Cyborg("Rob")
        )
        val cyborgs = people.filterIsInstance<Cyborg>()
        assertIs<Collection<Cyborg>>(cyborgs)
        assertEquals(1, cyborgs.size)
    }

    @Test
    fun `other collection functions`() {
        val people: List<Person> = listOf(
            Person("John", 45),
            Person("Peter", 25),
            Cyborg("Rob")
        )
        val joined = people.joinToString(
            separator = " | ",
            prefix = "<- ",
            postfix = " ->"
        ) {
            "[${it.name}, ${it.age}]"
        }
        assertEquals(1070, people.sumOf { it.age })
        assertEquals(25, people.minOfOrNull { it.age })
        assertEquals(1000, people.maxOfOrNull { it.age })
        assertEquals(12, people.sumOf { it.name.length })
        assertEquals(1, people.count { it.name.length == 3 })
        assertEquals("<- [John, 45] | [Peter, 25] | [Rob, 1000] ->", joined)
    }

    @Test
    fun `mutable list`() {
        val colors = mutableListOf("red", "green")
        val fruits = listOf("banana", "orange")
        colors.add("blue")
        colors.removeFirst()
        // fruits.add("cherry") - compilation error, fruits list is not mutable
        assertEquals(2, colors.size)
        assertEquals("blue", colors[1])
        assertEquals("green", colors[0])
    }

    @Test
    fun `items subtraction`() {
        val colors = mutableListOf("red", "green", "blue", "yellow", "purple")
        val colorsToRemove = setOf("red", "green", "yellow")
        colors -= colorsToRemove
        assertEquals(2, colors.size)
        assertEquals("blue", colors.first())
        assertEquals("purple", colors.last())
    }

    @Test
    fun `map collection`() { // Map<K, V> is not an inheritor of the Collection interface
        val numbersMap = mapOf(
            "key1" to 1,
            "key2" to 22,
            "key3" to 1,
            "key4" to 15,
        )
        fun isNumberInMap(num: Int) = num in numbersMap.values
        fun isNumberInMapAlternative(num: Int) = numbersMap.containsValue(num)
        assertEquals(22, numbersMap["key2"])
        assertEquals(true, isNumberInMap(15))
        assertEquals(true, isNumberInMapAlternative(15))
    }

    @Test
    fun `comparing maps`() {
        val numbersMap = mapOf(
            "key1" to 1,
            "key2" to 22,
            "key3" to 1,
            "key4" to 15,
        )
        val numbersMap2 = mapOf(
            "key3" to 1,
            "key1" to 1,
            "key2" to 22,
            "key4" to 15,
        )
        val numbersMap3 = mapOf(
            "key1" to 1,
            "key2" to 22,
            "key3" to 1,
            "key4" to 1500,
        )
        assertEquals(numbersMap, numbersMap2) // two maps containing the equal pairs are equal regardless of the pair order
        assertNotEquals(numbersMap, numbersMap3)
    }

    @Test
    fun `mutable map`() {
        val numbersMap = mutableMapOf(
            "key1" to 1,
            "key2" to 22,
            "key3" to 1,
            "key4" to 15,
        )
        numbersMap.put("key5", 5)   // put new key-value pair
        numbersMap["key6"] = 6      // put new key-value pair
        numbersMap["key1"] = 11     // update existing key-value pair
        assertEquals(5, numbersMap["key5"])
        assertEquals(6, numbersMap["key6"])
        assertEquals(11, numbersMap["key1"])
    }

}
