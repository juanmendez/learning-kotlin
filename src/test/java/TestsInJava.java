import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestsInJava {

    @Test
    public void testsInJavaVersion(){

        //this is just related to working with Kotlin
        Pet pet = new Pet();
        pet.setAge( 1 );
        pet.setName( "Chino");

        final String[] allLetters = {""};
        String[] calls = new String[]{"a", "b", "c", "d" };

        for (String letter: calls) {

            Utils.writeFunNotation( letter, s -> {
                allLetters[0] += s;

                return null;
            });
        }

        assertEquals( allLetters[0], "abcd" );
    }
}