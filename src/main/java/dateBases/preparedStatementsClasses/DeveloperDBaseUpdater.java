package dateBases.preparedStatementsClasses;

import dateBases.dbUtils.ListCreator;
import dateBases.entitys.Developer;
import dateBases.enums.EntitysEnum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DeveloperDBaseUpdater {

    private final static String INSERT_NEW = "INSERT INTO developers VALUES (default, ?, ?, ?, ?, ?)";
    private final static String DELETE = "DELETE FROM developers WHERE (last_name = ? AND first_name = ?)";
    private final static String SELECT = "SELECT * FROM developers WHERE last_name = ?";
    private final static String UPDATE = "UPDATE developers SET last_name = ? WHERE last_name = ?";

    Connection connection;
    PreparedStatement preparedStatement;

    public DeveloperDBaseUpdater(Connection connection) {
        this.connection = connection;
    }

    public void addDeveloperToBase(String surName, String name, int age, int salary, boolean isMale) {
        try {
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
            e.printStackTrace();
        }
    }

    public void deleteDeveByLastName(String surName, String name) {
        try {
            if (!connection.isClosed()) {
                preparedStatement = connection.prepareStatement(DELETE);
                preparedStatement.setString(1, surName);
                preparedStatement.setString(2, name);
                preparedStatement.execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Developer> selectDevelopers(String surName) {
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT);
            preparedStatement.setString(1, surName);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ListCreator.iterateDevelResultSet(EntitysEnum.DEVELOPERS, resultSet);
    }

    public void changeSurNameDev(String newSurName, String oldSurname) {
        try {
            preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, newSurName);
            preparedStatement.setString(2, oldSurname);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
