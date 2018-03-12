/**
 * Created by Juan Mendez on 10/23/2017.
 */

interface IPerson {
    var age:Int
    var name:String
    fun message():String
}

class Person(override var name:String, override var age: Int ): IPerson{

    override fun message()="$name is $age years old"
}

class DelegatedOnce(person: IPerson) : IPerson by person{

    //if you remove this one, then Person's message is used instead
    override fun message()="$name tiene $age años"
}

class DelegatedTwice(delegatedOnce: IPerson) : IPerson by delegatedOnce{

    //if you remove this one, then DelegateOnce's message is used instead
    override fun message()="$name ma $age lat"
}
