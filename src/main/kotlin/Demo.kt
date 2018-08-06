
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Single
import io.reactivex.SingleObserver

data class Band(var name:String = "", var album:String ="", var year:Int = 0, var rate:Int = 0 )

object ModuleBehaviorRelay {
    private lateinit var relay:BehaviorRelay<String>

    fun simpleValues() {
        println("------------Simple Values----------")
        relay = BehaviorRelay.createDefault("default")
        println( "relay ${relay.value}")
    }

    fun doNext(s:String)= relay.accept(s)

    fun asObservable()=relay.hide()
}

object SingleRx: Single<String>() {
    override fun subscribeActual(observer: SingleObserver<in String>) {
     this.subscribe(observer)
    }
}