package com.swipeyourjob.swipeyourjobchat.Controller.RequestHandlers;

public class LoginRequest {

    public String action;
    public String jwt;

    public LoginRequest(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
