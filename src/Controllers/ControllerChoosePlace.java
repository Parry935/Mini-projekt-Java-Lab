package Controllers;

import Models.Movie;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import source.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
    private Button btn_returnToIndex;

    List<String> seatTaken = new ArrayList<String>();

    Color colorRed = Color.rgb(217, 54, 17);
    Color colorGreen = Color.rgb(101, 218, 17);

    @FXML
    public void initialize() throws SQLException, IOException {
        getPlaceForMovie();

        if(seatTaken.size() >0) {
            for (String item : seatTaken) {
                Rectangle r = StringToRectangle(item);
                r.setFill(colorRed);
            }
        }
    }

    private void getPlaceForMovie() throws SQLException, IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getPlaceForMovie " + Main.movie.getDate()
                + "#" + Main.movie.getTitle()
                + "#" + Main.movie.getType());
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("Error") && !buffer.equals("")){
            String[] placeFromDb = buffer.split("#");

            for(String item: placeFromDb){
                String[] places = item.split(" ");
                for(String p: places)
                    seatTaken.add(p);
            }
        }
    }

    @FXML
    public void returnToIndex(ActionEvent event) throws IOException, SQLException {

        if(event.getSource() == btn_returnToIndex) {
            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Index.fxml"));
            Parent root = null;
            try {
                root = (Parent) rootLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage window = (Stage) btn_order.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setTitle("Index");
            window.setScene(scene);
            window.show();
        }
    }

    public void orderEvent(ActionEvent event) throws IOException {

        if (event.getSource() == btn_order) {

            List<String> seatTakenByUser = new ArrayList<String>();

            CheckPlaces(seatTakenByUser);

            boolean correctChecked = true;

            for(String seat: seatTaken)
            {
                for(String seatUser : seatTakenByUser) {
                    if(seatUser.equals(seat))
                    {
                        correctChecked = false;
                        break;
                    }
                }

                if(correctChecked == false)
                    break;
            }

            if(correctChecked == false)
            {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error Dialog");
                alert.setHeaderText(null);
                alert.setContentText("Wybrałeś już zajete miejsca");
                alert.showAndWait();
            }

            else
            {
                addReservationToDb(seatTakenByUser);

                FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Index.fxml"));
                Parent root = null;
                try {
                    root = (Parent) rootLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Stage window = (Stage) btn_order.getScene().getWindow();
                Scene scene = new Scene(root);
                window.setTitle("Index");
                window.setScene(scene);
                window.show();
            }

        }
    }

    private void addReservationToDb(List<String> seatTakenByUser) throws IOException {

        String convertSeats = String.join(" ", seatTakenByUser);

        String idMovie = getMovieId(Main.movie.getDate(),Main.movie.getTitle(),Main.movie.getType());

        if(!idMovie.equals("Error")) {

            Socket s = new Socket("localhost", 9999);

            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.println("addReservationToDb " + String.valueOf(Main.userID) + "#" + idMovie +"#" + convertSeats);
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String buffer = in.readLine();
        }

    }

    private String getMovieId(String date, String title, String type) throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getMovieId " + date + "#" + title + "#" + type);
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        return buffer;
    }

    private Rectangle StringToRectangle(String place)
    {
        switch (place)
        {
            case "11":
                return p1_1;
            case "12":
                return p1_2;
            case "13":
                return p1_3;
            case "14":
                return p1_4;
            case "15":
                return p1_5;
            case "16":
                return p1_6;
            case "17":
                return p1_7;
            case "18":
                return p1_8;
            case "19":
                return p1_9;
            case "21":
                return p2_1;
            case "22":
                return p2_2;
            case "23":
                return p2_3;
            case "24":
                return p2_4;
            case "25":
                return p2_5;
            case "26":
                return p2_6;
            case "27":
                return p2_7;
            case "28":
                return p2_8;
            case "29":
                return p2_9;
            case "31":
                return p3_1;
            case "32":
                return p3_2;
            case "33":
                return p3_3;
            case "34":
                return p3_4;
            case "35":
                return p3_5;
            case "36":
                return p3_6;
            case "37":
                return p3_7;
            case "38":
                return p3_8;
            case "39":
                return p3_9;
            case "41":
                return p4_1;
            case "42":
                return p4_2;
            case "43":
                return p4_3;
            case "44":
                return p4_4;
            case "45":
                return p4_5;
            case "46":
                return p4_6;
            case "47":
                return p4_7;
            case "48":
                return p4_8;
            case "49":
                return p4_9;
        }

        return null;
    }

    private void CheckPlaces(List<String> seatTakenByUser)
    {
      if(r1_1.isSelected())
            seatTakenByUser.add("11");
      if(r1_2.isSelected())
            seatTakenByUser.add("12");
      if(r1_3.isSelected())
            seatTakenByUser.add("13");
      if(r1_4.isSelected())
            seatTakenByUser.add("14");
      if(r1_5.isSelected())
            seatTakenByUser.add("15");
      if(r1_6.isSelected())
            seatTakenByUser.add("16");
      if(r1_7.isSelected())
            seatTakenByUser.add("17");
      if(r1_8.isSelected())
            seatTakenByUser.add("18");
      if(r1_9.isSelected())
            seatTakenByUser.add("19");
      if(r2_1.isSelected())
            seatTakenByUser.add("21");
      if(r2_2.isSelected())
            seatTakenByUser.add("22");
      if(r2_3.isSelected())
            seatTakenByUser.add("23");
      if(r2_4.isSelected())
            seatTakenByUser.add("24");
      if(r2_5.isSelected())
            seatTakenByUser.add("25");
      if(r2_6.isSelected())
            seatTakenByUser.add("26");
      if(r2_7.isSelected())
            seatTakenByUser.add("27");
      if(r2_8.isSelected())
            seatTakenByUser.add("28");
      if(r2_9.isSelected())
            seatTakenByUser.add("29");
      if(r3_1.isSelected())
            seatTakenByUser.add("31");
      if(r3_2.isSelected())
            seatTakenByUser.add("32");
      if(r3_3.isSelected())
            seatTakenByUser.add("33");
      if(r3_4.isSelected())
            seatTakenByUser.add("34");
      if(r3_5.isSelected())
            seatTakenByUser.add("35");
      if(r3_6.isSelected())
            seatTakenByUser.add("36");
      if(r3_7.isSelected())
            seatTakenByUser.add("37");
      if(r3_8.isSelected())
            seatTakenByUser.add("38");
      if(r3_9.isSelected())
            seatTakenByUser.add("39");
      if(r4_1.isSelected())
            seatTakenByUser.add("41");
      if(r4_2.isSelected())
            seatTakenByUser.add("42");
      if(r4_3.isSelected())
            seatTakenByUser.add("43");
      if(r4_4.isSelected())
            seatTakenByUser.add("44");
      if(r4_5.isSelected())
            seatTakenByUser.add("45");
      if(r4_6.isSelected())
            seatTakenByUser.add("46");
      if(r4_7.isSelected())
            seatTakenByUser.add("47");
      if(r4_8.isSelected())
            seatTakenByUser.add("48");
      if(r4_9.isSelected())
            seatTakenByUser.add("49");
    }

    private RadioButton StringToRadioBtn(String place)
    {
        switch (place)
        {
            case "11":
                return r1_1;
            case "12":
                return r1_2;
            case "13":
                return r1_3;
            case "14":
                return r1_4;
            case "15":
                return r1_5;
            case "16":
                return r1_6;
            case "17":
                return r1_7;
            case "18":
                return r1_8;
            case "19":
                return r1_9;
            case "21":
                return r2_1;
            case "22":
                return r2_2;
            case "23":
                return r2_3;
            case "24":
                return r2_4;
            case "25":
                return r2_5;
            case "26":
                return r2_6;
            case "27":
                return r2_7;
            case "28":
                return r2_8;
            case "29":
                return r2_9;
            case "31":
                return r3_1;
            case "32":
                return r3_2;
            case "33":
                return r3_3;
            case "34":
                return r3_4;
            case "35":
                return r3_5;
            case "36":
                return r3_6;
            case "37":
                return r3_7;
            case "38":
                return r3_8;
            case "39":
                return r3_9;
            case "41":
                return r4_1;
            case "42":
                return r4_2;
            case "43":
                return r4_3;
            case "44":
                return r4_4;
            case "45":
                return r4_5;
            case "46":
                return r4_6;
            case "47":
                return r4_7;
            case "48":
                return r4_8;
            case "49":
                return r4_9;
        }

        return null;
    }
}
