import java.util.ArrayList;

public class Album {
    private int id;
    private String title;
    private String author;
    private String genre;
    private int year;
    private ArrayList<Song> songs = new ArrayList<Song>();
    private ArrayList<Collection> collections = new ArrayList<Collection>();

    public Album(int id, String title, String author, String genre, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
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

    // Author
    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    // Genre
    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Year
    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    // Songs
    public ArrayList<Song> getSongs() {
        return this.songs;
    }

    public void addSong(Song song) {
        this.songs.add(song);   
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    // Collections
    public ArrayList<Collection> getCollections() {
        return this.collections;
    }

    public void addCollection(Collection collection) {
        this.collections.add(collection);
    }

    public void removeCollection(Collection collection) {
        this.collections.remove(collection);
    }
}