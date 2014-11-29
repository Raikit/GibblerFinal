

import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Aisha, Flannery, Kira
 */
public class Search {
    
    public void Search(){
        
    }
     
    public boolean SearchByUser(String username, boolean fromSub){
        
        String thing = "";
        String sql;
        int result;
        boolean exists = false;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");
            
            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            
            //Check if the desired username is taken
            statement = conn.createStatement();
            sql = ("SELECT userids FROM users WHERE usernames = '" 
                    + username +"';");
            ResultSet rs1 = statement.executeQuery(sql);
            exists = rs1.next();
        
        
            if (exists){
                
                exists = true; //Making absolutely sure it's returning what I want it to.
                
                statement = conn.createStatement();
                sql = ("SELECT firstname, lastname FROM users WHERE usernames='" + username + "';");
                ResultSet rs2 = statement.executeQuery(sql);
                
                if(rs2.isBeforeFirst()){
                    rs2.next();
                    System.out.println("User " + username);
                    System.out.println("Name " + rs2.getString("firstName") + " " + rs2.getString("lastName") +"\n");
                    
                    rs2.close();
                }
                
                if(!fromSub){
                    statement = conn.createStatement();
                    sql = ("SELECT text, username, date_time FROM posts WHERE username = '" + username + "' AND privacy = 'public';");
                    //System.out.println(sql);
                    rs2 = statement.executeQuery(sql);


                    if (rs2.isBeforeFirst()){

                        try {
                             System.out.println("Posts");
                             while (rs2.next()) {
                                System.out.println(rs2.getString("text") + " by user: " + rs2.getString("username")
                                    + " at " + rs2.getString("date_time"));
                            }
                        } catch (Exception e) {
                             e.printStackTrace();
                        }
                    }else {

                        System.out.println("Error: No posts by that user.");
                    }//if isBeforeFirst
                }//if !fromSub
            }//if exists
            else {
            
                System.out.println("Error: No user by that name");
                exists = false;
            }
            
            
            conn.close();
        } catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return exists;
        
    }
    
    public boolean SearchByGroup(String group, boolean fromSub){
        String thing = "";
        String sql;
        int result;
        boolean exists = false;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");
            
            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            
            //Check if the desired username is taken
            statement = conn.createStatement();
            sql = ("SELECT name FROM groups WHERE name = '" 
                    + group +"_group';");
            ResultSet rs1 = statement.executeQuery(sql);
            exists = rs1.next();
        
        
            if (exists){
                
                thing = group;
                exists = true;

                System.out.println("Group " + group + " exists. Here are a few recent posts.");
                
                if(!fromSub){
                    statement = conn.createStatement();
                    sql = ("SELECT text, username, date_time FROM posts WHERE groups LIKE '%" + group + "%' AND privacy = 'public';");
                    ResultSet rs2 = statement.executeQuery(sql);
                
                    if (rs2.isBeforeFirst()){

                    try {
                         System.out.println("Posts");
                         while (rs2.next()) {
                            System.out.println(rs2.getString("text") + " by user: " + rs2.getString("username")
                                + " at " + rs2.getString("date_time"));
                            }//while
                        } catch (Exception e) {
                            e.printStackTrace();
                        }//catch
                    }else {

                        System.out.println("Error: No posts from that group to display.");
                    }
                }//if !fromSub

            }//if exists
        
            else {
            
                System.out.println("Error: No group by that name");
                exists = false;
            }
            
            
            conn.close();
        } catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return exists;
    }
    
    public void SearchByHash(String tag){
        
        String sql;
        int result;
        boolean exists;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");
            
            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            
            statement = conn.createStatement();
            sql = ("SELECT text, username, date_time FROM posts WHERE hashtags LIKE '%" + tag + "%' AND privacy = 'public';");
            ResultSet rs1 = statement.executeQuery(sql);
        
        
            if (rs1.isBeforeFirst()){

                try {
                     System.out.println("Posts");
                     while (rs1.next()) {
                        System.out.println(rs1.getString("text") + " by user: " + rs1.getString("username")
                            + " at " + rs1.getString("date_time"));
                     }
                  } catch (Exception e) {
                     e.printStackTrace();
                  }
            }
        
            else {
            
                System.out.println("Error: No posts with that tag.");
            }
            
            
            conn.close();
        } catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }
}
