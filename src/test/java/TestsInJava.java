import org.junit.Assert;
import org.junit.Test;

public class TestsInJava {

    @Test
    public void testInheritance(){
        ScriptingLanguage language = new ScriptingLanguage( "Javascript", 1995, 0 );
        Assert.assertEquals( language.getVersion(), 0 );
        Assert.assertTrue( language instanceof  Language );

        Assert.assertEquals( "Hello "  + language.getName() + ":" + language.getYearCreated(), language.hello() );
    }
}