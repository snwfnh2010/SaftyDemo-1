package com.example.a360safty.model;

/**
 * Created by snwfnh on 2016/10/28.
 */
public class BlackNumberInfo {
    public String phone;
    public String mode;

    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getMode() {
        return mode;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }
    @Override
    public String toString() {
        return "BlackNumberInfo [phone=" + phone + ", mode=" + mode + "]";
    }
}
