package edu.innopolis.task_4_exceptions;

public class User {
    private String  email;
    private  String password;
    private boolean LoggingIn;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.LoggingIn = false;
    }

    public boolean isLoggingIn() {
        return LoggingIn;
    }

    public void setLoggingIn(boolean loggingIn) {
        LoggingIn = loggingIn;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
