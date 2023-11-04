package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public List<Message> getMessagesFromUser(int user){
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
    public Message createNewMessage(Message message){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "Insert into message(posted_by, message_text, time_posted_epoch) values(?,?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, message.getPosted_by());
            ps.setString(2, message.getMessage_text());
            ps.setLong(3, message.getTime_posted_epoch());
            ps.executeUpdate();
            ResultSet rsKey = ps.getGeneratedKeys();
            if(rsKey.next()){
                int messageID = (int)rsKey.getLong("message_id");
                return new Message(messageID, message.getPosted_by(), message.getMessage_text(), message.getTime_posted_epoch());                
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public List<Integer> getPoster(){
        List<Integer> allPosters = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "Select posted_by from message";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int posted_by = rs.getInt("posted_by");
                allPosters.add(posted_by);                
            }
            return allPosters;

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;

    }
    public Message getMessageByID(int messageID){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "select * from message where message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, messageID);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int postedBy = rs.getInt("posted_by");
                String messageTxt = rs.getString("message_text");
                Long timePostedEpoch = rs.getLong("time_posted_epoch");
                return new Message(messageID, postedBy, messageTxt, timePostedEpoch);
            }           

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public int deleteMessageByID(int messageID){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "delete from message where message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, messageID);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected;

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
    public void updateMessageByID(int message_id, Message message){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "Update message set message_text = ? where message_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, message.getMessage_text());
            ps.setInt(2, message_id);
            ps.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
