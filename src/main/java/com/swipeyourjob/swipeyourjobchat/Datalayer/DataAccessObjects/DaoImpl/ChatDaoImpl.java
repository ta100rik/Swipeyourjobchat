package com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.DaoImpl;

import com.swipeyourjob.swipeyourjobchat.Datalayer.DataAccessObjects.BaseDaoMySQL;
import com.swipeyourjob.swipeyourjobchat.Domain.WebUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChatDaoImpl  extends BaseDaoMySQL {
    public List<UserConnectionRow> GetUserRooms(int userid){
        try {
            //Connection conn = super.getConnection();
            List<UserConnectionRow> connections = new ArrayList<>();
            connections.add(new UserConnectionRow(1,"testroom",userid));
            connections.add(new UserConnectionRow(2,"realbullshit",userid));

            return connections;
        }catch (Exception e){
            return new ArrayList<UserConnectionRow>();
        }
    }
    public boolean addMessage(int userid, String message, int roomid){
        try{
            Connection conn = super.getConnection();
            String sql = "INSERT INTO chatmessages (chatmessage,roomid,userid) VALUES (?,?,?)";
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1,message);
            statement.setInt(2,roomid);
            statement.setInt(3,userid);

            int id = super.executeQueryReturningId(statement,conn);
            if(id != 0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
    public WebUser getUserById(int userid){
        try{
            Connection conn = super.getConnection();
            String sql = "SELECT * FROM webusers where idwebusers = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1,userid);
            ResultSet Result = super.executeQuery(statement,conn);
            while(Result.next()){
                String Firstname        = Result.getString("firstname");
                String LastName         = Result.getString("lastname");
                String profilepicture   = Result.getString("profilepicture");
                String Email            = Result.getString("email");
                WebUser currentuser     = new WebUser(Firstname,LastName,profilepicture,Email);
                return currentuser;
            }
            return null;
        }catch (Exception e){
            return null;
        }

    }
}
