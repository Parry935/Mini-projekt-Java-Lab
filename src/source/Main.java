package source;

import Controllers.ControllerIndex;
import Models.Message;
import Models.Movie;
import Models.User;
import ViewModel.MovieVM;
import ViewModel.ReservationConfirmVM;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Main extends Application {

    public static Movie movie;
    public static Message message;
    public static int userID;
    public static ReservationConfirmVM generateReservationTicket;
    public static MovieVM movieToUpdate;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../FXML/Login.fxml"));
        Scene scene = new Scene(root, 510, 450);
        primaryStage.setTitle("Cinema");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) throws IOException {
        launch(args);
    }
}
