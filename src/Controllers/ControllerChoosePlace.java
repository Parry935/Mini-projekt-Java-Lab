package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.shape.Rectangle;

import javax.swing.*;
import java.io.IOException;

public class ControllerChoosePlace {
    @FXML
    private Rectangle p1_1;

    @FXML
    private Rectangle p1_2;

    @FXML
    private Rectangle p1_3;

    @FXML
    private Rectangle p1_4;

    @FXML
    private Rectangle p1_5;

    @FXML
    private Rectangle p1_6;

    @FXML
    private Rectangle p1_7;

    @FXML
    private Rectangle p1_8;

    @FXML
    private Rectangle p1_9;

    @FXML
    private RadioButton r1_1;

    @FXML
    private RadioButton r1_2;

    @FXML
    private RadioButton r1_3;

    @FXML
    private RadioButton r1_4;

    @FXML
    private RadioButton r1_5;

    @FXML
    private RadioButton r1_6;

    @FXML
    private RadioButton r1_7;

    @FXML
    private RadioButton r1_8;

    @FXML
    private RadioButton r1_9;

    @FXML
    private Rectangle p2_1;

    @FXML
    private Rectangle p2_2;

    @FXML
    private Rectangle p2_3;

    @FXML
    private Rectangle p2_4;

    @FXML
    private Rectangle p2_5;

    @FXML
    private Rectangle p2_6;

    @FXML
    private Rectangle p2_7;

    @FXML
    private Rectangle p2_8;

    @FXML
    private Rectangle p2_9;

    @FXML
    private RadioButton r2_1;

    @FXML
    private RadioButton r2_2;

    @FXML
    private RadioButton r2_3;

    @FXML
    private RadioButton r2_4;

    @FXML
    private RadioButton r2_5;

    @FXML
    private RadioButton r2_6;

    @FXML
    private RadioButton r2_7;

    @FXML
    private RadioButton r2_8;

    @FXML
    private RadioButton r2_9;

    @FXML
    private Rectangle p3_1;

    @FXML
    private Rectangle p3_2;

    @FXML
    private Rectangle p3_3;

    @FXML
    private Rectangle p3_4;

    @FXML
    private Rectangle p3_5;

    @FXML
    private Rectangle p3_6;

    @FXML
    private Rectangle p3_7;

    @FXML
    private Rectangle p3_8;

    @FXML
    private Rectangle p3_9;

    @FXML
    private RadioButton r3_1;

    @FXML
    private RadioButton r3_2;

    @FXML
    private RadioButton r3_3;

    @FXML
    private RadioButton r3_4;

    @FXML
    private RadioButton r3_5;

    @FXML
    private RadioButton r3_6;

    @FXML
    private RadioButton r3_7;

    @FXML
    private RadioButton r3_8;

    @FXML
    private RadioButton r3_9;

    @FXML
    private Rectangle p4_1;

    @FXML
    private Rectangle p4_2;

    @FXML
    private Rectangle p4_3;

    @FXML
    private Rectangle p4_4;

    @FXML
    private Rectangle p4_5;

    @FXML
    private Rectangle p4_6;

    @FXML
    private Rectangle p4_7;

    @FXML
    private Rectangle p4_8;

    @FXML
    private Rectangle p4_9;

    @FXML
    private RadioButton r4_1;

    @FXML
    private RadioButton r4_2;

    @FXML
    private RadioButton r4_3;

    @FXML
    private RadioButton r4_4;

    @FXML
    private RadioButton r4_5;

    @FXML
    private RadioButton r4_6;

    @FXML
    private RadioButton r4_7;

    @FXML
    private RadioButton r4_8;

    @FXML
    private RadioButton r4_9;

    @FXML
    private Button btn_order;

    @FXML
    private RadioButton [][]  placeChoose = new RadioButton[][]{
            {r1_1, r1_2, r1_3, r1_4, r1_5, r1_6, r1_7, r1_8, r1_9},
            {r2_1, r2_2, r2_3, r2_4, r2_5, r2_6, r2_7, r2_8, r2_9},
            {r3_1, r3_2, r3_3, r3_4, r3_5, r3_6, r3_7, r3_8, r3_9},
            {r4_1, r4_2, r4_3, r4_4, r4_5, r4_6, r4_7, r4_8, r4_9},
            };
    @FXML
    private Rectangle [][] placeColor = new Rectangle[][]{
            {p1_1, p1_2, p1_3, p1_4, p1_5, p1_6, p1_7, p1_8, p1_9},
            {p2_1, p2_2, p2_3, p2_4, p2_5, p2_6, p2_7, p2_8, p2_9},
            {p3_1, p3_2, p3_3, p3_4, p3_5, p3_6, p3_7, p3_8, p3_9},
            {p4_1, p4_2, p4_3, p4_4, p4_5, p4_6, p4_7, p4_8, p4_9},
    };


    @FXML
    public void initialize() {
    }

    public void handleButtonAction(ActionEvent event) {
    }

}
