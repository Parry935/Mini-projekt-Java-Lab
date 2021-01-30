package Models;

public class Message {

    private String id_mes;
    private String title;
    private String text;
    private String date;

    public Message() {
    }

    public Message(String id_mes, String title, String text, String date) {
        this.id_mes = id_mes;
        this.title = title;
        this.text = text;
        this.date = date;
    }

    public String getId_mes() {
        return id_mes;
    }

    public void setId_mes(String id_mes) {
        this.id_mes = id_mes;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
