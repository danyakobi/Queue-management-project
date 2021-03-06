package com.example.queue_management_project.Model;

public class User {
    String fullName;
    String id;
    String phone;
    String email;
    String password;

    public User(String fullName, String id, String phone, String email, String password) {
        this.fullName = fullName;
        this.id = id;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }
    public User(){}


    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}