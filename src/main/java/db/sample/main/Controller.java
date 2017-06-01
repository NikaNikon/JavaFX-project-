package db.sample.main;
import db.sample.DAO.mongoDbDAO.MongoDbUserDAO;
import db.sample.DAO.mySQLDAO.MySQLUserDAO;
import db.sample.IDAO.IUserDAO;
import db.sample.speedtest.Speedtest;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import db.sample.coaches.Coaches;
import db.sample.groups.Groups;
import db.sample.registration.Registration;

import static com.sun.corba.se.impl.util.Utility.printStackTrace;


public class Controller
{
    @FXML
    public void onClickedRegistration()
    {
        Stage regStage = new Stage();
        Registration newUser = new Registration();
        try { newUser.start(regStage);}
        catch (Exception ex){}
    }

    @FXML
    public void onClickedGroups()
    {
        Stage groupsStage = new Stage();
        Groups groupsView = new Groups();
        try {groupsView.start(groupsStage);}
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @FXML
    public void onClickedCoaches()
    {
        Stage coachesStage = new Stage();
        Coaches coachesView = new Coaches();
        try {coachesView.start(coachesStage);}
        catch (Exception ex){}
    }

    @FXML
    public void onClickedSpeedBtn()
    {
        Stage speedTestStage = new Stage();
        Speedtest speedTestView = new Speedtest();
        try {speedTestView.start(speedTestStage);}
        catch (Exception ex){}
    }

    @FXML
    public void onMigrateToMongoClicked(){
        IUserDAO dao = MongoDbUserDAO.getInstance();
        dao.migrate();
    }

    @FXML
    public void onMigrateToMySQLClicked(){
        IUserDAO dao = MySQLUserDAO.getInstance();
        dao.migrate();
    }
}
