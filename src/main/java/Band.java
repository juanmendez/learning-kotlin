import java.util.ArrayList;
import java.util.List;

public class Band {
    private String name;
    private List<Album> albums = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addAlbum( Album album ){
        albums.add( album );
    }
}
