import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class Operations {
    internal class Word {
        val value = 0
    }

    val newName: Int by Word()::value

    @Deprecated("Use 'newName' instead", ReplaceWith("newName"))
    val oldName: Int by this::newName

    @Test
    fun `testing shuffle for random`() {
        val numbers = (0 until 10).toList()

        // ex: [8, 9, 5, 6, 1, 3, 7, 2, 4, 0]
        print(numbers.shuffled())
    }

    @Test
    fun `new indexed operations`() {
        val numbers = (0 until 10).toList().shuffled()

        numbers.forEachIndexed { i, value ->
            println("[$i] $value")
        }

        val result = numbers.mapIndexed { i, value ->
            "[$i] $value"
        }

        println(result)
    }

    @Test
    fun `delegated by another property`() {
        assertEquals(newName, 0)
        assertEquals(oldName, 0)
    }
}