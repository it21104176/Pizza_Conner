package com.example.delivery;

public class Profile {
    private String userid;
    private String name;
    private String address;
    private String city;
    private String conNo;
    private String email;
    private String zipCode;

    public Profile() {

    }

    public Profile(String userid, String name, String address, String city, String conNo, String email, String zipCode) {
        this.userid = userid;
        this.name = name;
        this.address = address;
        this.city = city;
        this.conNo = conNo;
        this.email = email;
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
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

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }
}