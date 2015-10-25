/**
 * Created by ubuntu on 2015-10-24.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainWindow_JavaFX extends Application {

    public static ServerManager serverManager;


    public static void main(String[] args) {
        Application.launch(MainWindow_JavaFX.class, (java.lang.String[])null);
    }

    public MainWindow_JavaFX(){
        serverManager = new ServerManager();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("UserInterface.fxml"));

        Scene scene = new Scene(root, 800, 450);

        stage.setTitle("Gangsta FTPServer");
        stage.setScene(scene);

        stage.show();
    }
}
