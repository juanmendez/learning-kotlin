
import org.junit.Assert
import org.junit.Test

/**
 * Created by musta on 10/23/2017.
 */
class Tests {

    @Test
    fun testAnimalInterface(){

        val human = Human("Juan" );
        Assert.assertTrue( human is Animal )
        Assert.assertEquals( human.getLegs(), 0)


        //interesting way how to instantiate an instance on the fly
        //if only one method then we can use labmda instead
        val cat:Animal = object:Animal{
            override fun getName(): String {
                return "Meow"
            }

            override fun getLegs(): Int {
                return 0
            }
        }

        Assert.assertTrue( cat is Animal )

        describeAnimal( human )
        describeAnimal( cat )
    }
}