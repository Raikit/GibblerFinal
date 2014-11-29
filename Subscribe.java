import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Subscribe {        
        //calls the search seach function to find user first
        
    
        public void SubscribeToUser(String user, User current){
            
            Scanner in = new Scanner(System.in);
            String ans;
            
            System.out.println("\nAre you sure you want to subcribe to user " + user + " ? (Yes or No)");
            ans = in.nextLine();
            
            if(ans.equals("Yes") || ans.equals("yes")){
                try {
                    String sql;
                    int result;
                    
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
                    //System.out.println("Connection successful");

                    Statement statement = conn.createStatement();
                    sql = "use mydb;";
                    result = statement.executeUpdate(sql);
                    
                    statement = conn.createStatement();
                    sql = ("INSERT INTO " + current.getUN() + " (Users) VALUES ('" 
                            + user + "');");
                    //System.out.println(sql);
                    result = statement.executeUpdate(sql);
                    
                    System.out.println("Successfully subscribed to user " + user + ". Returning to main menu.");
                }catch (SQLException ex) {
                    // handle any SQL errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
            }
            else if(ans.equals("No") || ans.equals("no")){
                System.out.println("\nYou have not subscribed to this user, you are being returned to the main menu.");
            }
            
        }
        
        
        public void SubscribeToGroup(String Group, User current){
            
            Scanner in = new Scanner(System.in);
            String ans2;
            
            System.out.println("Are you sure you want to subcribe to user " + Group + " ? (Yes or No)");
            ans2 = in.nextLine();
            
            if(ans2.equals("Yes") || ans2.equals("yes")){
                try {
                    String sql;
                    int result;
                    
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
                    //System.out.println("Connection successful");

                    Statement statement = conn.createStatement();
                    sql = "use mydb;";
                    result = statement.executeUpdate(sql);
                    
                    statement = conn.createStatement();
                    sql = ("INSERT INTO " + current.getUN() + " (Groups) VALUES ('" 
                            + Group + "');");
                    //System.out.println(sql);
                    result = statement.executeUpdate(sql);
                    
                    System.out.println("Successfully subscribed to Group " + Group + ". Returning to main menu.");
                }catch (SQLException ex) {
                    // handle any SQL errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                
            }
            else if(ans2.equals("No") || ans2.equals("no")){
                System.out.println("You have not subscribed to this group, you are being returned to the main menu.");
            } 
        }
}
