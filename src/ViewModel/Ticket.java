package ViewModel;


import Models.Movie;

public class Ticket extends Movie {
    private String email;
    private String first_name;
    private String last_name;
    private String age;
    private String number;
    private String idReservation;
    private String place;

    public Ticket(String date, String title, String type, String email, String first_name, String last_name, String age, String number, String idReservation, String place) {
        super(date, title, type);
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.number = number;
        this.idReservation = idReservation;
        this.place = place;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(String idReservation) {
        this.idReservation = idReservation;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
