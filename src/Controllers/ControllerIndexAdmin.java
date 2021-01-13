package Controllers;

import Models.Movie;
import ViewModel.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import source.Clock;
import source.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

public class ControllerIndexAdmin {

    @FXML
    private Button btn_logout;

    @FXML
    private TableView<MovieVM> tableMovie;

    @FXML
    private TableColumn<MovieVM, String> idMovie;

    @FXML
    private TableColumn<MovieVM, String> dateMovie;

    @FXML
    private TableColumn<MovieVM, String> titleMovie;

    @FXML
    private TableColumn<MovieVM, String> typeMovie;

    @FXML
    private TableColumn delMovie;

    @FXML
    private DatePicker addMovieDate;

    @FXML
    private TextField addMovieTitle;

    @FXML
    private TextField addMovieType;

    @FXML
    private Button btn_addMovie;

    @FXML
    private TextField addMovieTime;

    @FXML
    private TableView<UsersVM> tableUsers;

    @FXML
    private TableColumn<UsersVM, String> idUser;

    @FXML
    private TableColumn<UsersVM, String> emailUser;

    @FXML
    private TableColumn<UsersVM, String> firstNameUser;

    @FXML
    private TableColumn<UsersVM, String> lastNameUser;

    @FXML
    private TableColumn<UsersVM, String> ageUser;

    @FXML
    private TableColumn<UsersVM, String> phoneUser;

    @FXML
    private TableView<ReservationVM> tableReservation;

    @FXML
    private TableColumn<ReservationVM, String> idReservation;

    @FXML
    private TableColumn<ReservationVM, String> idMovieRes;

    @FXML
    private TableColumn<ReservationVM, String> idUserRes;

    @FXML
    private TableColumn<ReservationVM, String> placeRes;

    @FXML
    private TableColumn<ReservationVM, String> confirmRes;

    @FXML
    private TableColumn delRes;

    ObservableList<MovieVM> movies = FXCollections.observableArrayList();
    ObservableList<UsersVM> users = FXCollections.observableArrayList();
    ObservableList<ReservationVM> reservations = FXCollections.observableArrayList();


    @FXML
    public void initialize() throws SQLException, IOException {


        getMovies();

        idMovie.setCellValueFactory(new PropertyValueFactory<MovieVM,String>("id"));
        dateMovie.setCellValueFactory(new PropertyValueFactory<MovieVM,String>("date"));
        titleMovie.setCellValueFactory(new PropertyValueFactory<MovieVM,String>("title"));
        typeMovie.setCellValueFactory(new PropertyValueFactory<MovieVM,String>("type"));


        //Button to row
        Callback<TableColumn<MovieVM, String>, TableCell<MovieVM, String>> callFactory = (param) ->{
            final TableCell<MovieVM,String> cell = new TableCell<MovieVM,String>(){
                @Override
                public void updateItem(String item,boolean empty)
                {
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button editButton = new Button(("Usuń"));
                        editButton.setOnAction(event -> {
                            MovieVM m= getTableView().getItems().get(getIndex());

                            try {
                                deleteMovie(m.getId());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/IndexAdmin.fxml"));
                            Parent root = null;
                            try {
                                root = (Parent) rootLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage stage = (Stage) tableMovie.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setTitle("IndexAdmin");
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


        delMovie.setCellFactory(callFactory);

        tableMovie.setItems(movies);


        //users

        getUsers();

        idUser.setCellValueFactory(new PropertyValueFactory<UsersVM,String>("id"));
        emailUser.setCellValueFactory(new PropertyValueFactory<UsersVM,String>("email"));
        firstNameUser.setCellValueFactory(new PropertyValueFactory<UsersVM,String>("first_name"));
        lastNameUser.setCellValueFactory(new PropertyValueFactory<UsersVM,String>("last_name"));
        ageUser.setCellValueFactory(new PropertyValueFactory<UsersVM,String>("age"));
        phoneUser.setCellValueFactory(new PropertyValueFactory<UsersVM,String>("number"));

        tableUsers.setItems(users);


        getReservations();

        Callback<TableColumn<ReservationVM, String>, TableCell<ReservationVM, String>> callFactoryReservationDel = (param) ->{
            final TableCell<ReservationVM,String> cell = new TableCell<ReservationVM,String>(){
                @Override
                public void updateItem(String item,boolean empty)
                {
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button editButton = new Button(("Usuń"));
                        editButton.setOnAction(event -> {
                            ReservationVM r= getTableView().getItems().get(getIndex());

                            try {
                                deleteReservation(r.getId_movie(), r.getId_user(),r.getPlace());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/IndexAdmin.fxml"));
                            Parent root = null;
                            try {
                                root = (Parent) rootLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage window = (Stage) btn_logout.getScene().getWindow();
                            Scene scene = new Scene(root);
                            window.setTitle("Index");
                            window.setScene(scene);
                            window.show();
                        });

                        setGraphic(editButton);
                        setText(null);
                    }
                }

            };
            return cell;

        };

        delRes.setCellFactory(callFactoryReservationDel);

        idReservation.setCellValueFactory(new PropertyValueFactory<ReservationVM,String>("id_reservation"));
        idMovieRes.setCellValueFactory(new PropertyValueFactory<ReservationVM,String>("id_movie"));
        idUserRes.setCellValueFactory(new PropertyValueFactory<ReservationVM,String>("id_user"));
        placeRes.setCellValueFactory(new PropertyValueFactory<ReservationVM,String>("place"));
        confirmRes.setCellValueFactory(new PropertyValueFactory<ReservationVM,String>("confirm"));

        tableReservation.setItems(reservations);


    }

    public void addMovieEvent(ActionEvent event) throws IOException, SQLException {

        if(event.getSource() == btn_addMovie) {
            if (!addMovieTime.getText().equals("") &&
                    !addMovieTitle.getText().equals("") &&
                    !addMovieType.getText().equals("")){

                String date = addMovieDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                date += "#" + addMovieTime.getText();

                System.out.println(date);
                System.out.println(addMovieTitle.getText());
                System.out.println(addMovieType.getText());

                String result = addMovieToDb(date,addMovieTitle.getText(),addMovieType.getText());


                if(result.equals("Succses"))
                {
                    FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/IndexAdmin.fxml"));
                    Parent root = (Parent) rootLoader.load();
                    Stage window = (Stage) btn_addMovie.getScene().getWindow();
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

    private String addMovieToDb(String date, String title, String type) throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("addMovieToDB " + date + " " + title + " " + type);
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        return buffer;
    }

    private void deleteMovie(String id) throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("deleteMovieById " + id);
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("Succses"))
            System.out.println("Error from server");
    }


    private void getMovies() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getMoviesWithId");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        String[] moviesFromServer = buffer.split("#");

        for (String item : moviesFromServer) {
            String[] movie = item.split("@");
            MovieVM movieToList = new MovieVM(movie[0], movie[1], movie[2], movie[3]);
            movies.add(movieToList);
        }
    }

    private void getReservations() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getReservations");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        String[] reservationsFromDb = buffer.split("#");

        String confirmTemp = "Nie";

        for (String item : reservationsFromDb) {
            String[] reservation = item.split("@");

            if(reservation[4].equals("1"))
                confirmTemp = "Tak";
            else
                confirmTemp = "Nie";

            ReservationVM reservationToList = new ReservationVM(
                    Integer.parseInt(reservation[0]),
                    Integer.parseInt(reservation[1]),
                    Integer.parseInt(reservation[2]),
                    reservation[3],
                    confirmTemp);

            reservations.add(reservationToList);
        }
    }

        private void getUsers() throws IOException {

            Socket s = new Socket("localhost", 9999);

            PrintWriter out = new PrintWriter(s.getOutputStream());
            out.println("getUsers");
            out.flush();

            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

            String buffer = in.readLine();

            String[] usersFromServer = buffer.split("#");

            for (String item : usersFromServer) {
                String[] user = item.split("&");
                UsersVM userToList = new UsersVM(user[0], user[1], user[2], user[3],user[4],user[5]);
                users.add(userToList);
            }
    }

    @FXML
    public void logout(ActionEvent event) throws IOException, SQLException {

        if(event.getSource() == btn_logout) {
            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Login.fxml"));
            Parent root = (Parent) rootLoader.load();
            Stage window = (Stage) btn_logout.getScene().getWindow();
            Scene scene = new Scene(root);
            window.setTitle("Index");
            window.setScene(scene);
            window.show();
        }
    }

    private void deleteReservation(int id_movie, int id_user, String place) throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("deleteReservation " + String.valueOf(id_movie) + "#" + String.valueOf(id_user) + "#" + place);
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("Succses"))
            System.out.println("Error from server");

    }


}
