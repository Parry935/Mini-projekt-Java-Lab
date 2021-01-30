package Models;

public class User {
    private String emial;
    private String first_name;
    private String last_name;
    private String age;
    private String number;
    private String password;
    private String role;


    public User() {
    }

    public User(String emial, String first_name, String last_name, String age, String number, String password,String role) {
        this.emial = emial;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.number = number;
        this.password = password;
        this.role = role;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
