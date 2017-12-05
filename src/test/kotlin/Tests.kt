
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by musta on 10/23/2017.
 */
class Tests {

    @Test
    fun personTest(){
       val juan = Person( "Juan" )

        assertEquals( juan.name, "Juan")
        assertTrue( juan.cats.isEmpty() )

        juan.cats.add( Cat("Felipe", 8 ))
        juan.cats.add( Cat( "Lynda", 9 ))
        juan.cats.add( juan.cats[juan.cats.lastIndex].copy())


        assertEquals( juan.cats.size, 3 )

        juan.cats.sortBy { it.age }

            for( cat in juan.cats ){
                println( cat.age )
            }


        juan.cats.sortBy { it.name }

        for( cat in juan.cats ){
            println( "Cat name is ${cat.name}" )
        }


        /**
         * instances of data classes can be equal if their members match with another instance's.
         * if not using data class, then that is always false
         */
        assertNotEquals(juan.cats[0], juan.cats[1])
        assertEquals(juan.cats[1], juan.cats[2])

        //iterating with index
        for( (index,cat) in juan.cats.withIndex() ){
            println( "${cat.name} at $index")
        }


        var cats = HashMap<String,Cat>()
        cats["felipe"] = juan.cats[0]
        cats["lynda"] = juan.cats[1]
        cats["lynda2"] = juan.cats[2]

        for( (alias,cat) in cats ){
            println( "$alias:${cat.name}")
        }

        assertEquals( getOldestCat(juan.cats), cats.get("lynda2") )

        //on purpose, make a call being explicit about parameter
        assertEquals( getOldestCat(juan.cats),getOldestCat( cats=juan.cats) )


        //overloading, we ommit age..
        val chino = Cat( name = "Chino" )
        juan.cats.add( chino )


        assertEquals( juan.getYoungestCat(), chino )
    }
}