
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CollectionORM {
    private static ArrayList<Collection> allCollections = new ArrayList<Collection>();

    public static ArrayList<Collection> getCollections() {
        return allCollections;
    }

    public static void fetchCollections() {
        try {
            Connection connection = DBConnect.connect();
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT collection.* FROM collection");

            if (!allCollections.isEmpty()) {
                    allCollections.clear();
            }

            while (resultSet.next()) {
                Collection collection = new Collection(resultSet.getInt("collection_id"), resultSet.getString("title"), resultSet.getString("owner_name"));
                allCollections.add(collection);
            }

            resultSet = statement.executeQuery(
                "SELECT\n" + 
                    "album_collection.collection_id,\n" +
                    "album_collection.album_id\n" + 
                "FROM\n" + 
                    "album_collection"
            );

            while(resultSet.next()) {
                for (Collection collection : allCollections) {
                    for (Album album : AlbumORM.getAlbums()) {
                        if ((collection.getId() == resultSet.getInt("collection_id")) && (album.getId() == resultSet.getInt("album_id"))) {
                            collection.addAlbum(album);
                        }
                    }
                }
            }

            // for (Collection collection : allCollections) {
            //     System.out.println(
            //         collection.getId() + "\n" + 
            //         collection.getTitle() + "\n" + 
            //         collection.getNameOfOwner() + "\n"
            //     );

            //     for (Album album : collection.getAlbums()) {
            //         System.out.println(album.getId() + "\n" + album.getTitle() + "\n" + album.getAuthor() + "\n" + album.getGenre() + "\n" + album.getYear() + "\n");
            //     }
            // }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void newCollection(String title, String nameOfOwner) {
        try {
            Connection connection = DBConnect.connect();

            String query = "INSERT INTO collection (title, owner_name) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, nameOfOwner);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateCollection(int id, String title, String nameOfOwner) {
        try {
            Connection connection = DBConnect.connect();

            String query = "UPDATE collection SET title = ?, owner_name = ? WHERE collection_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, title);
            preparedStatement.setString(2, nameOfOwner);
            preparedStatement.setString(3, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteCollection(int id) {
        try {
            Connection connection = DBConnect.connect();

            String query = "DELETE FROM collection WHERE collection_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(id));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void addAlbum(int CollectionId, int albumId) {
        try {
            Connection connection = DBConnect.connect();

            String query = "INSERT INTO album_collection (collection_id, album_id) VALUES (?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);

            preparedStatement.setString(1, String.valueOf(CollectionId));
            preparedStatement.setString(2, String.valueOf(albumId));

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
