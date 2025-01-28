import java.util.ArrayList;

public class Collection {
    private int id;
    private String title;
    private String nameOfOwner;
    private ArrayList<Album> albums = new ArrayList<Album>();

    public Collection(int id, String title, String nameOfOwner) {
        this.id = id;
        this.title = title;
        this.nameOfOwner = nameOfOwner;
    }

    // ID
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Title
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Name of Owner
    public String getNameOfOwner() {
        return this.nameOfOwner;
    }

    public void setNameOfOwner(String nameOfOwner) {
        this.nameOfOwner = nameOfOwner;
    }

    // Albums
    public ArrayList<Album> getAlbums() {
        return this.albums;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    public void removeAlbum(Album album) {
        this.albums.remove(album);
    }

    @Override
    public String toString() {
        return this.title;
    }
}
