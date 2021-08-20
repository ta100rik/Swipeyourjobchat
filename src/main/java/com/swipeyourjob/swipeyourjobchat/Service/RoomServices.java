package com.swipeyourjob.swipeyourjobchat.Service;

import com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl.ChatDaoImpl;
import com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl.UserConnectionRow;
import com.swipeyourjob.swipeyourjobchat.Domain.Room;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;

public class RoomServices {
    private ChatDaoImpl chatImpl = new ChatDaoImpl();
    public List<Room> roomlist = new ArrayList();
    public void addSocketToRooms(int userid, WebSocketSession session){
        List<Room> rooms =  getUserConnectedRooms(userid);
        for (Room room : rooms){
            room.addUserList(userid,session);
        }

    }
    public Room getMessageRoom(int roomid,int userid){
        Room currentroom = this.isRoomInList(roomid);

        if (currentroom != null){
            if(currentroom.getUserlist().contains(userid)){

                return currentroom;
            }
        }
        return null;
    }
    public boolean uploadMessage(int userid, String message, int roomid){
        return chatImpl.addMessage(userid,message,roomid);
    }
    private Room isRoomInList(int roomid){
        for (Room currentRoom : roomlist){

            if(currentRoom.getRoomid() == roomid){
                System.out.println(roomid);
                System.out.println(currentRoom.getRoomid());
                return currentRoom;
            }else {
                System.out.println(currentRoom.getRoomid());
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
