import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class TestsInJava {

    Holder holder;

    @Before
    public void onBefore(){
        holder = Holder.INSTANCE;

        holder.getApps().add( new Application("1st"));
        holder.getApps().add( new Application("2nd"));
        holder.getApps().add( new Application("3rd"));
    }

    @Test
    public void testHolderSingleton(){


        Application application = holder.getApps().get( holder.getApps().size()-1 );
        Assert.assertEquals( application.getName(), "3rd");

        final Activity mainActivity = application.createActivity("MainActivity");
        Assert.assertEquals( mainActivity.getName(), "MainActivity");
        Assert.assertEquals( mainActivity.getApplication(), application );

        Application.createApp( "4th");
        Activity activity = Application.createAppAndActivity( "5thApp", "2ndActivity");


        //@file:JvmName("Utils")
        System.out.println( Utils.greeting( activity ));
        System.out.println( Utils.helloAgain() );

        Assert.assertFalse( application.fieldSaysHi.isEmpty() );

        //cannot be assigned to a final variable: Application.staticFieldSaysHi = "hello"
        Assert.assertFalse( Application.staticFieldSaysHi.isEmpty() );

        //without overloads, first two methods report missing args
        application.tryOverloads("param1", "param2");
        application.tryOverloads("param1", "param2", 1);
        application.tryOverloads("param1", "param2", 1, 2);
    }

    @Test(expected = IOException.class)
    public void expectException() throws IOException {

        //without @Throws in Kotlin, there is no exception expected
        Utils.throwUnsignedException();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void expectMe(){
        holder.getNicknames().add("1");
        holder.getNicknames().add("2");
        holder.getNicknames().add("3");
    }
}