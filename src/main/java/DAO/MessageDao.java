package DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Model.Message;
import Util.ConnectionUtil;

public class MessageDao {

    public List<Message> getAllMessages() {
        List<Message> allMessages = new ArrayList<>();
        String sql = "Select * from message";
        try {
            PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
              int message_id = rs.getInt("message_id");
              int posted_by = rs.getInt("posted_by");
              String message_text = rs.getString("message_text");
              long time_posted_epoch = rs.getLong("time_posted_epoch");
              allMessages.add(new Message(message_id, posted_by, message_text, time_posted_epoch));

            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return allMessages;

    } 
    public static List<Message> getMessagesFromUser(int user){
        List<Message> foundMessages = new ArrayList<>();
        String sql = "select * from message where posted_by = ?";
        try{
            PreparedStatement ps = ConnectionUtil.getConnection().prepareStatement(sql);
            ps.setInt(1, user);
            ResultSet rs = ps.executeQuery();
                while(rs.next()){
                    int message_id = rs.getInt("message_id");
                    int posted_by = rs.getInt("posted_by");
                    String message_text = rs.getString("message_text");
                    long time_posted_epoch = rs.getLong("time_posted_epoch");
                    foundMessages.add(new Message(message_id,posted_by,message_text,time_posted_epoch));}

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return foundMessages;
    }  
}
