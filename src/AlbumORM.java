
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AlbumORM {
    private static ArrayList<Album> allAlbums = new ArrayList<Album>();

    public static ArrayList<Album> getAlbums() {
        return allAlbums;
    }

    public static void fetchAlbums() {
        try {    
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT album.* FROM album");

            if (!allAlbums.isEmpty()) {
                allAlbums.clear();
            }

            while (resultSet.next()) {
                Album album = new Album(resultSet.getInt("album_id"), resultSet.getString("title"), resultSet.getString("author"), resultSet.getString("genre"), resultSet.getInt("year"));
                for (Song song : SongORM.getSongs()) {
                    if (album.getId() == song.getAlbum().getId()) {
                        album.addSong(song);
                    }
                }
                allAlbums.add(album);
            }


            // Fetch collections
            resultSet = statement.executeQuery("SELECT collection.* FROM collection");

            ArrayList<Collection> collections = new ArrayList<Collection>();

            while (resultSet.next()) {
                Collection collection = new Collection(resultSet.getInt("collection_id"), resultSet.getString("title"), resultSet.getString("owner_name"));
                collections.add(collection);
            }

            // Add collection to album
            resultSet = statement.executeQuery(
                "SELECT\n" + 
                    "album_collection.collection_id,\n" +
                    "album_collection.album_id\n" + 
                "FROM\n" + 
                    "album_collection"
            );

            while(resultSet.next()) {
                for (Album album : allAlbums) {
                    for (Collection collection : collections) {
                        if ((collection.getId() == resultSet.getInt("collection_id")) && (album.getId() == resultSet.getInt("album_id"))) {
                            album.addCollection(collection);
                        }
                    }
                }
            }

            // for (Album album : allAlbums) {
            //     System.out.println(
            //         album.getId() + "\n" + 
            //         album.getTitle() + "\n" + 
            //         album.getAuthor() + "\n" + 
            //         album.getGenre() + "\n" + 
            //         album.getYear() + "\n"
            //     );

            //     for (Collection collection : album.getCollections()) {
            //         System.out.println(collection.getId() + "\n" + collection.getTitle() + "\n" + collection.getNameOfOwner() + "\n");
            //     }
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newAlbum(String title, String author, String genre, int year) {
        try {
            Connection connection = DBConnect.connect();

            String query = "INSERT INTO album (title, author, genre, year) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, String.valueOf(year));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateAlbum(int id, String title, String author, String genre, int year) {
        try {
            Connection connection = DBConnect.connect();

            String query = "UPDATE album SET title = ?, author = ?, genre = ?, year = ? WHERE album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, author);
            preparedStatement.setString(3, genre);
            preparedStatement.setString(4, String.valueOf(year));
            preparedStatement.setString(5, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteAlbum(int id) {
        try {
            Connection connection = DBConnect.connect();

            String query = "DELETE FROM album WHERE album_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
