
import org.junit.Assert.assertTrue
import org.junit.Test
import java.io.File

/**
 * Created by musta on 10/23/2017
 */
class Tests {

    @Test
    fun `emulate asynchronous call to get a collection of bands`(){


        /**
         * Imagine there is an asynchronous callback from getNonNullableList
         * So this works...
         */
        `get real and empty bands`( {realBands, emptyBands->
            run {
                assertTrue( realBands.isNotEmpty() )
                assertTrue( emptyBands.isNotEmpty() )
            }
        })
    }

    /**
     * callback is a method, and not a SAM representation. That's why we use the keyword inline
     * ensuring to be expected as such. This is a good thing to do to improve performance but should be used in
     * lambdas directly used which are not kept in memory. Example: collections use inlines for filtering, and actions
     *
     * Note: removing inline only affects performance
     */
    private inline fun `get real and empty bands`( callback:(List<Band>, List<Band> )->Unit ) {

        val str = "./raw/SomeNullableAlbums.csv"
        val file = File(str)

        assertTrue(file.exists())

        var bands:List<Band> = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map {

                    if( (it[0]+it[1]+it[2]).trim().isNotEmpty()  ){
                        Band().apply {
                            //map each col to band attribute
                            name = it[0]
                            album = it[1]
                            year = it[2].let {
                                if( it.matches( Regex("\\d+")))
                                    it.toInt()
                                else
                                    0
                            }
                        }
                    }else{
                        Band()
                    }
                }


        callback(  bands.filter {  it.isReal() }, bands.filter { !it.isReal() } )
    }
}