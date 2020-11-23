package Controllers;

import DB.DBconection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Callback;
import source.Movie;

import java.io.IOException;
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
    private Label timeLabel;

    private ExecutorService executorService = Executors.newSingleThreadExecutor();


    //Repertuar
    @FXML
    private TableView<Movie> table;

    @FXML
    private TableColumn<Movie, String> date;

    @FXML
    private TableColumn<Movie, String> title;

    @FXML
    private TableColumn<Movie, String> type;

    @FXML
    private TableColumn reserv;

    //Moje


    @FXML
    private ImageView prem_img_1;

    @FXML
    private ImageView prem_img_2;

    @FXML
    private ImageView prem_img_3;

    @FXML
    private Label prem_title1;

    @FXML
    private Label prem_title2;

    @FXML
    private Label prem_title3;

    ObservableList<Movie> movies = FXCollections.observableArrayList();
    @FXML
    public void initialize() throws SQLException{

        executorService.execute(new Clock(timeLabel));

        addFilms();

        //css
        date.setStyle( "-fx-alignment: CENTER;");
        title.setStyle( "-fx-alignment: CENTER;");
        type.setStyle( "-fx-alignment: CENTER;");
        reserv.setStyle( "-fx-alignment: CENTER;");

        ////////////
        date.setCellValueFactory(new PropertyValueFactory<Movie,String>("date"));
        title.setCellValueFactory(new PropertyValueFactory<Movie,String>("title"));
        type.setCellValueFactory(new PropertyValueFactory<Movie,String>("type"));

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

        table.setItems(movies);
    }

    private void addFilms() throws SQLException {

        String date;
        String title;
        String type;

        DBconection db = new DBconection();
        Connection connection = db.getContection();

        String sqlQuery = "Select * from mydatabase.films";

        PreparedStatement preparedStmt = connection.prepareStatement(sqlQuery);
        ResultSet rs = preparedStmt.executeQuery();

        while(rs.next())
        {
            date = rs.getString("date");
            title = rs.getString("title");
            type = rs.getString("type");

            movies.add(new Movie(date,title,type));
        }

        connection.close();
    }


}
