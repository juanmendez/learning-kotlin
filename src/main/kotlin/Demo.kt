/**
 * Created by musta on 10/23/2017.
 */

data class Band( var name:String = "", var album:String ="", var year:Int = 0 )

enum class Genre( var alias: String ){
    TRASHMETAL( "Thrash Metal"),
    ROCK( "Rock"),
    HEAVYMETAL("Heavy Metal"),
    CLASSICROCK( "Classic Rock"),
    ALTERNATIVE( "Alternative" )
}