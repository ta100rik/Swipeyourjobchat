package com.swipeyourjob.swipeyourjobchat.Controller.RequestHandlers;

import org.json.JSONObject;

public class MessageHandler {

    public LoginRequest MessageParser(String message){
        try{
            JSONObject obj = new JSONObject(message);
            String jwttoken = obj.get("jwt").toString();
            String action = obj.get("action").toString();
            
            return new LoginRequest(action,jwttoken);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public String getJsonObject (String message, String key){
        try{
            JSONObject obj = new JSONObject(message);

            return obj.get(key).toString();
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}
