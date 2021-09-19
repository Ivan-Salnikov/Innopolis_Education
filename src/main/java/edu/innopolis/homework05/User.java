package edu.innopolis.homework05;

public class User {
    public Integer getId() {
        return id;
    }

    private Integer id;
    private String  email;

    public void setPassword(String password) {
        this.password = password;
    }

    private  String password;

    public void setId(Integer id) {
        this.id = id;
    }

    private boolean isSignIn;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.isSignIn = false;
    }

    public User(Integer id, String email, String password, boolean isSignIn) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.isSignIn = isSignIn;
    }

    public boolean getSignInState() {
        return isSignIn;
    }

    public void setSignIn(boolean signIn) {
        isSignIn = signIn;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                ", id=" + id +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isSignIn=" + isSignIn +
                '}';
    }
}
