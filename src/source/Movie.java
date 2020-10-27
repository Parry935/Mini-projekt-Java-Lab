package source;


public class Movie {
    private String date;
    private String title;
    private String type;


    public Movie(String date, String title, String type) {
        this.date = date;
        this.title = title;
        this.type = type;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
