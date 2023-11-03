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
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, account.getUsername());
            ps.setString(2, account.getPassword());
            ps.executeUpdate();
            return new Account(account.getUsername(), account.getPassword());

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
    
}
