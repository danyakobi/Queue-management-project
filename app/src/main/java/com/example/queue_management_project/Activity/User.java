package com.example.queue_management_project.Activity;

public class User {
    String fullName;
    String id;
    String phone;

    public User(String fullName, String id, String phone) {
        this.fullName = fullName;
        this.id = id;
        this.phone = phone;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }
}
