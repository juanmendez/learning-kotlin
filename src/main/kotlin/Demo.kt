/**
 * Created by musta on 10/23/2017.
 */

data class Band( var name:String = "", var album:String ="", var year:Int = 0 )

fun Band.isReal()=album.isNotEmpty() || name.isNotEmpty() || year > 0