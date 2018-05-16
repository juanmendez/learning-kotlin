@file:JvmName("Utils")
@file:JvmMultifileClass

class Application(val name:String){

    //it can also implement an interface
    companion object {

        @JvmField
        val staticFieldSaysHi = "Hi, staticFieldSaysHi"


        //static Application createApp(String name)
        @JvmStatic //without this in Java reads as Application.Companion.createApp
        fun createApp(name:String)=Application(name)

        //static Activity createAppAndActivity(String appName, String activityName)
        @JvmStatic
        fun createAppAndActivity(appName:String, activityName:String):Activity {
            val application = Application(appName)

            return application.createActivity(activityName)
        }
    }

    @JvmField
    val fieldSaysHi = "Hi, from fieldSaysHi"

    fun createActivity(activityName:String):Activity=Activity(activityName, this)

    @JvmOverloads
    fun tryOverloads(param1:String, param2:String, param3:Int = 0, param4:Int = 5){}
}

object Holder{
    val apps = arrayListOf<Application>()
    val nicknames = listOf<String>()
}

open class Activity(val name:String, val application: Application)

fun Activity.greeting():String ="hi, from your App... ${application.name} with love to my beloved {$name}(activity)"