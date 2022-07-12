package basicSyntax

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs

class ClassesTest {

    class Foo {
        // Empty constructor is available automatically.
    }

    @Test
    fun `classes without properties`() {
        val bar = Foo()
        assertIs<Foo>(bar)
    }

    class Human(val name: String, val age: Int) {
        // The default constructor with listed parameters is available automatically.
    }

    @Test
    fun `classes with properties`() {
        val human1 = Human("John", 50)
        // val human2 = Human() <- Compilation error, no default empty constructor is provided for the class.
        assertEquals("John", human1.name)
        assertEquals(50, human1.age)
    }

    class Square(val sideLength: Double) {
        // Properties can be listed in the body of the class.
        val area = sideLength * sideLength
    }

    @Test
    fun `classes with properties in body`() {
        val square1 = Square(5.0)
        assertEquals(5.0, square1.sideLength)
        assertEquals(25.0, square1.area)
    }

    // Class marked as 'open' is inheritable.
    open class Animal

    // Inheritance between classes is declared by a colon.
    class Dog(val age: Int): Animal() {
        // body
    }

    @Test
    fun `inheritance between classes`() {
        assertIs<Dog>(Dog(10))
        assertIs<Animal>(Dog(10))
    }

}
