/**
 * Created by ubuntu on 2015-10-24.
 */

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class JavaFXTest extends Application {

    private FXMLController fxmlController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));

        Scene scene = new Scene(root, 640, 400);

        stage.setTitle("FXML Welcome");
        stage.setScene(scene);

        stage.show();
        fxmlController = new FXMLController();
        fxmlController.initialize();


    }
    @FXML
    private void handleButtonAction(ActionEvent event) {
        // Button was clicked, do something...ff
    }


}
