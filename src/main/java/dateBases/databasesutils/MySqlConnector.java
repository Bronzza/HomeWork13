package dateBases.databasesutils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Getter
@NoArgsConstructor
public class MySqlConnector {
    private static Properties properties = new Properties();
    private static String propertiesPath = "dbConfig.properties";
    private final static Logger LOGGER = Logger.getLogger(MySqlConnector.class);
    private static Connection connection;

    public static Connection getConnection() {

        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            InputStream propertiesStream = classLoader.getResourceAsStream(propertiesPath);
            properties.load(propertiesStream);
            connection = DriverManager.getConnection(properties.getProperty("db.conn.url"),
                    properties.getProperty("db.username"), properties.getProperty("db.password"));
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
