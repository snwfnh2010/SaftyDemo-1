package com.example.firstapplication;

/**
 * Created by snwfnh on 2016/10/22.
 */
public class MessageInfo {
    private String name;
    private String num;
    private String image;


    public MessageInfo(String name, String num, String image) {
        this.name = name;
        this.num = num;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
