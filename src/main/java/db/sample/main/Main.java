package db.sample.main;

import db.sample.IDAO.IUserDAO;
import db.sample.factoryPattern.CreatorMySQLHibernateUserDAO;
import db.sample.factoryPattern.CreatorUserDAO;
import db.sample.registration.Client;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

import javax.print.DocFlavor;
import java.io.File;
import java.net.URL;

public class Main extends Application {
    public static CreatorUserDAO creatorUserDao = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("ua.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Главное меню");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        /*Client client = (new Client.Builder("Hibernate", "Hibernate", "hibernate", "Hibernate123").
                setPhone("0990372222").
                setEmail("hibernate@nure.ua").build());
        Main.creatorUserDao = new CreatorMySQLHibernateUserDAO();
        IUserDAO dao = Main.creatorUserDao.factoryUserDAO();
        dao.insertUser(client);
        System.out.println("success");*/
    }



    public static void main(String[] args) {
        launch(args);
    }
}
