/**
 * Created by musta on 10/23/2017.
 */

//Java reads file class name as Utils
@file:JvmName("Utils")

interface LogCallback{
    fun writeContent( content:String )
}


fun asyncCall( message:String, log:LogCallback ){
    log.writeContent( message )
}

/**
 * second argument is expecting a signature. it requires a
 * method which receives one string argument, and uses Unit or Void as in Java.
 */
fun writeFunNotation( message:String, log:(String)->Unit ){
    log( message )
}

data class Pet( var name:String = "", var age:Int = 0)