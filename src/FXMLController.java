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
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
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
    private Button buttonUserDelete;

    @FXML
    private Button buttonUserNew;

    @FXML
    private Button buttonUserSave;

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

    private ObservableList<MyUser> users;
//    private Controller controller;

    private MyUser tmpUser;
    private boolean isCreatingUser;
    private boolean verifingDelete;

    public FXMLController() {
        isCreatingUser = false;
        verifingDelete = false;
    }

    @FXML
    void initialize() {
//        assert menuItemQuit != null : "fx:id=\"menuItemQuit\" was not injected: check your FXML file 'UserInterface.fxml'.";
//        assert buttonServerStop != null : "fx:id=\"buttonServerStop\" was not injected: check your FXML file 'UserInterface.fxml'.";
//        assert buttonServerStart != null : "fx:id=\"buttonServerStart\" was not injected: check your FXML file 'UserInterface.fxml'.";

        buttonServerStop.setDisable(true);

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
                        labelServerRunning.setText("Server is running! \n" + InetAddress.getLocalHost().getHostAddress() + ":" + MainWindow_JavaFX.serverManager.getPort());
                    } catch (UnknownHostException e) {
                        labelServerRunning.setText("Server is running!");
                    }
                    ;
                    buttonServerStop.setDisable(false);
                    buttonServerStart.setDisable(true);

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
                        buttonServerStop.setDisable(true);
                        System.out.println("Server stopped!");
                    } catch (FtpException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        buttonUserDelete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (!verifingDelete) {
                    buttonUserDelete.setText("Sure?");
                    verifingDelete = true;
                    listViewUsers.requestFocus();
                } else if (verifingDelete) {
                    users.remove(listViewUsers.getSelectionModel().getSelectedItem());
                    updateTextFields();
                    buttonUserDelete.setText("Delete");
                    listViewUsers.requestFocus();
                    verifingDelete = false;
                }
            }
        });

        buttonUserNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                tmpUser = new MyUser("", "", "");
                users.add(tmpUser);
                SelectionModel selectionModel = listViewUsers.getSelectionModel();
                selectionModel.select(tmpUser);

                textFieldUsername.setDisable(false);
                textFieldPassword.setDisable(false);
                textFieldUsername.clear();
                textFieldPassword.clear();
                textFieldDirectory.clear();

                textFieldUsername.requestFocus();
            }
        });

        buttonUserSave.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if(listViewUsers.getSelectionModel().getSelectedItem() == tmpUser) {
                    MyUser newUser = new MyUser(textFieldUsername.getText(), textFieldPassword.getText(), "");
                    users.add(newUser);
                    users.remove(tmpUser);
                    System.out.println(textFieldUsername.getText() + textFieldPassword.getText());
                    SelectionModel selectionModel = listViewUsers.getSelectionModel();
                    selectionModel.select(newUser);
                    updateTextFields();
                    listViewUsers.requestFocus();
                }
                else {
                    listViewUsers.getSelectionModel().getSelectedItem().setPassword(textFieldPassword.getText());
                }
            }
        });

        textFieldPort.setText(MainWindow_JavaFX.serverManager.getPort() + "");


//        MainWindow_JavaFX.serverManager.getMasterUserManager().createBaseUser("Jannik", "1234", "/tmp/Jannik");
//        MainWindow_JavaFX.serverManager.getMasterUserManager().createBaseUser("Theis", "1234", "/tmp/Theis");

        users = FXCollections.observableList(MainWindow_JavaFX.serverManager.getMasterUserManager().getMyUsers());

        listViewUsers.setItems(users);

        textFieldUsername.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
/*                users.remove(tmpUser);
                tmpUser.setName(textFieldUsername.getText());
                users.add(tmpUser);*/
            }
        });

        listViewUsers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                updateTextFields();
                textFieldPassword.setDisable(false);
                buttonUserDelete.setDisable(false);
            }
        });

    }

    private void updateTextFields() {
        if (listViewUsers.getSelectionModel().getSelectedItem() != null) {
            textFieldUsername.setText(listViewUsers.getSelectionModel().getSelectedItem().getName());
            textFieldPassword.setText(listViewUsers.getSelectionModel().getSelectedItem().getPassword());
            textFieldDirectory.setText(listViewUsers.getSelectionModel().getSelectedItem().getHomeDirectory());
        } else {
            textFieldUsername.setText("");
            textFieldPassword.setText("");
            textFieldDirectory.setText("");
        }


    }


}
