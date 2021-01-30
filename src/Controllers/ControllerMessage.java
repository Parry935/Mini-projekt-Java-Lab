package Controllers;
import javafx.scene.control.*;
import javafx.fxml.FXML;
import javafx.scene.shape.Rectangle;
import source.Main;

import java.io.IOException;
import java.sql.SQLException;


public class ControllerMessage {

    @FXML
    private Label titleMes;

    @FXML
    private Label dateMes;

    @FXML
    private Label textMes;


    @FXML
    public void initialize() throws SQLException, IOException {

        titleMes.setText(Main.message.getTitle());
        dateMes.setText(Main.message.getDate());
        textMes.setText(Main.message.getText());
    }
}
