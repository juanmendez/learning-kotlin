import org.junit.Test

/**
 * Created by musta on 10/23/2017.
 */
class Tests {
    @Test
    fun `treatingNulls`(){

        val band = Band()
        band.name = "Guns n' Roses"

        band.addAlbum( { band.name + " lies" } )
        band.addAlbum( { "User Your Illusion II" })

        //OkAlbum is a Java class, and works similar to just adding the album above
        band.addAlbum( OkAlbum( {name->"Spaguetti Incident"} ).album )

        //FunAlbum is a Kotlin class, and what's above doesn't work.
        //Instance is built from a constructor passing single lambda method
        band.addAlbum( FunAlbum( Album({ name -> "Chinese Democracy" }) ).album )

        //This is treated as a redundant SAM constructor, but works like the one above
        band.addAlbum( GreatAlbum().apply { setName("User Your Illusion I") })

        printOutBand( band )
        printOutBand( null )
    }
}