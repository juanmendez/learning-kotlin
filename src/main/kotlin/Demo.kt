/**
 * Created by musta on 10/23/2017.
 */
interface Animal{
    fun getName():String
    fun getLegs():Int
}


class Human(private val name:String, private val legs:Int=0 ):Animal{

    override fun getName(): String {
        return name
    }

    override fun getLegs(): Int {
        return legs
    }
}

fun describeAnimal( animal: Animal ):String{
    return "Animal's name is ${animal.getName()} and has ${animal.getLegs()} leg(s)"
}