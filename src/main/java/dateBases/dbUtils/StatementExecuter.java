package dateBases.dbUtils;

import lombok.AllArgsConstructor;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@AllArgsConstructor
@Setter
public class StatementExecuter {
    private static Logger logger = Logger.getLogger(StatementExecuter.class);
    private static Connection connection;
    private static Statement statement;

    public static void executeDelUpdtCreate(String querySQL) {
        try {
            statement = connection.createStatement();
            statement.execute(querySQL);
        } catch (SQLException e) {
            logger.error("SQL exeption. Check initialisation of connection or state of DB");
        }
    }

    public static ResultSet executeSelect(String querySelect) {
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySelect);
        } catch (SQLException e) {
            logger.error("SQL exeption. Check initialisation of connection or state of DB");
        }
        return resultSet;
    }
}
