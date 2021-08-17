package com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl;

public class UserConnectionRow {
    public int roomid;
    public String roomname;
    public int userid;


    public UserConnectionRow(int roomid, String roomname, int userid) {
        this.roomid = roomid;
        this.roomname = roomname;
        this.userid = userid;
    }

    public int getRoomid() {
        return roomid;
    }

    public void setRoomid(int roomid) {
        this.roomid = roomid;
    }

    public String getRoomname() {
        return roomname;
    }

    public void setRoomname(String roomname) {
        this.roomname = roomname;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
