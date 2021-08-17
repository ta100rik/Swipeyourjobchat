package com.swipeyourjob.swipeyourjobchat.Service;

import com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl.ChatDaoImpl;
import com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl.UserConnectionRow;
import com.swipeyourjob.swipeyourjobchat.Domain.Room;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class RoomServices {
    private final ChatDaoImpl chatImpl = new ChatDaoImpl();
    public List<Room> roomlist = new ArrayList();
    public void addSocketToRooms(int userid, WebSocketSession session){
        List<Room> rooms =  getUserConnectedRooms(userid);
        for (Room room : rooms){
            room.addUserList(userid,session);
        }

    }
    private Room isRoomInList(int roomid){
        for (Room currentRoom : roomlist){
            if(currentRoom.getRoomid() == roomid){
                return currentRoom;
            }
        }
        return null;
    }
    private List<Room> getUserConnectedRooms(int userid){
        List<Room> currentlist = new ArrayList<>();
        try{
           List<UserConnectionRow> connectionList =  this.chatImpl.GetUserRooms(userid);
           if(connectionList.size() > 0){
               for(UserConnectionRow row : connectionList){
                  Room currentroom =  this.isRoomInList(row.getRoomid());
                  if(currentroom != null){
                      currentlist.add(currentroom);
                      this.roomlist.add(currentroom);
                  }else{
                      Room newroom = new Room(row.getRoomid());
                      currentlist.add(newroom);
                      this.roomlist.add(newroom);
                  }
               }
               return currentlist;
           }else{
               return currentlist;
           }
        }catch(Exception e){
            return currentlist;
        }
    }
}
