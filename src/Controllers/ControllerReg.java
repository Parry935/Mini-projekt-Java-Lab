package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
    void handleButtonAction(ActionEvent event) {
        if (event.getSource() == btn_reg) {
            if (!Reg_emial.getText().equals("") &&
                    !Reg_first_name.getText().equals("") &&
                    !Reg_last_name.getText().equals("") &&
                    !Reg_age.getText().equals("") &&
                    !Reg_number.getText().equals("") &&
                    !Reg_pass.getText().equals("")) {

                if(Reg_emial.getText().contains("@"))
                {
                    //addUserToDB();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Zarejestrowałeś się!");
                    alert.showAndWait();
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


    public void addUserToDB()
    {

        //DBconection db = new DBconection();
        //Connection connection = db.getContection();

        /*  String sql = "Select description from mydatabase.newtable;";
            Statement myStat = connection.createStatement();
            ResultSet rs = myStat.executeQuery(sql);

          while (rs.next()){
            btn.setText(rs.getString(1));}*/
    }
}
