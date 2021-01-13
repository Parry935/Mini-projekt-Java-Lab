package ViewModel;

public class UsersVM {

    private String id;
    private String email;
    private String first_name;
    private String last_name;
    private String age;
    private String number;


    public UsersVM(String id, String email, String first_name, String last_name, String age, String number) {
        this.email = email;
        this.first_name = first_name;
        this.last_name = last_name;
        this.age = age;
        this.number = number;
        this.id = id;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
