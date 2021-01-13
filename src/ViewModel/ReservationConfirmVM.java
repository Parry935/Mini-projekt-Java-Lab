package ViewModel;

public class ReservationConfirmVM {
    private String date;
    private String title;
    private String place;
    private String idMovie;


    public ReservationConfirmVM(String date, String title, String place,String idMovie) {
        this.date = date;
        this.title = title;
        this.place = place;
        this.idMovie = idMovie;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(String idMovie) {
        this.idMovie = idMovie;
    }
}

