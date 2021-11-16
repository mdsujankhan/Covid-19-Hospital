package com.example.covid_19hospital.model;

public class Patient {
    private int id;
    private String name;
    private String address;
    private String age;
    private String contactNumber;
    private String email;
    private String password;

    public Patient() {
    }

    public Patient(int id, String name, String address, String age, String contactNumber, String email, String password) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.age = age;
        this.contactNumber = contactNumber;
        this.email = email;
        this.password = password;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
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
