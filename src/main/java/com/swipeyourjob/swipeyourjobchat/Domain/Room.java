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
            if(!this.userlist.contains(userid)){this.userlist.add(userid);}
            if(!this.sessionlist.contains(session)){this.sessionlist.add(session);}

            return true;
        }catch (Exception e){
            return false;
        }
    }

    public List<WebSocketSession> getSessionlist() {
        return sessionlist;
    }

    public void setSessionlist(List<WebSocketSession> sessionlist) {
        this.sessionlist = sessionlist;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }
}
