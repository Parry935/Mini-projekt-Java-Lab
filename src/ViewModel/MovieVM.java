package ViewModel;


import Models.Movie;

public class MovieVM extends Movie {
    private String id;


    public MovieVM(String id, String date, String title, String type) {
        super( date,title,type);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

