package source;

public class User {
    private String emial;
    private String first_name;
    private String last_name;
    private int age;
    private int number;
    private String password;


    public User(String emial, String first_name, String last_name, int age, int number, String password) {
        this.emial = emial;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.number = number;
        this.password = password;
    }

    public String getEmial() {
        return emial;
    }

    public void setEmial(String emial) {
        this.emial = emial;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
