package com.hykj.liuzhi.androidcomponents.bean;

public class UserInfo {
    private String phone;
    private String code;
    private String password;


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserInfo{" +
                "phone='" + phone + '\'' +
                ", code='" + code + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
