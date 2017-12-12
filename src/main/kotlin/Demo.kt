/**
 * Created by musta on 10/23/2017.
 */
open class Language(val name: String, val yearCreated:Int){

}


class ScriptingLanguage( _name:String, _yearCreated:Int, val version:Long = 0):Language( _name, _yearCreated){



}