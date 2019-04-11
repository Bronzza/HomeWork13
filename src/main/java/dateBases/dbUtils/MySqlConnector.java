package dateBases.dbUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

@Getter
@NoArgsConstructor
public class MySqlConnector {
    Properties properties = new Properties();
    private final static String URL = "jdbc:mysql://localhost:3306/home_work_mate" +
            "                    ?verifyServerCertificate=false" +
            "                    &useSSL=false" +
            "                    &requireSSL=false" +
            "                    &useLegacyDatetimeCode=false" +
            "                    &amp" +
            "                    &serverTimezone=UTC";
    private final static String LOGIN = "root";
    private final static String PASSWORD = "root";
    private final static Logger LOGGER = Logger.getLogger(MySqlConnector.class);

    @Getter
    private static Connection connection;

    public static void startConnection() {
        try {
            connection = DriverManager.getConnection(URL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            LOGGER.error("Connection start failed");
        }
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
