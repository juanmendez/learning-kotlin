
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Juan Mendez on 10/23/2017.
 * Playing with delegated classes
 *
 * @see kotlinlang.org/docs/reference/delegation.html
 */
class HomeDelegationTest {

    @Test
    fun `testHome`(){
        val home = RealHome( "00 N. Michingan Avenue")
        val delegatedHome = DelegatedHome( home )

        assertEquals( home.address, delegatedHome.address )
        assertEquals( home.describe(), delegatedHome.describe() )

        delegatedHome.address = "100 S. Wabash Avenue"
        assertEquals( home.address, "100 S. Wabash Avenue" )
    }
}