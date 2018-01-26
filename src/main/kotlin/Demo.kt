/**
 * Created by musta on 10/23/2017.
 */

//Java reads file class name as Utils
@file:JvmName("Utils")

class Application( val name:String ){

    fun createActivity( activityName:String ):Activity=Activity( activityName, this )

    //it can also implement an interface
    companion object {

        //static Application createApp( String name )
        @JvmStatic //without this in Java reads as Application.Companion.createApp
        fun createApp( name:String)=Application( name )

        //static Activity createAppAndActivity( String appName, String activityName )
        @JvmStatic
        fun createAppAndActivity( appName:String, activityName:String):Activity{
            val application = Application( appName )

            return application.createActivity( activityName )
        }
    }
}


class Activity( val name:String, val application: Application )

fun Activity.greeting()="hi, from your App... ${application.name} with love to my beloved {$name}(activity)"


object Holder{
    val apps = arrayListOf<Application>()
}