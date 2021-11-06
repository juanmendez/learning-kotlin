import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

@ExperimentalStdlibApi
@Suppress("UNUSED_VARIABLE")
class Demo {
    @Test
    fun `testing sealed classes`() {
        assertEquals(guessMyType(object: Triangle{}), "Triangle")
        assertNull(guessMyType(object: Rectangle{}))
    }

    @Test
    fun `working with value classes a la Freezed`() {
        val freezedPolygon = FreezedPolygon(object : Triangle {})

        // this won't work..
        // freezedPolygon.item = object : Rectangle

        val unfreezedPolygon = UnfreezedPolygon(object: Triangle {})
        unfreezedPolygon.item = object : Rectangle {}
        assert(unfreezedPolygon.item is Rectangle)
    }

    private fun guessMyType(item:Polygon): String? {
        return when(item) {
            is Triangle -> {
                "Triangle"
            }
            else -> null
        }

    }

    @Test
    fun `making use of non-exhaustive whens`() {
        // Non exhaustive 'when' statements on Boolean will be prohibited in 1.7,
        // add 'false' branch or 'else' branch instead
        val y: Boolean = false
        when (y) {
            true -> println("true")
        }
    }

    @Test
    fun `making use of magic list and map creation`() {
        // see @ExperimentalStdlibApi above
        val list = buildList {
            add("a")
            add("b")
            set(1, null)
            val x = get(1)
            if (x == null) {
                set(1, 1)
            }
        }

        print(list)

        val map = buildMap {
            put("a", 1)
            put("b", 1.1)
            put("c", 2f)
        }

        print(map)
    }
}