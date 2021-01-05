import info.juanmendez.kotlin.Album
import info.juanmendez.kotlin.Band

data class FunAlbum( val album: Album)

class GreatAlbum: Album {
    lateinit var theName:String

    override fun setName(name: String?) {
        theName = name ?: ""
    }
}

fun printOutBand( band: Band? ){

    //${band?.name} = band!=null ? band.name?:''
    //?:false = band.albums!=null ? band.albums.isNotEmpty():false
    println( "${band?.name} has albums? " + ( band?.albums?.isNotEmpty() ?: false ) )


    /**
     * band?.albums?.let {} : if( band!= null && band.albums!=null ){}
     */
    band?.albums?.let {

        for( album in it ){

            /**
             * as?:  if( album intanceof GreatAlbum){ greatAlbum = album }
             * ()?.theName = greatAlbum!=null?greatAlbum.theName?""
             */
            println( "It's a great album: " + (album as? GreatAlbum)?.theName )
        }
    }
}