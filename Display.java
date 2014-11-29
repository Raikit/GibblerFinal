
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Display {
    
    public void Display(){
        
    }
    
    
    public void viewFeed(String user){
        
        if (!(user == null)){
            
            String sql;
            int result;
            
            System.out.println("\nThese are your custom posts!\n");
            
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
                //System.out.println("Connection successful");

                Statement statement = conn.createStatement();
                sql = "use mydb;";
                result = statement.executeUpdate(sql);
                //System.out.println("Result of SQL update: " + result);
                
                statement = conn.createStatement();
                sql = ("SELECT Users FROM " + user + ";");
                ResultSet rs1 = statement.executeQuery(sql);
                
                if(rs1.isBeforeFirst()){
                    while(rs1.next()){
                        statement = conn.createStatement();
                        sql = ("SELECT text, username, date_time FROM posts WHERE username = '" + rs1.getString("Users") + "';");
                        //System.out.println(sql);
                        ResultSet rs2 = statement.executeQuery(sql);
                        
                        if(rs2.isBeforeFirst()){
                            System.out.println("Displaying posts by " + rs1.getString("Users"));
                            while(rs2.next()){
                                System.out.println(rs2.getString("text") + " by user: " + rs2.getString("username")
                                    + " at " + rs2.getString("date_time"));
                            }
                            
                            System.out.println("");
                        }else{
                            System.out.println("No posts to display from user " + rs1.getString("Users") + "\n");
                        }//if posts by user
                    }//while users in rs1
                }else{
                    System.out.println("\nYou are not following any users.\n");
                }//if users in list
                
                rs1.close();
                
                statement = conn.createStatement();
                sql = ("SELECT Groups FROM " + user + ";");
                rs1 = statement.executeQuery(sql);
                
                if(rs1.isBeforeFirst()){
                    while(rs1.next()){
                        statement = conn.createStatement();
                        sql = ("SELECT text, username, date_time FROM posts WHERE groups LIKE '%" + rs1.getString("Groups") + "%';");
                        //System.out.println(sql);
                        ResultSet rs2 = statement.executeQuery(sql);
                        
                        if(rs2.isBeforeFirst()){
                            System.out.println("Displaying posts in group " + rs1.getString("Groups"));
                            while(rs2.next()){
                                System.out.println(rs2.getString("text") + " by user: " + rs2.getString("username")
                                    + " at " + rs2.getString("date_time"));
                            }
                            
                            System.out.println("");
                        }else {
                            System.out.println("No posts to display from group " + rs1.getString("Groups") + "\n");
                        }//if posts in group
                    }//while groups in rs1
                }else{
                    System.out.println("\nYou are not following any users.\n");
                }//if groups in list
                
                rs1.close();

            }catch (SQLException ex) {
                // handle any SQL errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
            
        }//logged in user
        
        else {
            
            System.out.println("These are the public posts!\n");
            
            String sql;
            int result;
            
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
                //System.out.println("Connection successful");

                Statement statement = conn.createStatement();
                sql = "use mydb;";
                result = statement.executeUpdate(sql);
                //System.out.println("Result of SQL update: " + result);
                
                statement = conn.createStatement();
                sql = ("SELECT text, username, date_time FROM posts WHERE privacy='public';");
                //System.out.println(sql);
                ResultSet rs2 = statement.executeQuery(sql);
                
                if(rs2.isBeforeFirst()){
                    while(rs2.next()){
                        System.out.println(rs2.getString("text") + " by user: " + rs2.getString("username")
                            + " at " + rs2.getString("date_time"));
                    }
                }else{
                    System.out.println("No posts to display.");
                }
                
                System.out.println("");
                
            }catch (SQLException ex) {
                // handle any SQL errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        
        }
        
    }
    //String user;
}
