
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Created by musta on 10/23/2017.
 */
class Tests {

    @Test
    fun testInheritance(){

        val js = ScriptingLanguage( "JavaScript", 1995 )

        //this is goofy and is always true: replaces instanceof
        assertTrue( js is Language )
        assertEquals( js.version , 0 )


        /**
         * Here is how we implement a new instance of abstract class Framework
         */
        val angular = object: Framework("Angular", 4.0f ) {
            override fun getTheme():String="""
                |$name=>One framework.
                |Mobile & desktop!"
                """.trimMargin()
        }


        val react = object:Framework("ReactJS", 1.0f ){
            override fun getTheme(): String="$name=>A JavaScript library for building user interfaces"

        }


        val vue = object:Framework("VueJS", 1.0f ){
            override fun getTheme(): String="""$name=>The Progressive
                |JavaScript Framework""".trimMargin()

        }


        println( angular.getTheme() )
        println( react.getTheme() )
        println( vue.getTheme() )
    }
}