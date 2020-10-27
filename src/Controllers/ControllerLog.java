package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class ControllerLog {

    @FXML
    private Button btn_log;

    @FXML
    private Button btn_reg;

    @FXML
    private TextField Log_email;

    @FXML
    private PasswordField Log_pass;

    public void handleButtonAction(ActionEvent event) throws IOException {

        if(event.getSource() == btn_log) {
            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Index.fxml"));
            Parent root = (Parent) rootLoader.load();
            Stage window = (Stage) btn_log.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setTitle("Index");
            window.setScene(scene);
            window.show();
        }


        if(event.getSource() == btn_reg) {
            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Registration.fxml"));
            Parent root = (Parent) rootLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setTitle("Registration");
            stage.setScene(scene);
            stage.show();
        }

    }

    private void checkUserInDB() {
    }
}
