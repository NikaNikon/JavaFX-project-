package db.sample.registration;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Registration extends Application
{
    public static Stage prStage;
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("registrationForm.fxml"));
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("ua.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Регистрация");
        primaryStage.setScene(new Scene(root));
        prStage = primaryStage;
        primaryStage.show();
    }
}
