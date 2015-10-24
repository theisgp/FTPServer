import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextField;

public class FXMLController {
    @FXML private MenuItem helpMenuItem;


    @FXML
    public void initialize() {
        helpMenuItem = new MenuItem();
        System.out.println(helpMenuItem.getText());
    }
}