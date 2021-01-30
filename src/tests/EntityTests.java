package tests;

import Models.Message;
import Models.Movie;
import Models.Reservation;
import Models.User;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class EntityTests {

    Movie movie;
    Message message;
    Reservation reservation;
    User user;

    @Before
    public void setUp(){
        movie = new Movie("test","test","test");
        message = new Message("1","test","test","test");
        reservation = new Reservation(1,1,"test",1);
        user = new User("test","test","test","test","test","test","test");
    }

    @Test
    public void testMovie() throws SQLException {

        Movie movieTest = new Movie();
        movieTest.setDate("test");
        movieTest.setTitle("test");
        movieTest.setType("test");

        assertEquals(movieTest.getDate(),movie.getDate());
        assertEquals(movieTest.getTitle(),movie.getTitle());
        assertEquals(movieTest.getType(),movie.getType());
    }

    @Test
    public void testMessage() throws SQLException {

        Message messageTest = new Message();
        messageTest.setDate("test");
        messageTest.setTitle("test");
        messageTest.setId_mes("1");
        messageTest.setText("test");

        assertEquals(messageTest.getDate(),message.getDate());
        assertEquals(messageTest.getTitle(),message.getTitle());
        assertEquals(messageTest.getId_mes(),message.getId_mes());
        assertEquals(messageTest.getText(),message.getText());
    }

    @Test
    public void testReservation() throws SQLException {

        Reservation reservationTest = new Reservation();
        reservationTest.setId_user(1);
        reservationTest.setId_movie(1);
        reservationTest.setPlace("test");
        reservationTest.setConfirm(1);

        assertEquals(reservationTest.getId_movie(),reservation.getId_movie());
        assertEquals(reservationTest.getId_user(),reservation.getId_user());
        assertEquals(reservationTest.getPlace(),reservation.getPlace());
        assertEquals(reservationTest.getConfirm(),reservation.getConfirm());
    }

    @Test
    public void testUser() throws SQLException {

        User userTest = new User();
        userTest.setEmial("test");
        userTest.setFirst_name("test");
        userTest.setLast_name("test");
        userTest.setNumber("test");
        userTest.setAge("test");
        userTest.setPassword("test");
        userTest.setRole("test");

        assertEquals(userTest.getEmial(),user.getEmial());
        assertEquals(userTest.getFirst_name(),user.getFirst_name());
        assertEquals(userTest.getLast_name(),user.getLast_name());
        assertEquals(userTest.getNumber(),user.getNumber());
        assertEquals(userTest.getAge(),user.getAge());
        assertEquals(userTest.getPassword(),user.getPassword());
        assertEquals(userTest.getRole(),user.getRole());
    }

}
