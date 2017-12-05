/**
 * Created by musta on 10/23/2017.
 */


//create a person class with a given constructor..
class Person(val name:String){
    val cats = arrayListOf<Cat>()

}

data class Cat( val name:String, val age:Int=0 ){
}


//single function.. no body
fun getOldestCat( cats:List<Cat> ):Cat= cats.sortedByDescending { it.age }[0]


fun getYoungestCat( cats:List<Cat> ):Cat= cats.sortedBy { it.age }[0]