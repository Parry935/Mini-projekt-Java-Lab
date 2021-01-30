package Controllers;

import ViewModel.MovieVM;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import source.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ControllerUpdateMovie {

    @FXML
    private DatePicker upMovieDate;

    @FXML
    private TextField upMovieTitle;

    @FXML
    private TextField upMovieType;

    @FXML
    private Button btn_upMovie;

    @FXML
    private TextField upMovieTime;

    @FXML
    private Button btn_upMovieBack;

    MovieVM movie;

    @FXML
    public void initialize() throws SQLException, IOException {
        movie = new MovieVM(Main.movieToUpdate.getId(),
                Main.movieToUpdate.getDate(),
                Main.movieToUpdate.getTitle(),
                Main.movieToUpdate.getType());

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String[] strDate = movie.getDate().split(" ",2);
        LocalDate date = LocalDate.parse(strDate[0]);

        upMovieDate.setValue(date);
        upMovieTime.setText(strDate[1]);
        upMovieTitle.setText(movie.getTitle());
        upMovieType.setText(movie.getType());
    }

    @FXML
    void backToIndexAdminEvent(ActionEvent event) {

        if(event.getSource() == btn_upMovieBack) {
            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/IndexAdmin.fxml"));
            Parent root = null;
            try {
                root = (Parent) rootLoader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Stage window = (Stage) btn_upMovieBack.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setTitle("IndexAdmin");
            window.setScene(scene);
            window.show();
        }
    }


    @FXML
    public void updateMovieEvent(ActionEvent event) throws IOException {
        if(event.getSource() == btn_upMovie) {
            if (!upMovieTime.getText().equals("") &&
                    !upMovieTitle.getText().equals("") &&
                    !upMovieType.getText().equals("")){

                String date = upMovieDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                date += "#" + upMovieTime.getText();


                String result = upMovieToDb(date,upMovieTitle.getText(),upMovieType.getText());


                if(result.equals("Succses"))
                {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText(null);
                    alert.setContentText("Poprawnie zedytowano film");
                    alert.showAndWait();


                    FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/IndexAdmin.fxml"));
                    Parent root = (Parent) rootLoader.load();
                    Stage window = (Stage) btn_upMovie.getScene().getWindow();
                    Scene scene = new Scene(root);
                    window.setTitle("IndexAdmin");
                    window.setScene(scene);
                    window.show();
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

    private String upMovieToDb(String date, String title, String type) throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("upMovieToDB " + date + "@" + title + "@" + type + "@" + movie.getId());
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        return buffer;
    }
}
