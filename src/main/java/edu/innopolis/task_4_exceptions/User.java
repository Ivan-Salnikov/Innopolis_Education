package edu.innopolis.task_4_exceptions;

public class User {
    private String  email;
    private  String password;
    private boolean isSignIn;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.isSignIn = false;
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
}
