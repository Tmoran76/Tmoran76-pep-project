package DAO;


import java.sql.*;
import java.util.*;

import Model.Account;
import Util.ConnectionUtil;

public class AccountDao {
    
    public Account addAccount(Account account){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "Insert Into Account (username, password) values(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.executeUpdate();
            ResultSet rsKey = ps.getGeneratedKeys();
            if(rsKey.next()){
                int accountKey = (int)rsKey.getLong("account_id");
                return new Account(accountKey, account.getUsername(), account.getPassword());
            }           

        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
        
        
    }
    public List<String> getAllUsernames(){
        List<String> allUsernames = new ArrayList<>();
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "Select username from account";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                String username = rs.getString("username");
                allUsernames.add(username);
            }
          }
        catch(SQLException e){
            e.printStackTrace();
        }
        return allUsernames;
    }
    public Account findAccount(Account account){
        Connection conn = ConnectionUtil.getConnection();
        try{
            String sql = "Select * from Account where username = ? and password = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ResultSet rs = ps.executeQuery();            
            while(rs.next()){
                int account_id = rs.getInt("account_id");
                String un = rs.getString("username");
                String pw = rs.getString("password");
                return new Account(account_id, un, pw);
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    
}
