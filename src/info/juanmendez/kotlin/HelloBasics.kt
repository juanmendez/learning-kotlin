package info.juanmendez.kotlin

/**
 * Created by musta on 8/31/2017.
 *
 * I have never done anything with Kotlin, so this is my first lines of code. :)
 */

import java.util.*

fun sum(a: Int, b: Int): Int {
    return a + b
}


fun arrayDemo(){
    val s = "hello world";

    var long = """
               |This string is
               |made of several lines
              """.trimMargin()


    val str = "The value kept is $s which has a length of ${s.length}"


}


fun conditions(){
    val a = 5
    val b = 3

    //condition is used like a lambda, whose condition result is returned.
    var max = if( a>b ) a else b

    /**
     * returned condition value must be based on an else. Meaning there cannot
     * be a null value.
     */
    max = if( a > b ){
        print("choosing a")
        a
    }else{
        print( "choosing b" )
        b
    }

    //fancy switch statement.. ;)
    when( a ){
        2 -> println( "equals 2")
        3 -> println( "equals 3")
        in 4..5-> println( "number on top of 3")
        else -> println( "some other number")
    }

    //use it as if it were a condition
    var x:Int = 1

    when {
        x%2==0 -> print("x is even")
        x%2!=0 -> print("x is odd")
        else -> print("x is funny")
    }
}

fun loops(){

    //labels are ways to reference our loops, so we can break here from inner loop
    myTopLoop@ for( i in 1..100 ){
        for( j in 1..10 ){
         if( j == i ){
             break@myTopLoop
         }
        }
    }
}
