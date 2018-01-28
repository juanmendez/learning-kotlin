package info.juanmendez.kotlin.interop;

import info.juanmendez.kotlin.Album;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * This class is intended to show how to work with interoperability
 * when handling nullables from Java to Kotlin
 */
public class InteropAlbum implements Album {

    private String mName;

    @Override
    public void setName( @NotNull String name) {
        mName = name;
    }


    public @NotNull String getName(){
        return mName;
    }

    public @Nullable String getNameOrNull(){
        return mName;
    }
}
