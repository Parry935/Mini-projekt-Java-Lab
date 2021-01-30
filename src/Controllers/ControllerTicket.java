package Controllers;

import ViewModel.*;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;
import source.Main;

import java.awt.image.AreaAveragingScaleFilter;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ControllerTicket {

    @FXML
    private Label emailT;

    @FXML
    private Label firstNameT;

    @FXML
    private Label ageT;

    @FXML
    private Label lastNameT;

    @FXML
    private Label phoneT;

    @FXML
    private Label dateT;

    @FXML
    private Label titleT;

    @FXML
    private Label typeT;

    @FXML
    private Label idResT;

    @FXML
    private Label placeT;

    private List<UsersVM> users = new ArrayList<>();
    private List<MovieVM> movies = new ArrayList<>();
    private List<ReservationVM> reservations = new ArrayList<>();

    @FXML
    public void initialize() throws SQLException, IOException {
        getDataForTicket();

    }

    private void getDataForTicket() throws IOException {
        getUser();
        getMovies();
        getReservations();
    }


    private void getUser() throws IOException {

        Socket s = new Socket("localhost", 9999);

        PrintWriter out = new PrintWriter(s.getOutputStream());
        out.println("getUsers");
        out.flush();

        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));

        String buffer = in.readLine();

        String[] usersFromServer = buffer.split("#");

        for (String item : usersFromServer) {
            String[] user = item.split("&");
            UsersVM userToList = new UsersVM(user[0], user[1], user[2], user[3], user[4], user[5]);
            users.add(userToList);
        }

        for (UsersVM user : users) {
            if(Integer.parseInt(user.getId()) == Main.userID)
            {
                emailT.setText(user.getEmail());
                firstNameT.setText(user.getFirst_name());
                lastNameT.setText(user.getLast_name());
                ageT.setText(user.getAge());
                phoneT.setText(user.getNumber());
            }
        }

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

        for (MovieVM movie : movies) {
            if(movie.getId().equals(Main.generateReservationTicket.getIdMovie()))
            {
                dateT.setText(movie.getDate());
                titleT.setText(movie.getTitle());
                typeT.setText(movie.getType());
            }
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

        for (ReservationVM res : reservations) {
            if(res.getId_user() == Main.userID && res.getId_movie() == Integer.parseInt(Main.generateReservationTicket.getIdMovie())
            && res.getConfirm().equals("Tak") && res.getPlace().equals(Main.generateReservationTicket.getPlace()))
            {
                idResT.setText(String.valueOf(res.getId_reservation()));
                placeT.setText(String.valueOf(res.getPlace()));
            }
        }
    }

}
