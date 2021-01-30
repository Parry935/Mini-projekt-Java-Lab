package Models;

public class Reservation {

    private int id_user;
    private int id_movie;
    private String place;
    private int confirm;

    public Reservation() {
    }

    public Reservation(int id_user, int id_movie, String place, int confirm) {
        this.id_user = id_user;
        this.id_movie = id_movie;
        this.place = place;
        this.confirm = confirm;
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

    public int getConfirm() {
        return confirm;
    }

    public void setConfirm(int confirm) {
        this.confirm = confirm;
    }
}
