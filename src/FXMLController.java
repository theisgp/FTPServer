import java.awt.*;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.ResourceBundle;

//import Old.Controller;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import org.apache.ftpserver.ftplet.FtpException;

import javax.swing.*;


public class FXMLController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    public MenuItem menuItemQuit;

    @FXML
    private Button buttonServerStop;

    @FXML
    private Button buttonServerStart;

    @FXML
    private Label labelServerRunning;

    @FXML
    private ListView<MyUser> listViewUsers;

    @FXML
    private TextField textFieldDirectory;

    @FXML
    private TextField textFieldUsername;

    @FXML
    private TextField textFieldPassword;

    @FXML
    private TextField textFieldPort;

//    private Controller controller;

    public FXMLController() {
    }

    @FXML
    void initialize() {
        assert menuItemQuit != null : "fx:id=\"menuItemQuit\" was not injected: check your FXML file 'UserInterface.fxml'.";
        assert buttonServerStop != null : "fx:id=\"buttonServerStop\" was not injected: check your FXML file 'UserInterface.fxml'.";
        assert buttonServerStart != null : "fx:id=\"buttonServerStart\" was not injected: check your FXML file 'UserInterface.fxml'.";

        buttonServerStop.setVisible(false);

        menuItemQuit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });

        buttonServerStart.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    MainWindow_JavaFX.serverManager.startServer();
                    try {
                        labelServerRunning.setText("Server is running! \n" + InetAddress.getLocalHost().getHostAddress()+":"+MainWindow_JavaFX.serverManager.getPort());
                    } catch (UnknownHostException e) {
                        labelServerRunning.setText("Server is running!");
                    };
                    buttonServerStop.setVisible(true);
                    buttonServerStart.setVisible(false);

                } catch (FtpException e) {
                    e.printStackTrace();
                }
            }
        });
        buttonServerStop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!MainWindow_JavaFX.serverManager.getServer().isStopped()) {
                    try {
                        MainWindow_JavaFX.serverManager.stopServer();
                        labelServerRunning.setText("Server is stopped!\n" + "Restart is not supported :(( \n (YET!)");
                        buttonServerStop.setVisible(false);
                        System.out.println("Server stopped!");
                    } catch (FtpException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        textFieldPort.setText(MainWindow_JavaFX.serverManager.getPort()+"");


        MainWindow_JavaFX.serverManager.getMasterUserManager().createBaseUser("Jannik", "1234", "/tmp/Jannik");
        MainWindow_JavaFX.serverManager.getMasterUserManager().createBaseUser("Theis", "1234", "/tmp/Theis");

        ObservableList<MyUser> users = FXCollections.observableList(MainWindow_JavaFX.serverManager.getMasterUserManager().getMyUsers());

        listViewUsers.setItems(users);

        listViewUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                textFieldUsername.setText(listViewUsers.getSelectionModel().getSelectedItem().getName());
                textFieldPassword.setText(listViewUsers.getSelectionModel().getSelectedItem().getPassword());
                textFieldDirectory.setText(listViewUsers.getSelectionModel().getSelectedItem().getHomeDirectory());
                textFieldPassword.setDisable(false);
            }
        });

    }


}
