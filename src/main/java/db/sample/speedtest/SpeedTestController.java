package db.sample.speedtest;

import db.sample.IDAO.IUserDAO;
import db.sample.DAO.mongoDbDAO.MongoDbUserDAO;
import db.sample.DAO.mySQLDAO.MySQLUserDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.time.Duration;
import java.time.LocalTime;

public class SpeedTestController {

    @FXML
    private Label mySqlRezult;
    @FXML
    private Label mongoRezult;
    @FXML
    private TextField usersAmount;


    @FXML
    public void addToMySQL(){
        LocalTime time1 = LocalTime.now();

        int amount;
        if(!usersAmount.getText().isEmpty()){
            amount = Integer.parseInt(usersAmount.getText());
            IUserDAO dao = MySQLUserDAO.getInstance();

            for(int i = 0; i<amount; i++) {
                try {
                    dao.insertUser("Test", "User", "imaTest", "Test123");
                } catch (Exception ex) {
                }
            }
        }

        LocalTime time2 = LocalTime.now();
        Duration duration = Duration.between(time1, time2);
        mySqlRezult.setText(duration.toString());
    }

    @FXML
    public void addToMongo(){
        LocalTime time1 = LocalTime.now();

        int amount;
        if(!usersAmount.getText().isEmpty()){
            amount = Integer.parseInt(usersAmount.getText());
            IUserDAO dao = MongoDbUserDAO.getInstance();

            for(int i = 0; i<amount; i++) {
                try {
                    dao.insertUser("Test", "User", "imaTest", "Test123");
                } catch (Exception ex) {
                }
            }
        }

        LocalTime time2 = LocalTime.now();
        Duration duration = Duration.between(time1, time2);
        mongoRezult.setText(duration.toString());
    }

}
