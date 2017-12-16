
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

/**
 * Created by musta on 10/23/2017.
 */
class Tests {

    @Test
    fun testHolderSingleton() {
        assertEquals(Holder.apps.size, 0)

        Holder.apps.add( Application("1stApp"))
        Holder.apps.add( Application("2ndApp"))
        Holder.apps.add( Application("3rdApp"))

        assertEquals(Holder.apps.size, 3)
        assertEquals(Holder.apps[Holder.apps.size-1].name, "3rdApp")

        var application = Holder.apps[Holder.apps.size-1]
        var activity = application.createActivity( "MainActivity" )
        assertEquals( activity.name, "MainActivity")
        assertEquals( activity.application, application )


        application = Application.createApp( "4thApp")
        activity = Application.createAppAndActivity( "5thApp", "2ndActivity")
        assertNotEquals( application, activity.application )

        println( activity.greeting() )
    }
}
