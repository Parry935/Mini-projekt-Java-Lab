package Controllers;

import ViewModel.MovieVM;
import ViewModel.ReservationConfirmVM;
import ViewModel.ReservationToConfirmVM;
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
import Models.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import source.Clock;
import source.Main;


public class ControllerIndex {

    @FXML
    private Button btn_logout;

    @FXML
    private TableView<Movie> tableMovies;

    @FXML
    private TableColumn<Movie, String> dateMovies;

    @FXML
    private TableColumn<Movie, String> titleMovies;

    @FXML
    private TableColumn<Movie, String> typeMovies;

    @FXML
    private TableColumn reservMovies;

    @FXML
    private TableView<ReservationToConfirmVM> tableReservation;

    @FXML
    private TableColumn<ReservationToConfirmVM, String> dateRes;

    @FXML
    private TableColumn<ReservationToConfirmVM, String> titleRes;

    @FXML
    private TableColumn<ReservationToConfirmVM, String> placeRes;

    @FXML
    private TableColumn<ReservationToConfirmVM, String>  timeRes;

    @FXML
    private TableColumn confirmRes;

    @FXML
    private TableColumn delRes;

    @FXML
    private Label timeLabel;

    @FXML
    private TableView<ReservationConfirmVM> tableTicets;

    @FXML
    private TableColumn<ReservationConfirmVM, String> dateTicets;

    @FXML
    private TableColumn<ReservationConfirmVM, String> titleTicets;

    @FXML
    private TableColumn<ReservationConfirmVM, String> placeTicets;

    @FXML
    private TableColumn generateTicets;

    @FXML
    private TableView<Message> tableMessages;

    @FXML
    private TableColumn<Message, String> titleMessages;

    @FXML
    private TableColumn<Message, String> dateMessages;

    @FXML
    private TableColumn showMessages;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    ObservableList<Movie> movies = FXCollections.observableArrayList();
    ObservableList<ReservationToConfirmVM> reservationsVM = FXCollections.observableArrayList();
    ObservableList<ReservationConfirmVM> reservationsConfirmVM = FXCollections.observableArrayList();
    ObservableList<Message> messages = FXCollections.observableArrayList();

    List<MovieVM> reservedMovies = new ArrayList<MovieVM>();

    List<Reservation> reservations = new ArrayList<Reservation>();

    @FXML
    public void initialize() throws SQLException, IOException, ParseException {

        executorService.execute(new Clock(timeLabel));

        getMovies();

        dateMovies.setCellValueFactory(new PropertyValueFactory<Movie,String>("date"));
        titleMovies.setCellValueFactory(new PropertyValueFactory<Movie,String>("title"));
        typeMovies.setCellValueFactory(new PropertyValueFactory<Movie,String>("type"));


        //Button to row
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
                        Main.movie = m;
                        FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/ChoosePlace.fxml"));
                        Parent root = null;
                        try {
                            root = (Parent) rootLoader.load();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        if(root != null) {
                            Stage stage = (Stage) btn_logout.getScene().getWindow();
                            Scene scene = new Scene(root);
                            stage.setTitle("Order - " + m.getTitle() + "   Data: " + m.getDate());
                            stage.setScene(scene);
                            stage.show();
                        }
                    });

                    setGraphic(editButton);
                    setText(null);
                }
            }

            };
            return cell;

        };


        reservMovies.setCellFactory(callFactory);

        tableMovies.setItems(movies);

        getMoviesWithId();
        getReservationForUser();
        initViewReservationToConfirm();

        dateRes.setCellValueFactory(new PropertyValueFactory<ReservationToConfirmVM,String>("date"));
        titleRes.setCellValueFactory(new PropertyValueFactory<ReservationToConfirmVM,String>("title"));
        placeRes.setCellValueFactory(new PropertyValueFactory<ReservationToConfirmVM,String>("place"));
        timeRes.setCellValueFactory(new PropertyValueFactory<ReservationToConfirmVM,String>("dateToConfirm"));


        Callback<TableColumn<ReservationToConfirmVM, String>, TableCell<ReservationToConfirmVM, String>> callFactoryReservationConfirm = (param) ->{
            final TableCell<ReservationToConfirmVM,String> cell = new TableCell<ReservationToConfirmVM,String>(){
                @Override
                public void updateItem(String item,boolean empty)
                {
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button editButton = new Button(("Potwierdz"));
                        editButton.setOnAction(event -> {
                            ReservationToConfirmVM r= getTableView().getItems().get(getIndex());

                            try {
                                updateReservation(Integer.parseInt(r.getIdMovie()),Main.userID,r.getPlace());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Index.fxml"));
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


        Callback<TableColumn<ReservationToConfirmVM, String>, TableCell<ReservationToConfirmVM, String>> callFactoryReservationDel = (param) ->{
            final TableCell<ReservationToConfirmVM,String> cell = new TableCell<ReservationToConfirmVM,String>(){
                @Override
                public void updateItem(String item,boolean empty)
                {
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button editButton = new Button(("Anuluj"));
                        editButton.setOnAction(event -> {
                            ReservationToConfirmVM r= getTableView().getItems().get(getIndex());

                            try {
                                deleteReservation(Integer.parseInt(r.getIdMovie()),Main.userID,r.getPlace());
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Index.fxml"));
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

        confirmRes.setCellFactory(callFactoryReservationConfirm);
        delRes.setCellFactory(callFactoryReservationDel);
        tableReservation.setItems(reservationsVM);


        initViewReservationConfirm();

        Callback<TableColumn<ReservationConfirmVM, String>, TableCell<ReservationConfirmVM, String>> callFactoryGenerate = (param) ->{
            final TableCell<ReservationConfirmVM,String> cell = new TableCell<ReservationConfirmVM,String>(){
                @Override
                public void updateItem(String item,boolean empty)
                {
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button editButton = new Button(("Generuj"));
                        editButton.setOnAction(event -> {
                            ReservationConfirmVM r= getTableView().getItems().get(getIndex());

                            Main.generateReservationTicket = r;

                            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Ticket.fxml"));
                            Parent root = null;
                            try {
                                root = (Parent) rootLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage window = new Stage();
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

        dateTicets.setCellValueFactory(new PropertyValueFactory<ReservationConfirmVM,String>("date"));
        titleTicets.setCellValueFactory(new PropertyValueFactory<ReservationConfirmVM,String>("title"));
        placeTicets.setCellValueFactory(new PropertyValueFactory<ReservationConfirmVM,String>("place"));

        generateTicets.setCellFactory(callFactoryGenerate);

        tableTicets.setItems(reservationsConfirmVM);

        getMessages();


        Callback<TableColumn<Message, String>, TableCell<Message, String>> callFactoryMessegeShow = (param) ->{
            final TableCell<Message,String> cell = new TableCell<Message,String>(){
                @Override
                public void updateItem(String item,boolean empty)
                {
                    super.updateItem(item,empty);
                    if(empty){
                        setGraphic(null);
                        setText(null);
                    }
                    else{
                        final Button editButton = new Button(("Pokaż wiadomość"));
                        editButton.setOnAction(event -> {
                            Message m= getTableView().getItems().get(getIndex());

                            Main.message = m;

                            FXMLLoader rootLoader = new FXMLLoader(getClass().getResource("../FXML/Message.fxml"));
                            Parent root = null;
                            try {
                                root = (Parent) rootLoader.load();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            Stage window = new Stage();
                            Scene scene = new Scene(root);
                            window.setTitle("Message");
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


        showMessages.setCellFactory(callFactoryMessegeShow);

        titleMessages.setCellValueFactory(new PropertyValueFactory<Message,String>("title"));
        dateMessages.setCellValueFactory(new PropertyValueFactory<Message,String>("date"));

        tableMessages.setItems(messages);

    }

    private void getMovies() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getMovies");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("")) {
        String[] moviesFromServer = buffer.split("#");

            for (String item : moviesFromServer) {
                String[] movie = item.split("@");
                Movie movieToList = new Movie(movie[0], movie[1], movie[2]);
                movies.add(movieToList);
            }
        }

    }

    private void getReservationForUser() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getReservationForUser " + String.valueOf(Main.userID));
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("")) {

        String[] reservationFromDb = buffer.split("#");

            for (String item : reservationFromDb) {
                String[] reservation = item.split("@");
                Reservation reservationToList = new Reservation(
                        Main.userID,
                        Integer.parseInt(reservation[0]),
                        reservation[1],
                        Integer.parseInt(reservation[2]));
                reservations.add(reservationToList);
            }
        }

    }

    private void getMoviesWithId() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getMoviesWithId");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("")) {
        String[] moviesFromServer = buffer.split("#");

            for (String item : moviesFromServer) {
                String[] movie = item.split("@");
                MovieVM movieToList = new MovieVM(movie[0], movie[1], movie[2], movie[3]);
                reservedMovies.add(movieToList);
            }
        }
    }


    private void initViewReservationConfirm() throws IOException {

        for(Reservation res : reservations)
        {
            for(MovieVM m : reservedMovies)
            {
                if(Integer.parseInt(m.getId())==res.getId_movie())
                {
                    if(res.getConfirm() == 1) {

                        ReservationConfirmVM resToView = new ReservationConfirmVM(m.getDate(), m.getTitle(), res.getPlace(),m.getId());
                        reservationsConfirmVM.add(resToView);
                    }
                }
            }
        }

    }

    private void initViewReservationToConfirm() throws IOException, ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for(Reservation res : reservations)
        {
            for(MovieVM m : reservedMovies)
            {
                if(Integer.parseInt(m.getId())==res.getId_movie())
                {
                    if(res.getConfirm() == 0) {

                        String [] dateString = m.getDate().split(" ", 2);

                        LocalDate date = LocalDate.parse(dateString[0],formatter);

                        LocalDate dateToView = date.minusDays(1);


                        if(dateToView.isAfter(LocalDate.now())) {

                            String dateToViewWithHour = dateToView.toString() + " " + dateString[1];

                            ReservationToConfirmVM resToView = new ReservationToConfirmVM(m.getDate(), m.getTitle(), res.getPlace(), dateToViewWithHour,m.getId());
                            reservationsVM.add(resToView);
                        }

                        else
                        {

                            deleteReservation(res.getId_movie(),res.getId_user(),res.getPlace());
                        }
                    }
                }
            }
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
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Anulowano rezerwację");
            alert.showAndWait();
        }

    }

    private void updateReservation(int id_movie, int id_user, String place) throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("updateReservation " + String.valueOf(id_movie) + "#" + String.valueOf(id_user) + "#" + place);
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("Succses"))
            System.out.println("Error from server");

        else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Poprawnie potwierdzono rezerwacje");
            alert.showAndWait();
        }

    }


    @FXML
    private void logout(ActionEvent event) throws IOException, SQLException {

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

    private void getMessages() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getMessages");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        if(!buffer.equals("")) {

            String[] messagesFromServer = buffer.split("#");

            for (String item : messagesFromServer) {
                String[] mes = item.split("@");
                mes[2] = mes[2].replaceAll("~","\n");
                Message messageToList = new Message(mes[0], mes[1], mes[2], mes[3]);
                messages.add(messageToList);
            }
        }
    }


}
