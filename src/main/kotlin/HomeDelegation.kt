interface IHome{
    var address:String
    fun describe():String
}

class RealHome(override var address: String) :IHome{
    override fun describe(): String = "Welcome Home!! $address"
}

class DelegatedHome( val home:IHome ):IHome by home