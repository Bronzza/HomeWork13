package dateBases.databasesutils;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Log4j
@AllArgsConstructor
@Setter
public class StatementExecuter {
    private static Statement statement;

    public static void executeDelUpdtCreate(String querySQL) {
        try (Connection connection = MySqlConnector.getConnection()) {
            statement = connection.createStatement();
            statement.execute(querySQL);
        } catch (SQLException e) {
            log.error("SQL exeption. Check initialisation of connection or state of DB");
        }
    }

    public static ResultSet executeSelect(String querySelect) {
        ResultSet resultSet = null;
        try (Connection connection = MySqlConnector.getConnection()) {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(querySelect);
        } catch (SQLException e) {
            log.error("SQL exeption. Check initialisation of connection or state of DB");
        }
        return resultSet;
    }
}
