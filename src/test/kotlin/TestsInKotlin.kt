
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Consumer
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File

class Tests {
    lateinit var eightiesBands: List<Band>
    lateinit var ninetiesBands: List<Band>
    val strEighties = "../../raw/great_eighties_albums.csv"
    val strNineties = "../../raw/great_nineties_albums.csv"
    
    @Before
    fun `before`() {
        
        var file = File(strEighties)

        assertTrue(file.exists())

        eightiesBands = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map {
                    Band().apply {
                        //map each col to band attribute
                        name = it[0]
                        album = it[1]
                        year = it[2].toInt()
                    }
                } //0. mapping turned initial list into List<Band>

        assertTrue(eightiesBands.isNotEmpty())


        file = File(strNineties)
        ninetiesBands = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map {
                    Band().apply {
                        //map each col to band attribute
                        name = it[0]
                        album = it[1]
                        year = it[2].toInt()
                    }
                } //0. mapping turned initial list into List<Band>

        assertTrue(ninetiesBands.isNotEmpty())
    }

    @Test
    fun `rxZip`(){
        val eighties = Flowable.just( eightiesBands )
        val nineties = Flowable.just( ninetiesBands )


    }

    @Test
    fun `get funky with rxJava`() {

        val sentences = mutableListOf<String>()
        sentences.add("Ladybug!  Ladybug!")
        sentences.add("Fly away home.")
        sentences.add("")
        sentences.add("Your house is on fire.")
        sentences.add("And your children all gone.")

        var sizes = mutableListOf<Int>()

        val testSubscriber = TestSubscriber<MutableList<String>>()
        Flowable
                .fromArray(sentences)
                .flatMap { Flowable.fromIterable(it) }
                .filter { it.isNotEmpty() }
                .doOnNext { sizes.add(it.length) }
                .toList()
                .toFlowable()
                .subscribe(testSubscriber)

        testSubscriber.assertOf { it.values().size.compareTo(4) }
    }

    @Test
    fun `predicateTest`() {
        //all albums are in the 80's
        assertTrue(eightiesBands.all { it.year.toString().contains(Regex("198")) })

        //at least one band's name is Metallica
        assertTrue(eightiesBands.any({ it.name.equals("Metallica") }))
        assertTrue(eightiesBands.any({ predicateByBandName(it, "Aerosmith") }))

        //there is no band called Nickelback
        assertTrue(eightiesBands.none({ it.name.equals("Nickelback") }))

        //two albums belong to Aerosmith
        assertEquals(eightiesBands.count({ it.name.equals("Aerosmith") }), 2)
    }

    //custom predicates, help elaborate reusable predicates rather than being inline
    fun predicateByBandName(band: Band, name: String) = band.name.equals(name)


    @Test
    fun `asSequenceTest`(){

        val testSubscriber = TestObserver<Sequence<Band>>()

        Observable.create<Sequence<Band>> {
            it.onNext(getBandSequence("../../raw/great_nineties_albums.csv"))
        }.subscribe(testSubscriber)

        /**
         * Just:
         * - nice to use, but it runs even without subscription
         */
        Observable.just(
                getBandSequence("../../raw/great_nineties_albums.csv")
        ).subscribe(testSubscriber)


        /**
         * Defers:
         * - wraps an expensive method
         * - executes until subscribed
         * - always expects to return an observable
         * @see <a href="https://github.com/CDRussell/CasterRxJava/tree/defer">Defer</a>
         */
        Observable.defer {
            Observable.just(getBandSequence("../../raw/great_nineties_albums.csv"))
        }.subscribe(testSubscriber)

        testSubscriber.assertOf { it.values().isNotEmpty() }


        Observable.fromCallable{
            getBandSequence("../../raw/great_nineties_albums.csv")
        }.subscribe(testSubscriber)
        testSubscriber.assertOf { it.values().isNotEmpty() }


        /**
         * Defer and fromCallable
         * both do much the same. FromCallable is a lot shorter to write, and ideal.
         * Defer is great when it comes to return an observable. So that can be a reason why to use it over fromCallable
         */

        /*

        //all albums are in the 90's
        assertTrue( bands.all { it.year.toString().contains( Regex("199")) } )

        //two albums belong to Soundgarden
        assertEquals( bands.count({ it.name.equals("Soundgarden")}), 2 )*/
    }


    @Test
    fun `multiple observables into a zip`(){
        val testObserver = TestSubscriber<Int>()

        Flowable.zip( getObservableBands(strEighties,"Bon Jovi"),
                getObservableBands(strNineties, "Pearl Jam"),
                BiFunction { t1:List<Band>, t2:List<Band> -> t1.size+t2.size })
                .subscribe(testObserver)

        testObserver.assertOf { it.values().isNotEmpty() }
    }

    @Test
    fun `chain of observable into zip`(){

        val observableBands = mutableListOf<Flowable<List<Band>>>()

        listOf("Bon Jovi", "Aerosmith").forEach { observableBands.add( getObservableBands(strEighties,it)) }

        Flowable
                .merge(observableBands)
                .subscribe {
                    println(it.size)
                }

        observableBands.clear()

        Flowable
                .just(listOf("Bon Jovi", "Aerosmith"))
                .flatMap {
                    it.forEach { observableBands.add( getObservableBands(strEighties,it)) }

                    Flowable.merge(observableBands)
                }.subscribe {
                    println(it.size)
                }


        observableBands.clear()

        Flowable
                .just(listOf("Bon Jovi", "Aerosmith"))
                .flatMapIterable { it }
                .map {
                    getObservableBands(strEighties,it)
                }
                .toList()
                .toFlowable()
                .flatMap {
                    Flowable.merge(it)
                }.subscribe( Consumer {
                    println(it)
                })
    }


    @Test
    fun `chain of observables to get bands and then their ratings`(){

        val observableBands = mutableListOf<Flowable<List<Band>>>()

        listOf("Bon Jovi", "Aerosmith").forEach { observableBands.add( getObservableBands(strEighties,it)) }


        /**
         * I couldn't find a way to combine two observables. Zip worked, but only let the iteration happened once.
         */
        Flowable
                .just(listOf("Bon Jovi", "Aerosmith"))
                .flatMapIterable { it }
                .flatMap {
                    getObservableBands(strEighties,it)
                }
                .flatMapIterable { it }
                .doOnNext {
                    it.rate = randomRating(1,5)
                }
                .toList()
                .toFlowable()
                .subscribe {
                    println(it)
                }
    }

    @Test
    fun `test relay`() {
        val observer = TestObserver<String>()

        val module = ModuleBehaviorRelay
        module.simpleValues()

        observer.assertNotSubscribed()
        module.doNext("hello world")

        module.asObservable().subscribe(observer)
        assertTrue(observer.values().contains("hello world"))
    }

    /**
     * Outer loop iterates elements, inner loop iterates transformations
     * Without sequence, outer loop iterates transformations, inner loop iterates elements
     *
     * Sequences are useful for streaming or very large lists
     */
    fun getBandSequence(fileLocation: String): Sequence<Band> {
        val file = File(fileLocation)

        return file.readLines().asSequence().drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map {
                    Band().apply {
                        //map each col to band attribute
                        name = it[0]
                        album = it[1]
                        year = it[2].toInt()
                    }
                } //0. mapping turned initial list into List<Band>
    }

    /**
     * It is kind of redundant, but lets work with multiple calls
     */
    fun getObservableBands(fileLocation: String, bandName: String): Flowable<List<Band>> {
        val file = File(fileLocation)

        val list = file.readLines().asSequence().drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map { Band().apply { //map each col to band attribute
                    name = it[0]
                    album = it[1]
                    year = it[2].toInt()
                }}.filter { it.name == bandName }.toList()

        return Flowable.just(list)
    }

    fun randomRating(min: Int, max: Int): Int {
        val range = max - min + 1
        return (Math.random() * range).toInt() + min
    }
}
