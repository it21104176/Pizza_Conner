package com.example.delivery;

public class Cart {
    private String userid;
    private String name;
    private String size;
    private String price;

    public Cart() {

    }

    public Cart(String userid, String name, String size, String price) {
        this.userid = userid;
        this.name = name;
        this.size = size;
        this.price = price;
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

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}