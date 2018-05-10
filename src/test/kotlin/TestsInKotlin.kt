
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.observers.TestObserver
import io.reactivex.subscribers.TestSubscriber
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.File


/**
 * Created by musta on 10/23/2017.
 */
class Tests {
    lateinit var eightiesBands:List<Band>
    lateinit var ninetiesBands:List<Band>

    @Before
    fun `before`(){
        val strEighties = "../../raw/great_eighties_albums.csv"
        val strNineties = "../../raw/great_nineties_albums.csv"
        var file = File(strEighties)

        assertTrue(file.exists())

        eightiesBands = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map { Band().apply { //map each col to band attribute
                    name = it[0]
                    album = it[1]
                    year = it[2].toInt()
                }} //0. mapping turned initial list into List<Band>

        assertTrue( eightiesBands.isNotEmpty() )


        file = File(strNineties)
        ninetiesBands = file.readLines() //0. returns a list of strings
                .drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map { Band().apply { //map each col to band attribute
                    name = it[0]
                    album = it[1]
                    year = it[2].toInt()
                }} //0. mapping turned initial list into List<Band>

        assertTrue( ninetiesBands.isNotEmpty() )
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
                .fromArray( sentences )
                .flatMap { Flowable.fromIterable( it )  }
                .filter { it.isNotEmpty() }
                .doOnNext { sizes.add( it.length ) }
                .toList()
                .toFlowable()
                .subscribe( testSubscriber )

        testSubscriber.assertOf{ it.values().size.compareTo(4)  }
    }

    @Test
    fun `predicateTest`(){
        //all albums are in the 80's
        assertTrue( eightiesBands.all { it.year.toString().contains( Regex("198")) } )

        //at least one band's name is Metallica
        assertTrue( eightiesBands.any({it.name.equals("Metallica")}) )
        assertTrue( eightiesBands.any( { predicateByBandName(it, "Aerosmith")}) )

        //there is no band called Nickelback
        assertTrue( eightiesBands.none({it.name.equals("Nickelback")}))

        //two albums belong to Aerosmith
        assertEquals( eightiesBands.count({ it.name.equals("Aerosmith")}), 2 )
    }

    //custom predicates, help elaborate reusable predicates rather than being inline
    fun predicateByBandName(band:Band, name:String )=band.name.equals( name )


    @Test
    fun `asSequenceTest`(){

        val testSubscriber = TestObserver<Sequence<Band>>()

        Observable.create<Sequence<Band>> {
            it.onNext(getBandSequence("../../raw/great_nineties_albums.csv"))
        }.subscribe(testSubscriber)


        testSubscriber.assertOf { it.values().isNotEmpty() }
        /*

        //all albums are in the 90's
        assertTrue( bands.all { it.year.toString().contains( Regex("199")) } )

        //two albums belong to Soundgarden
        assertEquals( bands.count({ it.name.equals("Soundgarden")}), 2 )*/
    }

    /**
     * Outer loop iterates elements, inner loop iterates transformations
     * Without sequence, outer loop iterates transformations, inner loop iterates elements
     *
     * Sequences are useful for streaming or very large lists
     */
    fun getBandSequence( fileLocation: String ): Sequence<Band> {
        val file = File(fileLocation)

        return file.readLines().asSequence().drop(1) //skip header row
                .map { it.split(",") } //make row a list of strings split by comma
                .map { Band().apply { //map each col to band attribute
                    name = it[0]
                    album = it[1]
                    year = it[2].toInt()
                }} //0. mapping turned initial list into List<Band>
    }
}
