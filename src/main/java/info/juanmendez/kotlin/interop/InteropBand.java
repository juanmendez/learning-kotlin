package info.juanmendez.kotlin.interop;

import info.juanmendez.kotlin.Album;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class InteropBand {

    private String name;
    private List<Album> albumList;

    @NotNull
    public String getName() {
        return name;
    }

    public void setName( @NotNull  String name) {
        this.name = name;
    }

    @Nullable
    public List<Album> getAlbumList() {
        return albumList;
    }

    public void addAlbum( @NotNull Album album ){
        if( albumList == null )
            albumList = new ArrayList<>();

        albumList.add( album );
    }
}
