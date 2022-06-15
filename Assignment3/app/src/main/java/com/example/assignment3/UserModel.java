package com.example.assignment3;

public class UserModel {
    String Name;
    String Des;
    String date;

    public UserModel() {

    }

    public UserModel(String name, String des, String date) {
        this.Name = name;
        this.Des = des;
        this.date = date;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDes() {
        return Des;
    }

    public void setDes(String des) {
        Des = des;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String image) {
        this.date = date;
    }
}

