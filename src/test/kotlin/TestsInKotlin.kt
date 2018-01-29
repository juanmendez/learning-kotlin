
import junit.framework.Assert.assertNotSame
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test
import java.io.File

/**
 * Created by musta on 10/23/2017.
 */
class Tests {

    /**
     * In Java we don't have this option..
     * but in Kotlin List<Band> won't allow to update
     * therefore we need a writable collection.
     */
    lateinit var bands:MutableList<Band?>

    @Test
    fun `dealingWithNullableAlbums`(){
        val str = "./raw/SomeNullableAlbums.csv"
        val file = File(str)

        Assert.assertTrue(file.exists())

        val thoseBands = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map {

                        Band().apply {
                            //map col:List<String> to each attribute in band
                            name = it[0]
                            album = it[1]
                            year = it[2].let {
                                if( it.isNotEmpty() )
                                    it.toInt()
                                else
                                    0
                            }
                        }
                }

        bands = arrayListOf()

        for( band in thoseBands ){
            if( band.name.isNotEmpty() ){
                bands.add( band )
            }else{
                bands.add( null )
            }
        }


        Assert.assertTrue(bands.isNotEmpty())
    }


    @Test
    fun `simplerExample`(){
        val str = "./raw/SomeNullableAlbums.csv"
        val file = File(str)

        Assert.assertTrue(file.exists())

        bands = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map {

                    if( (it[0]+it[1]+it[2]).trim().isNotEmpty()  ){
                        Band().apply {
                            //map each col to band attribute
                            name = it[0]
                            album = it[1]
                            year = it[2].toInt()
                        }
                    }else{
                        null
                    }
                }.toMutableList() //mutable must get assigned a mutable list


        Assert.assertTrue(bands.isNotEmpty())

        //this is how to pass list as List<Band> argument
        assertTrue( bands.filterNotNull() is List<Band> )

        //this is ok, if we pass it as an argument to a method
        assertTrue( bands is List<Band?>)

        //we want to protect our own list allowing others to have a copy of ours
        val theOtherList = bands.toMutableList()
        theOtherList.add( Band() )

        assertNotSame( theOtherList, bands )
        assertNotSame( theOtherList.size, bands.size )
    }
}
