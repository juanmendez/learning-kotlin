
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by musta on 10/23/2017.
 */
class Tests {

    @Test
    fun `making lambda instance of interface`() {
        asyncCall( "hello", object:LogCallback{
            override fun writeContent( msg:String ){
                println( "Your msg $msg")
                assertEquals( msg, "hello" )
            }
        })
    }


    /**
     * This time we are passing a lambda which matches the signature of the second argument.
     * It expects a method which accepts a string, but is Void or Unit in Kotlin
     */
    @Test
    fun `functional notation`(){
        writeFunNotation( "hello world", { s-> assertEquals(s, "hello world") } )

        //also we can do it in dsl way
        writeFunNotation( "hello world" ) { s-> assertEquals(s, "hello world") }

        //why not use it?, therefore we don't even apply argument
        writeFunNotation( "hello world" ) { assertEquals( it, "hello world") }
    }


    /**
     * Compared with Java, in Kotlin you can have an external variable not declared final
     */
    @Test
    fun `no final external var`(){

        var allLetters = ""

        writeFunNotation( "a", {allLetters+=it})
        writeFunNotation( "b", {allLetters+=it})
        writeFunNotation( "c", {allLetters+=it})
        writeFunNotation( "d", {allLetters+=it})

        assertEquals( allLetters, "abcd" )
    }

    @Test
    fun `learn with`(){


        var max = Pet()

        with( max ){
            age = 2
            name = "max"
        }


        var chino = Pet()

        with( chino ){
            age = 4
            name = "Chino"
        }

        assertEquals( max.age, 2 )

        /**
         * In Java, we do this by chaining setters having the class object returned.
         * Here apply works a lot easier by simply assigning values to each
         * of its members
         */
        var felipe = Pet().apply {
            age = 4
            name = "Felipe"
        }
    }


}
