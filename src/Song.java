public class Song {
    private int id;
    private String title;
    private int duration;
    private Album album;

    public Song(int id, String title, int duration, Album album) {
        this.id = id;
        this.title = title;
        this.duration = duration;
        this.album = album;
    }

    public Song(int id, String title, int duration) {
        this.id = id;
        this.title = title;
        this.duration = duration;
    }

    public Song(int id, String title) {
        this.id = id;
        this.title = title;
        this.duration = 0;
    }

    // ID
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    // Album

    public Album getAlbum() {
        return this.album;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }
    
    // Title
    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // Duration
    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
