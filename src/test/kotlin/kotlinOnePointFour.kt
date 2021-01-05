import info.juanmendez.kotlin.Album
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class Tests {
    @Test
    fun `checking SAM`() {
        val album = Album {}
        val sammy = Sammy {}

        album.setName("Best of Both Worlds")
        sammy.setName("Sammy Hagar")
    }

    private fun hello(var1: String, var2: String, var3: String,): String {
        return "$var1 $var2 $var3"
    }

    @Test
    fun `checking argument names`() {
        // if changing the order, you need to name all your parameters
        hello(var3 = "hello", var2 = "again", var1 = "and again..")

        // if in order, you can opt to name or not your parameters
        hello("hello", var2 = "again", "and again..")
        hello("hello", "again", var3 = "and again..")

        // trailing commas, just like doing Dart
        hello("hello", "again", "and again..")
    }

    private fun defaultArg(description: String = "hello") = description
    private fun fun2() = "hello world"
    private fun argTest(func: () -> String): String = func()
    private suspend fun suspendArgTest(func: suspend () -> String): String = func()

    @Test
    fun `functional references`() {
        // before we had to pass argText{ fun1() }
        // now we can do argText(fun1)
        val fun1 = { "hello" }
        argTest(fun1)
        argTest(::fun2)
    }


    @Test
    fun `references to functions with default args`() {

        // here we pass function reference which has a default value as an argument
        // therefore (String) -> String, can be accepted as ()-> String to no1:11argTest
        assertEquals("hello", argTest(::defaultArg))
    }

    @Test
    fun `assigning suspended function references`() = runBlocking {
        val result = suspendArgTest(::fun2)
        assertEquals(result, "hello world")
    }

    @Test
    fun `debugging coroutines`() = runBlockingTest {
        /**
         * I was able to place breakpoints in lines 75, and 79
         * There I could confirm the coroutine tracking in the Coroutines panel.
         * Here is a how to trace it https://bit.ly/3gWkjLH
         */
        val myFlow = flow<Int> {
            emit(4)
        }.onStart {
            delay(4000)
        }

        advanceTimeBy(4000)

        myFlow.collect {
            print("item taken $it")
        }
    }

    @Test
    fun `automatic inferring`() {
        val kotlinOnePointThree: Map<String, String?> = mapOf(
            "chicken" to "wings",
            "apple" to "pie",
            "orange" to "crush",
            "pizza" to null,
        )

        val kotlinOnePointFour = mapOf(
            "chicken" to "wings",
            "apple" to "pie",
            "orange" to "crush",
            "pizza" to null,
        )

        assertTrue(kotlinOnePointFour is Map<String, String?>)

        // in Kotlin 1.3, the last expression inside a lambda wasn't smart cast
        // unless you specified the expected type.

        val lambda = {
            var str = if(LocalDateTime.now().second % 2 == 0) {
              "hello"
            } else {
                null
            }

            // without this condition, first assertion fails
            if(str == null) {
                str = "test"
            }

            str
        }

        assert(lambda() is String)
        assert(lambda() is String?)
    }
}