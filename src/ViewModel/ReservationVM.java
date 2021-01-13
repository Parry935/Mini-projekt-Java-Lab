package ViewModel;

import Models.Reservation;

public class ReservationVM {

    private int id_reservation;
    private int id_movie;
    private int id_user;
    private String place;
    private String confirm;

    public ReservationVM(int id_reservation, int id_movie, int id_user,String place, String confirm) {
        this.id_reservation = id_reservation;
        this.id_user = id_user;
        this.id_movie = id_movie;
        this.place = place;
        this.confirm = confirm;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_movie() {
        return id_movie;
    }

    public void setId_movie(int id_movie) {
        this.id_movie = id_movie;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getConfirm() {
        return confirm;
    }

    public void setConfirm(String confirm) {
        this.confirm = confirm;
    }
}
