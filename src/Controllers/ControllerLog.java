package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ControllerLog {

    public static int idUser;

    @FXML
    private Button btn_log;

    @FXML
    private Button btn_reg;

    @FXML
    private TextField Log_email;
    //git
    @FXML
    private PasswordField Log_pass;

    public void handleButtonAction(ActionEvent event) throws IOException, SQLException {

        if(event.getSource() == btn_log) {

            Socket s = new Socket("localhost", 9999);

            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.println("login " + Log_email.getText() + " " + Log_pass.getText());
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String idUserString = in.readLine();

            int idUser = Integer.parseInt(idUserString);

            System.out.println(idUser);

            if(idUser == 0) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Błędny email lub hasło!");
                alert.showAndWait();
            }

            else {
                FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Index.fxml"));
                Parent root = (Parent) rootLoader.load();
                Stage window = (Stage) btn_log.getScene().getWindow();
                Scene scene = new Scene(root);
                window.setTitle("Index");
                window.setScene(scene);
                window.show();
            }
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
}
