/**
 * Created by musta on 10/23/2017.
 */
open class Language(val name: String, val yearCreated:Int){

    open fun hello():String="Hello $name"

}


class ScriptingLanguage( _name:String, _yearCreated:Int, val version:Long = 0):Language( _name, _yearCreated){

    override fun  hello():String=super.hello() + ":$yearCreated"

}


abstract class Framework( val name: String, val version: Float){
    abstract fun getTheme():String
}

