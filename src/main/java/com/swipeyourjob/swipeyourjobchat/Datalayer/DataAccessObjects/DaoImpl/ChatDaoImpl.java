package com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl;

import com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.BaseDaoMySQL;
import com.swipeyourjob.swipeyourjobchat.Domain.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatDaoImpl  extends BaseDaoMySQL {
    public List<UserConnectionRow> GetUserRooms(int userid){
        try {
            Connection conn = super.getConnection();
            List<UserConnectionRow> connections = new ArrayList<>();
            connections.add(new UserConnectionRow(1,"testroom",userid));
            connections.add(new UserConnectionRow(2,"realbullshit",userid));
            return connections;
        }catch (Exception e){
            return new ArrayList<UserConnectionRow>();
        }
    }
}
