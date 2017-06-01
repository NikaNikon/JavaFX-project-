package db.sample.groups;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;

public class Groups extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getResource("groups.fxml"));
        FXMLLoader loader = new FXMLLoader();//(getClass().getResource("groups.fxml"));
        loader.setLocation(Groups.class.getClassLoader().getResource("groups.fxml"));
        AnchorPane  root = (AnchorPane)  loader.load();
        GroupsController controller = (GroupsController) loader.getController();
        Image icon = new Image(getClass().getClassLoader().getResourceAsStream("ua.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setTitle("Список групп");

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        controller.fillList(null);
        controller.setCheckbox();
        primaryStage.show();
    }
}
