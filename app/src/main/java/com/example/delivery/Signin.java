package com.example.delivery;

public class Signin {
    private String name;
    private String phone;
    private String email;
    private String pwd;

    public Signin(){

    }
    public Signin(String name, String phone, String email, String pwd) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.pwd = pwd;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }


    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPwd() {
        return pwd;
    }

}
