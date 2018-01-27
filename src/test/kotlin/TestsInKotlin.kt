
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Created by musta on 10/23/2017.
 */
class Tests {
    lateinit var bands:List<Band>

    @Before
    fun `before`(){
        val str = "./raw/great_eighties_albums.csv"
        val file = File(str)

        assertTrue(file.exists())

        bands = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map { Band().apply { //map each col to band attribute
                    name = it[0]
                    album = it[1]
                    year = it[2].toInt()
                }} //0. mapping turned initial list into List<Band>

        assertTrue( bands.isNotEmpty() )
    }


    @Test
    fun `filterTest`() {

        var aereosmiths = bands.filter { it.name.contains( Regex( "Aerosmith" )) }
        assertEquals( aereosmiths.size, 2 )
    }

    @Test
    fun `predicateTest`(){
        //all albums are in the 80's
        assertTrue( bands.all { it.year.toString().contains( Regex("198")) } )

        //at least one band's name is Metallica
        assertTrue( bands.any({it.name.equals("Metallica")}) )
        assertTrue( bands.any( { predicateByBandName(it, "Aerosmith")}) )

        //there is no band called Nickelback
        assertTrue( bands.none({it.name.equals("Nickelback")}))

        //two albums belong to Aerosmith
        assertEquals( bands.count({ it.name.equals("Aerosmith")}), 2 )
    }

    //custom predicates, help elaborate reusable predicates rather than being inline
    fun predicateByBandName(band:Band, name:String )=band.name.equals( name )

}
