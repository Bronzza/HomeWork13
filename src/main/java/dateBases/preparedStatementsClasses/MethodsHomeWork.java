package dateBases.preparedStatementsClasses;

import dateBases.databasesutils.ListCreator;
import dateBases.databasesutils.MySqlConnector;
import dateBases.entitys.Developer;
import lombok.extern.log4j.Log4j;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Log4j
public class MethodsHomeWork {

    private final static String GET_ALL_DEV_OF_PROJ_ID = "SELECT developers.*FROM developers\n" +
            "INNER JOIN develop_proj\n" +
            "ON developers.id = develop_proj.id_developers \n" +
            "WHERE develop_proj.id_project = ?";
    private final static String GET_ALL_DEV_OF_LANG_NAME = "SELECT developers.*FROM developers\n" +
            "INNER JOIN developer_skills\n" +
            "ON developers.id = developer_skills.id_developers \n" +
            "INNER JOIN language\n" +
            "ON developer_skills.id_language = language.id\n" +
            "WHERE language.lang= ?";

    private Connection localConnection;
    private PreparedStatement preparedStatementId;
    private PreparedStatement preparedStatementName;

    public MethodsHomeWork(Connection connection) {
        this.localConnection = connection;
        try {
            preparedStatementId = connection.prepareStatement(GET_ALL_DEV_OF_PROJ_ID);
            preparedStatementName = connection.prepareStatement(GET_ALL_DEV_OF_LANG_NAME);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Developer> getAllDevelopersOfProject(int idProject) {
        ResultSet resultSet = null;
        try (Connection connection = MySqlConnector.getConnection()) {
            preparedStatementId.setInt(1, idProject);
            resultSet = preparedStatementId.executeQuery();

        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
        return ListCreator.iterateDevelResultSet(resultSet);
    }

    public List<Developer> getAllDevelopersLangName(String name) {
        ResultSet resultSet = null;
        try (Connection connection = MySqlConnector.getConnection()) {
            preparedStatementName.setString(1, name);
            resultSet = preparedStatementName.executeQuery();
        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
        return ListCreator.iterateDevelResultSet(resultSet);
    }
}
