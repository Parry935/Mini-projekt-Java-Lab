package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import Models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import source.Clock;


public class ControllerIndex {

    ControllerLog controllerLog;
    public static Movie chooseMovie;

    @FXML
    private TableView<Movie> tableMovies;

    @FXML
    private TableColumn<Movie, String> dateMovies;

    @FXML
    private TableColumn<Movie, String> titleMovies;

    @FXML
    private TableColumn<Movie, String> typeMovies;

    @FXML
    private TableColumn reserv;

    @FXML
    private TableView<Movie> tableReservation;

    @FXML
    private TableColumn<Movie, String> dateRes;

    @FXML
    private TableColumn<Movie, String> titleRes;

    @FXML
    private TableColumn<Reservation, String> placeRes;

    @FXML
    private TableColumn timeRes;

    @FXML
    private TableColumn confirmRes;

    @FXML
    private TableColumn delRes;

    @FXML
    private Label timeLabel;

    @FXML
    private TableView<Movie> tableTicets;

    @FXML
    private TableColumn<Movie, String> dateTicets;

    @FXML
    private TableColumn<Movie, String> titleTicets;

    @FXML
    private TableColumn<Movie, String> placeTicets;

    @FXML
    private TableColumn generateTicets;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    ObservableList<Movie> movies = FXCollections.observableArrayList();
    @FXML
    public void initialize() throws SQLException, IOException {

        getMovies();

        dateMovies.setCellValueFactory(new PropertyValueFactory<Movie,String>("date"));
        titleMovies.setCellValueFactory(new PropertyValueFactory<Movie,String>("title"));
        typeMovies.setCellValueFactory(new PropertyValueFactory<Movie,String>("type"));

        Callback<TableColumn<Movie, String>, TableCell<Movie, String>> callFactory = (param) ->{
            final TableCell<Movie,String> cell = new TableCell<Movie,String>(){
            @Override
            public void updateItem(String item,boolean empty)
            {
                super.updateItem(item,empty);
                if(empty){
                    setGraphic(null);
                    setText(null);
                }
                else{
                    final Button editButton = new Button(("Rezerwuj"));
                    editButton.setOnAction(event -> {
                        Movie m= getTableView().getItems().get(getIndex());
                        chooseMovie = m;
                        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/ChoosePlace.fxml"));
                        Parent root = null;
                        try {
                            root = (Parent) rootLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Stage stage = new Stage();
                        Scene scene = new Scene(root);
                        stage.setTitle("Order - " + m.getTitle() + "   Data: " + m.getDate());
                        stage.setScene(scene);
                        stage.show();
                    });

                    setGraphic(editButton);
                    setText(null);
                }
            }

            };
            return cell;

        };


        reserv.setCellFactory(callFactory);

        tableMovies.setItems(movies);
    }

    private void getMovies() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getMovies");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        System.out.println(buffer);

        String[] moviesFromServer = buffer.split("#");

        for(String item: moviesFromServer){
            String[] movie = item.split("@");
            Movie movieToList = new Movie(movie[0],movie[1],movie[2]);
            movies.add(movieToList);
        }

    }

}
