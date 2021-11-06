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

/**
 * As a refresh, sealed classes hide if being tried to access outside their packages
 */
sealed interface Polygon
interface Rectangle: Polygon
interface Triangle: Polygon

/**
 * In Dart we use Freezed to make inmutable classes which is already found using data classes.
 * The difference is those objects can be modified at runtime having vars, here if you try to
 * use vars, then you get the following error
 * `Value class primary constructor must have only final read-only (val) property parameters`
 */
@JvmInline
value class FreezedPolygon(val item: Polygon)

data class UnfreezedPolygon(var item: Polygon )