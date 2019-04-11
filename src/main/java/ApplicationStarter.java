import dateBases.databasesutils.MySqlConnector;
import dateBases.entitys.Developer;
import dateBases.preparedStatementsClasses.DeveloperDBaseUpdater;
import dateBases.preparedStatementsClasses.MethodsHomeWork;

import java.util.List;

public class ApplicationStarter {
    public static void main(String[] args)  {
        MySqlConnector.getConnection();
        DeveloperDBaseUpdater ddbu = new DeveloperDBaseUpdater(MySqlConnector.getConnection());
        MethodsHomeWork homeWork = new MethodsHomeWork(MySqlConnector.getConnection());
        ddbu.deleteDeveByLastName("Golub", "Lenya");
        ddbu.changeSurNameDev("Golubkovkov", "Golubkov");
        List<Developer> developers = homeWork.getAllDevelopersOfProject(3);
        List<Developer> developers1 = homeWork.getAllDevelopersLangName("Java");
        developers.forEach(System.out::println);
        developers1.forEach(System.out::println);
    }
}
