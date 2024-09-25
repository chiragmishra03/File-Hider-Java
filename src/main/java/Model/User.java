package Model;

public class User {
    private  String name;
    private  String email;
    private  String pass;
    public User(String name , String email , String pass){
        this.name = name;
        this.pass = pass;
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public String getPass() {
        return pass;
    }
    public String getEmail() {
        return email;
    }

}
