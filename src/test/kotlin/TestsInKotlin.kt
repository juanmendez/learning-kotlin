
import info.juanmendez.kotlin.Album
import info.juanmendez.kotlin.Band
import info.juanmendez.kotlin.OkAlbum
import info.juanmendez.kotlin.interop.InteropAlbum
import info.juanmendez.kotlin.interop.InteropBand
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
        band.addAlbum( OkAlbum({ name -> "Spaguetti Incident" }).album )

        //FunAlbum is a Kotlin class, and what's above doesn't work.
        //Instance is built from a constructor passing single lambda method
        band.addAlbum( FunAlbum(Album({ name -> "Chinese Democracy" })).album )

        //This is treated as a redundant SAM constructor, but works like the one above
        band.addAlbum( GreatAlbum().apply { setName("User Your Illusion I") })

        printOutBand( band )
        printOutBand( null )
    }


    @Test
    fun `check interoperability from Java to Kotlin`(){
        val band = InteropBand()

        //tells us you cannot assign null to a non-nullable value
        //band.name = null


        val album = InteropAlbum()

        //this says: you cannot assign a null value to a non-value attribute setName( @NotNull String name)
        //album.name = null

        //java.lang.IllegalStateException: @NotNull method InteropAlbum.getName must not return null
        //println( album.name )

        //getNameOrNull() tells value can be null
        println( album.nameOrNull ?: "there is no album name assigned" )

        //next line requires ?, as getAlbums() can be nullable
        println( "band has ${band.albumList?.size ?: 0 } albums" )
    }
}