package dateBases.databasesutils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Getter
@NoArgsConstructor
public class MySqlConnector {
    private static final Properties PROPERTIES = new Properties();
    private static final String PROPERTIES_PATH = "/dbConfig.properties";
    private final static Logger LOGGER = Logger.getLogger(MySqlConnector.class);
    private static Connection connection;

    public static Connection getConnection() {

        try {
            InputStream propertiesStream = MySqlConnector.class.getResourceAsStream(PROPERTIES_PATH);
            PROPERTIES.load(propertiesStream);
            propertiesStream.close();
            connection = DriverManager.getConnection(PROPERTIES.getProperty("db.conn.url"),
                    PROPERTIES.getProperty("db.username"), PROPERTIES.getProperty("db.password"));
        } catch (SQLException | IOException e) {
            LOGGER.error("Connection start failed");
        }
        return connection;
    }

    public static boolean closeConnection() {
        try {
            connection.close();
            return connection.isClosed();
        } catch (SQLException e) {
            LOGGER.error("Connection close failed");
        }
        return false;
    }
}
