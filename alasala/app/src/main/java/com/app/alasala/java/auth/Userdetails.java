package com.app.alasala.java.auth;

public class Userdetails {


    private String user;
    private String email;
    private String phoneNumber;


    public Userdetails( String user, String email, String phoneNumber) {
        this.user = user;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public Userdetails() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }
}
