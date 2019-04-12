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
public class DeveloperDBaseUpdater {

    private final static String INSERT_NEW = "INSERT INTO developers VALUES (default, ?, ?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM developers WHERE (last_name = ? AND first_name = ?)";
    private final static String SELECT = "SELECT * FROM developers WHERE last_name = ?";
    private final static String UPDATE = "UPDATE developers SET last_name = ? WHERE last_name = ?";

    private PreparedStatement preparedStatement;
    private Connection localConnection;

    public DeveloperDBaseUpdater(Connection connection) {
        this.localConnection = connection;
        try {
            preparedStatement = connection.prepareStatement(SELECT);
        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
    }

    public void addDeveloperToBase(String surName, String name, int age, int salary, boolean isMale) {
        try (Connection connection = MySqlConnector.getConnection()) {
            if (!connection.isClosed()) {
                preparedStatement = connection.prepareStatement(INSERT_NEW);
                preparedStatement.setString(1, surName);
                preparedStatement.setString(2, name);
                preparedStatement.setInt(3, age);
                preparedStatement.setBoolean(4, isMale);
                preparedStatement.setInt(5, salary);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
    }

    public void deleteDeveByLastName(String surName, String name) {
        try (Connection connection = MySqlConnector.getConnection()) {
            if (!connection.isClosed()) {
                preparedStatement = connection.prepareStatement(DELETE);
                preparedStatement.setString(1, surName);
                preparedStatement.setString(2, name);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
    }

    public List<Developer> selectDevelopers(String surName) {
        ResultSet resultSet = null;
        try (Connection connection = MySqlConnector.getConnection()) {
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1, surName);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
        return ListCreator.iterateDevelResultSet(resultSet);
    }

    public void changeSurNameDev(String newSurName, String oldSurname) {
        try (Connection connection = MySqlConnector.getConnection()) {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, newSurName);
            preparedStatement.setString(2, oldSurname);
            preparedStatement.execute();
        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
    }
}
