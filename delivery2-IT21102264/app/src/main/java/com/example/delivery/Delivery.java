package com.example.delivery;

public class Delivery {
    private String userid;
    private String name;
    private String address;
    private String conNo;
    private String email;

    public Delivery() {

    }

    public Delivery(String userid, String name, String address, String conNo, String email) {
        this.userid = userid;
        this.name = name;
        this.address = address;
        this.conNo = conNo;
        this.email = email;
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