package Controllers;

import DB.DBconection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    void handleButtonAction(ActionEvent event) throws SQLException {
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


    public void addUserToDB() throws java.sql.SQLException
    {

        DBconection db = new DBconection();
        Connection connection = db.getContection();

        String emailDB = null;
        String sqlQuery = "Select email from mydatabase.users where email= ?";

        PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery);
        preparedStmt.setString(1, Reg_emial.getText());
        ResultSet rs = preparedStmt.executeQuery();

            if (!rs.next()) {
                String sql = "INSERT INTO mydatabase.users (email, first_name, last_name, age, phone, pass)"
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                preparedStmt = connection.prepareStatement(sql);
                preparedStmt.setString(1, Reg_emial.getText());
                preparedStmt.setString(2, Reg_first_name.getText());
                preparedStmt.setString(3, Reg_last_name.getText());
                preparedStmt.setInt(4, Integer.parseInt(Reg_age.getText()));
                preparedStmt.setInt(5, Integer.parseInt(Reg_number.getText()));
                preparedStmt.setString(6, Reg_pass.getText());

                preparedStmt.execute();
                connection.close();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Zarejestrowałeś się!");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Email już istnieje!");
                alert.showAndWait();
        }
    }
}

//Statement myStat = connection.createStatement();
//ResultSet rs = myStat.executeQuery(sql);

//while (rs.next()){
//btn.setText(rs.getString(1));}