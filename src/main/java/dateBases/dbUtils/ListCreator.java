package dateBases.dbUtils;

import dateBases.entitys.Developer;
import dateBases.entitys.Project;
import dateBases.enums.EntitysEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ListCreator {

    public static List iterateDevelResultSet(EntitysEnum entityName, ResultSet resultSet) {
        List<Developer> result;
        List<Project> resultProject;
        if (entityName == EntitysEnum.DEVELOPERS) {
            result = new ArrayList<>();
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
                e.printStackTrace();
            }
            return result;
        } else if (entityName == EntitysEnum.PROJECTS) {
            resultProject = new ArrayList<>();
            try {
                while (resultSet.next()) {
                    Project localProject = new Project();
                    localProject.setName(resultSet.getString("name"));
                    localProject.setDate(resultSet.getString("date_of_start"));
                    localProject.setCost(resultSet.getInt("cost"));
                    localProject.setId(resultSet.getInt("id"));
                    localProject.setDevQuantity(resultSet.getInt("count(id)"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return resultProject;
        }
        return null;
    }
}
