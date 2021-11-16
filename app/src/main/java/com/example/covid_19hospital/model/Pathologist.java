package com.example.covid_19hospital.model;

public class Pathologist {
    private int id;
    private String name;
    private String address;
    private String email;
    private String password;
    private String gender;
    private String contact;

    public Pathologist(int id, String name, String address, String email, String password, String gender, String contact) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.password = password;
        this.gender = gender;
        this.contact = contact;
    }

    public Pathologist() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
