package db.sample.coaches;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Coaches extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("coaches.fxml"));
        Image icon = new Image(getClass().getResourceAsStream("ua.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Тренеры");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
