package dateBases.preparedStatementsClasses;

import dateBases.dbUtils.ListCreator;
import dateBases.entitys.Developer;
import dateBases.enums.EntitysEnum;
import lombok.Getter;
import lombok.Setter;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Getter
@Setter
public class MethodsHomeWork {
    private static Logger logger = Logger.getLogger(MethodsHomeWork.class);
    private static final String GET_ALL_DEV_OF_PROJ_ID = "SELECT developers.*FROM developers\n" +
            "INNER JOIN develop_proj\n" +
            "ON developers.id = develop_proj.id_developers \n" +
            "WHERE develop_proj.id_project = ?";
    private static final String GET_ALL_DEV_OF_LANG_NAME = "SELECT developers.*FROM developers\n" +
            "INNER JOIN developer_skills\n" +
            "ON developers.id = developer_skills.id_developers \n" +
            "INNER JOIN language\n" +
            "ON developer_skills.id_language = language.id\n" +
            "WHERE language.lang= ?";

    private Connection connection;
    private PreparedStatement preparedStatement;

    public MethodsHomeWork(Connection connection){
        this.connection = connection;
    }

   public List<Developer> getAllDevelopersOfProject(int idProject){
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_DEV_OF_PROJ_ID);
            preparedStatement.setInt(1, idProject);
            resultSet = preparedStatement.executeQuery();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (List <Developer>) ListCreator.iterateDevelResultSet(EntitysEnum.DEVELOPERS, resultSet);
    }
    public List<Developer> getAllDevelopersLangName(String name){
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(GET_ALL_DEV_OF_LANG_NAME);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (List <Developer>) ListCreator.iterateDevelResultSet(EntitysEnum.DEVELOPERS, resultSet);
    }
}
