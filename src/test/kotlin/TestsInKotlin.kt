
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test

/**
 * Created by Juan Mendez on 10/23/2017.
 * Playing with delegated classes
 *
 * @see kotlinlang.org/docs/reference/delegation.html
 */
class Tests {
    val person = Person("Peter", 10)
    lateinit var delegatedOnce: DelegatedOnce
    lateinit var delegatedTwice: DelegatedTwice

    @Before
    fun `before`(){
        delegatedOnce = DelegatedOnce( person )
        delegatedTwice = DelegatedTwice(delegatedOnce)
    }

    @Test
    fun `testDelegation`() {

        //English
        println( person.message() )

        delegatedOnce.name = "Pedro"
        assertEquals( delegatedOnce.age, 10 )
        assertEquals( person.name, "Pedro" )
        assertNotEquals(delegatedOnce, person )

        //Spanish
        println( delegatedOnce.message() )
    }

    @Test
    fun `testSecondDelegation`(){
        person.name = "Piotr"
        assertEquals( delegatedOnce.name, "Piotr" )
        assertEquals( delegatedTwice.name, "Piotr" )

        //Polish
        println( delegatedTwice.message() )
    }
}
