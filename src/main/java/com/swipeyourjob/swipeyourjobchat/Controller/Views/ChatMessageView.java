package com.swipeyourjob.swipeyourjobchat.Controller.Views;

public class ChatMessageView {

    public String message;
    public int roomid;
    public String sender;
    public boolean me = false;
    public ChatMessageView(String message, int roomid, String firstname) {
        this.message = message;
        this.roomid = roomid;
        this.sender = firstname;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }
}
