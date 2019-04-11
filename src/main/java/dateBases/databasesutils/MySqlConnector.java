package dateBases.databasesutils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Getter
@NoArgsConstructor
public class MySqlConnector {
    private static Properties properties = new Properties();
    private static String propertiesPath = "C:\\Users\\Sergey\\Desktop\\api-samples-master\\HomeWork13\\src\\main\\" +
            "resources\\dbConfig.properties";
    private static FileReader fileReader;

    private final static Logger LOGGER = Logger.getLogger(MySqlConnector.class);

    private static Connection connection;

    public static Connection getConnection() {
        try {
            fileReader = new FileReader(propertiesPath);
            properties.load(fileReader);
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
