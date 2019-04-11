import dateBases.dbUtils.MySqlConnector;
import dateBases.entitys.Developer;
import dateBases.preparedStatementsClasses.DeveloperDBaseUpdater;
import dateBases.preparedStatementsClasses.MethodsHomeWork;

import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args) {
         MySqlConnector.startConnection();
        DeveloperDBaseUpdater ddbu = new DeveloperDBaseUpdater(MySqlConnector.getConnection());
        MethodsHomeWork homeWork = new MethodsHomeWork(MySqlConnector.getConnection());
        ddbu.deleteDeveByLastName("Golub", "Lenya");
        ddbu.changeSurNameDev("Golubkov", "Golub");
       List<Developer> developers = homeWork.getAllDevelopersOfProject(3);
        List<Developer> developers1 = homeWork.getAllDevelopersLangName("Java");
       developers.stream()
                .forEach(System.out::println);
        developers1.stream()
                .forEach(System.out::println);
        MySqlConnector.closeConnection();
    }
}
