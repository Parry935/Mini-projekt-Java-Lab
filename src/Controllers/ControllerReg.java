package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;

public class ControllerReg {

    @FXML
    private TextField Reg_emial;

    @FXML
    private TextField Reg_first_name;

    @FXML
    private TextField Reg_last_name;

    @FXML
    private TextField Reg_age;

    @FXML
    private TextField Reg_number;

    @FXML
    private TextField Reg_pass;

    @FXML
    private Button btn_reg;

    @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
        if (event.getSource() == btn_reg) {
            if (!Reg_emial.getText().equals("") &&
                    !Reg_first_name.getText().equals("") &&
                    !Reg_last_name.getText().equals("") &&
                    !Reg_age.getText().equals("") &&
                    !Reg_number.getText().equals("") &&
                    !Reg_pass.getText().equals("")) {

                if(Reg_emial.getText().contains("@"))
                {
                    addUserToDB();
                }

                else
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Błędny adres email!");
                    alert.showAndWait();
                }
            }

            else
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Wypełnij wszystkie pola!");
                alert.showAndWait();

            }
        }
    }

    private void addUserToDB() throws IOException {
        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());

        out.println("checkUserInDbByEmail " + Reg_emial.getText());
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String status = in.readLine();

        if (status.equals("True")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Email już istnieje!");
            alert.showAndWait();
        }

        if (status.equals("False")) {
            insertUser();
        }
    }

    private void insertUser() throws IOException {

            Socket s = new Socket("localhost", 9999);
            PrintWriter out = new PrintWriter(s.getOutputStream());

            out.println("addUserToDB " + Reg_emial.getText() + " " + Reg_first_name.getText() + " "
                    + Reg_last_name.getText() + " " + Reg_age.getText() + " "
                    + Reg_number.getText() + " " + Reg_pass.getText()
            );
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            if(in.readLine().equals("Succses"))
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Zarejestrowałeś się!");
                alert.showAndWait();
            }
    }
}
