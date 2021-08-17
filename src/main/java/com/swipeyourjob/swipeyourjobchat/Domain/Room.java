package com.swipeyourjob.swipeyourjobchat.Domain;


import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class Room {
    List<Integer> userlist = new ArrayList<>();
    List<WebSocketSession> sessionlist = new ArrayList<>();
    int roomid;
    public Room(int roomid){
        this.roomid = roomid;
    }

    public List<Integer> getUserlist() {
        return userlist;
    }

    public void setUserlist(List<Integer> userlist) {
        this.userlist = userlist;
    }

    public int getRoomid() {
        return roomid;
    }
    public boolean addUserList (int userid,WebSocketSession session){
        try{
            this.userlist.add(userid);
            this.sessionlist.add(session);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }
}
