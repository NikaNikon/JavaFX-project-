package db.sample.speedtest;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class Speedtest extends Application

{
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("speedtest.fxml"));
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Speedtest.class.getResource("speedtest.fxml"));
        AnchorPane root = (AnchorPane)  loader.load();
        SpeedTestController controller = (SpeedTestController) loader.getController();
        Image icon = new Image(getClass().getResourceAsStream("ua.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Speedtest");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
