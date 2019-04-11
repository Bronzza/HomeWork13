package dateBases.databasesutils;

import dateBases.entitys.Developer;
import lombok.extern.log4j.Log4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
public class ListCreator {

    public static List iterateDevelResultSet(ResultSet resultSet) {
        List<Developer> result = new ArrayList<>();
        try {
            while (resultSet.next()) {
                Developer localDeveloper = new Developer();
                localDeveloper.setSurName(resultSet.getString("last_name"));
                localDeveloper.setFirstName(resultSet.getString("first_name"));
                localDeveloper.setAge(resultSet.getInt("age"));
                localDeveloper.setIsMale(resultSet.getBoolean("is_Man"));
                localDeveloper.setSalary(resultSet.getInt("salary"));
                localDeveloper.setId(resultSet.getInt("id"));
                result.add(localDeveloper);
            }
        } catch (SQLException e) {
            log.error("SQL exception happened");
        }
        return result;
    }
}
