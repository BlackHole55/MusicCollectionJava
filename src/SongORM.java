import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SongORM {
    private static ArrayList<Song> allSongs = new ArrayList<Song>();

    public static ArrayList<Song> getSongs() {
        return allSongs;
    }

    public static void fetchSongs() {
        try {    
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT song.*, album.* FROM song JOIN album ON song.album_id = album.album_id");

            if (!allSongs.isEmpty()) {
                allSongs.clear();
            }

            while (resultSet.next()) {
                String albumTitle = resultSet.getString(6);

                Album album = new Album(resultSet.getInt("album_id"), albumTitle, resultSet.getString("author"), resultSet.getString("genre"), resultSet.getInt("year"));

                Song song = new Song(
                    resultSet.getInt("song_id"), 
                    resultSet.getString("title"), 
                    resultSet.getInt("duration"),
                    album
                );

                allSongs.add(song);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newSong(int albumId, String title, int duration) {
        try {
            Connection connection = DBConnect.connect();

            String query = "INSERT INTO song (album_id, title, duration) VALUES (?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(albumId));
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, String.valueOf(duration));   

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateSong(int id, String title, int duration) {
        try {
            Connection connection = DBConnect.connect();

            String query = "UPDATE song SET title = ?, duration = ? WHERE song_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, String.valueOf(duration));
            preparedStatement.setString(3, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteSong(int id) {
        try {
            Connection connection = DBConnect.connect();

            String query = "DELETE FROM song WHERE song_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}