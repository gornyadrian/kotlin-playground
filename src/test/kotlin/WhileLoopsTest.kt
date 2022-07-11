import kotlin.test.Test
import kotlin.test.assertEquals

class WhileLoopsTest {

    @Test
    fun `simple while loop`() {
        val items = listOf(5, 10, 15)
        var index = 0
        var sum = 0
        while (index < items.size) {
            sum += items[index]
            index++
        }
        assertEquals(30, sum)
    }

    @Test
    fun `simple do-while loop`() {
        val items = listOf(5, 10, 20)
        var index = 0
        var sum = 0
        do {
            sum += items[index]
            index++
        } while (index < items.size)
        assertEquals(35, sum)
    }

}
