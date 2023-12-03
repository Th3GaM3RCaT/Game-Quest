package com.thecurseds.gamequest;

public class User {
    private String Userid;
    private String name;

    public User() {
    }

    public User(String Userid, String name) {
        this.Userid = Userid;
        this.name = name;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String Userid) {
        this.Userid = Userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
