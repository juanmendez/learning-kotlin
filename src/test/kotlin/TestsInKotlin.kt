
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

/**
 * Created by musta on 10/23/2017.
 */
class Tests {
    val genreMap = HashMap<Genre, MutableList<Band>>()

    @Before
    fun `before`(){
        val str = "./raw/rock_albums.csv"
        val file = File(str)

        assertTrue(file.exists())

        file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .forEach {
                    val list = it.split(",")

                    //searching for Genre based on its alias attribute
                    val genre:Genre? = Genre.values().filter { it.alias.equals(list[3]) }.first()

                    genre?.let {
                        if( genreMap[it] == null )
                            genreMap[it] = mutableListOf()

                        genreMap[it]?.add( Band().apply {
                            name = list[0]
                            album = list[1]
                            year = list[2].toInt()
                        })
                    }
                }
    }


    @Test
    fun `filterTest`() {
        //three albums by Bon Jovi, and Def Leppard
        assertEquals( genreMap[ Genre.ROCK]?.size, 3 )
        println( genreMap[Genre.ROCK]?.distinctBy { it.name })

        //lets see if Aereosmith is in classic rock,,
        assertTrue( genreMap[Genre.CLASSICROCK]?.any { it.name.equals("Aerosmith") } == true )

        //lets see if Black Sabbath is in Heavy Metal,
        assertTrue( genreMap[Genre.HEAVYMETAL]?.any { it.name.equals("Black Sabbath") } == true )

        //lets see if Megadeth is in Thrash Metal,
        assertTrue( genreMap[Genre.TRASHMETAL]?.any { it.name.equals("Megadeth") } == true )
    }
}
