package com.example.recyclerist;

public class UserModel {
String Name;
String Email;
int image;

    public UserModel() {

    }

    public UserModel(String name, String email, int image) {
        this.Name = name;
        this.Email = email;
        this.image = image;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
