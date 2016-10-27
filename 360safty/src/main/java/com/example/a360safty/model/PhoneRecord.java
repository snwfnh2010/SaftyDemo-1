package com.example.a360safty.model;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class PhoneRecord {
    private String number;
    private String data;

    public PhoneRecord(String number, String data) {
        this.number = number;
        this.data = data;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
