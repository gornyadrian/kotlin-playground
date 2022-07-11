import kotlin.test.*

class NullableValuesTest {

    @Test
    fun `simple nullable value example`() {
        var num1: Int? = 10
        var num2: Int = 20
        num1 = null
        // num2 = null <- compilation error: null can not be a value of a non-null type Int
        assertNull(num1)
        assertNotNull(num2)
    }

    @Test
    fun `operation on nullable values`() {
        val num1: Int? = 10
        val num2: Int? = 20
        var result = 0
        // result = num1 + num2 <- compilation error: variables may hold nulls
        if (num1 != null && num2 != null) {
            result = num1 + num2 // <- OK: after null check variables are automatically cast to nun-nullable
        }
        assertEquals(30, result)
    }

    @Test
    fun `checking for null in conditions using if-else`() {
        val name1 = "John"
        val name2: String? = null
        fun getLength(word: String?): Int = if (word != null) word.length else -1
        assertEquals(4, getLength(name1))
        assertEquals(-1, getLength(name2))
    }

    @Test
    fun `elvis operator`() {
        val name1 = "John"
        val name2: String? = null
        fun getLength(word: String?): Int = word?.length ?: -1 // if word is not null, use it, otherwise use -1
        assertEquals(4, getLength(name1))
        assertEquals(-1, getLength(name2))
    }

    class Head(var name: String)
    class Department(var head: Head?)
    class Job(var department: Department?)
    class Person(var name: String?, var age: Int?, var job: Job?)

    @Test
    fun `elvis operator with return and throw expressions`() {
        // throw and return are expressions, they can be used on the right-hand side of the Elvis operator
        val person1 = Person("John", 30, null)
        val person2 = Person(null, 30, null)
        fun introducePerson(person: Person): String? {
            val name = person.name ?: return null
            val age = person.age ?: throw IllegalArgumentException("age expected")
            return "name: $name, age: $age"
        }
        assertEquals("name: John, age: 30", introducePerson(person1))
        assertNull(introducePerson(person2))
    }

    @Test
    fun `safe calls`() {
        val name1 = "John"
        val name2: String? = null
        fun getLength(word: String?): Int? = word?.length // returns word.length if word is not null, and null otherwise
        assertEquals(4, getLength(name1))
        assertNull(getLength(name2))
    }

    @Test
    fun `safe call chains`() {
        val headOfDepartment = Head("Peter")
        val department = Department(headOfDepartment)
        val job = Job(department)
        val person = Person("John", 30, job)
        val unemployedPerson = Person("Mark", 40, null)
        assertNotNull(person.job?.department?.head?.name) // such a chain returns null if any of the properties in it is null
        assertNull(unemployedPerson.job)
        assertNull(unemployedPerson.job?.department)
        assertNull(unemployedPerson.job?.department?.head)
        assertNull(unemployedPerson.job?.department?.head?.name)
    }

    @Test
    fun `safe call operator with let`() {
        val people: List<Person?> = listOf(
            Person("John", 30, null),
            null,
            null,
            Person("Mark", 40, null),
            null
        )
        val existingPeople = mutableListOf<Person>()
        for (person in people) {
            person?.let { existingPeople.add(it) } // adds existing people and ignores null
        }
        assertEquals(2, existingPeople.size)
        assertEquals("John", existingPeople[0].name)
        assertEquals("Mark", existingPeople[1].name)
    }

    @Test
    fun `left side safe calls`() {
        val headOfDepartment = Head("Peter")
        val department = Department(headOfDepartment)
        val job = Job(department)
        val person: Person? = Person("John", 30, job)
        val unemployedPerson = Person("Mark", 40, null)
        person?.job?.department?.head?.name = "Joe"
        // if one of the receivers in the safe calls chain is null,
        // the assignment is skipped and the expression on the right is not evaluated at all
        unemployedPerson?.job?.department?.head?.name = "Andrew"
        assertEquals("Joe", person?.job?.department?.head?.name)
        assertNull(unemployedPerson?.job?.department?.head?.name)
    }

    @Test
    fun `not-null assertion operator`() {
        // !! operator converts any value to a non-null type
        // and throws an exception if the value is null
        val text: String? = "abc"
        val emptyVar: String? = null
        val textLen: Int = text!!.length
        // val emptyLen: Int = emptyVar!!.length <- NullPointerException
        val emptyLen: Int? = emptyVar?.length
        assertEquals(3, textLen)
        assertNull(emptyLen)
    }

    @Test
    fun `safe casts`() {
        val num1: Number = 10
        val num2: Number = 10.2
        val num3: Int = num1 as Int
        val num4: Double = num2 as Double
        // val num5: Double = num1 as Double <- ClassCastException
        val num5: Double? = num1 as? Double // safe casts return null if the attempt of casting was not successful
        assertEquals(num3, num1)
        assertIs<Int>(num3)
        assertEquals(num2, num4)
        assertIs<Double>(num4)
        assertNull(num5)
    }

    @Test
    fun `filtering non-null list elements`() {
        val nullableList: List<Int?> = listOf(1, 2, null, 4, null, 6, 7, 8, 9, null)
        val list: List<Int> = nullableList.filterNotNull()
        assertEquals(7, list.size)
        assertEquals(1, list[0])
        assertEquals(2, list[1])
        assertEquals(4, list[2])
        assertEquals(6, list[3])
        assertEquals(7, list[4])
        assertEquals(8, list[5])
        assertEquals(9, list[6])
    }

}