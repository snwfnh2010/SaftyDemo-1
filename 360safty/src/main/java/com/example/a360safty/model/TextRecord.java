package com.example.a360safty.model;

/**
 * Created by snwfnh on 2016/10/24.
 */
public class TextRecord {
    private String number;
    private String date;
    private String content;

    public TextRecord(String number, String date, String content) {
        this.number = number;
        this.date = date;
        this.content = content;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
