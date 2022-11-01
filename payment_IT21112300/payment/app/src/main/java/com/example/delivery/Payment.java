package com.example.delivery;

public class Payment {
    private String userid;
    private String cardName;
    private String cardNo;
    private String expDate;
    private String cvvCode;
    private String amount;
    private String pizzaName;

    public Payment() {

    }

    public Payment(String userid, String cardName, String cardNo, String expDate, String cvvCode, String amount, String pizzaName) {
        this.userid = userid;
        this.cardName = cardName;
        this.cardNo = cardNo;
        this.expDate = expDate;
        this.cvvCode = cvvCode;
        this.amount = amount;
        this.pizzaName = pizzaName;
    }

    public String getExpDate() {
        return expDate;
    }

    public void setExpDate(String expDate) {
        this.expDate = expDate;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCvvCode() {
        return cvvCode;
    }

    public void setCvvCode(String cvvCode) {
        this.cvvCode = cvvCode;
    }
}