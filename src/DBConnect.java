import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import io.github.cdimascio.dotenv.Dotenv;

public class DBConnect {
    static Dotenv dotenv = Dotenv.load();
        
    public static Connection connect() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(dotenv.get("JDBC_URL"), dotenv.get("DB_USER"), dotenv.get("DB_PASSWORD"));
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
