import org.junit.Assert;
import org.junit.Test;

public class TestsInJava {

    @Test
    public void testHolderSingleton(){

        Holder singleton = Holder.INSTANCE;

        Assert.assertEquals( singleton.getApps().size(), 0 );
        singleton.getApps().add( new Application("1st"));
        singleton.getApps().add( new Application("2nd"));
        singleton.getApps().add( new Application("3rd"));


        Application application = singleton.getApps().get( singleton.getApps().size()-1 );
        Assert.assertEquals( application.getName(), "3rd");

        final Activity mainActivity = application.createActivity("MainActivity");
        Assert.assertEquals( mainActivity.getName(), "MainActivity");
        Assert.assertEquals( mainActivity.getApplication(), application );

        Application.createApp( "4th");
        Activity activity = Application.createAppAndActivity( "5thApp", "2ndActivity");


        //@file:JvmName("Utils")
        System.out.println( Utils.greeting( activity ));
    }
}