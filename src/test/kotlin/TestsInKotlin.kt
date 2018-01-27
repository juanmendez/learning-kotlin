import org.junit.Test

/**
 * Created by musta on 10/23/2017.
 */
class Tests {
    @Test
    fun `playingWithSAM`(){

        val band = Band()
        band.addAlbum( Album { name -> "Appetite for destruction" })

    }
}
