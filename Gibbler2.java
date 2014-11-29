import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aisha
 */

import java.util.Scanner;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class Gibbler2 {

    
    public void Gibbler2(){
        
    }
    /**
     * @param args the command line arguments
     */
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        Gibbler2 gibbler = new Gibbler2();
        User current = new User();
        current.setUN(null);
        Scanner in = new Scanner(System.in);
        
        String ans;
        String sub;
        String sea;
        String con;
        Search find = new Search();
        Subscribe subs = new Subscribe();
        boolean alreadyExists = false;
        boolean loggedIn = false;
        
        //Search through posts and display all public ones.
        Display guest = new Display();
        Display user = new Display();
        
        guest.viewFeed(null);
        
        while (!loggedIn){
            System.out.println("Welcome to Gibbler! How can we assist you?\nPlease enter the number of the option you would like."
                    + "\n\n1. Register an Account\n2. Register an Admin Account\n3. Login\n4. Search\n5. View Feed");
            ans = in.nextLine();

            if (ans.equals("1")){
                gibbler.Register();
            }

            else if (ans.equals("2")){
                gibbler.RegisterAsAdmin();
            }

            else if (ans.equals("3")){

                current = gibbler.LogIn();
                user.viewFeed(current.getUN());

                if (current.getUN() != null){
                    loggedIn = true;
                }

            }
            
            else if (ans.equals("4")){
                System.out.println("What would you like to search by?"
                        + "1. User\n"
                        + "2. Group\n"
                        + "3. Hashtag\n");
                ans = in.nextLine();
                
                if (ans.equals("1")){
                    System.out.println("Which user would you like to search for?");
                    ans = in.nextLine();
                    
                    find.SearchByUser(ans, false);
                }
                else if (ans.equals("2")){
                    System.out.println("Which group would you like to search for?");
                    ans = in.nextLine();
                    
                    find.SearchByGroup(ans, false);                    
                }
                else if (ans.equals("3")){
                    System.out.println("What tag would you like to search for?");
                    ans = in.nextLine();
                    
                    find.SearchByHash(ans);
                }
                else {
                    System.out.println("That option is invalid. Returning to main menu.\n");
                }
            }
            else if(ans.equals("5")){
                guest.viewFeed(null);
            } 
        }
        
        while (loggedIn){
            
            System.out.println("\nWhat section do you want to see?\nPlease enter the number of the option you would like."
                    + "\n\n1. User Options\n2. Admin Options\n3. Group Options");
            ans = in.nextLine();
            
            if(ans.equals("1")){
             
                System.out.println("\nUser Options:"
                        + "\n1. Make a Post"
                        + "\n2. Edit Profile"
                        + "\n3. Subscribe to a User"
                        + "\n4. Subscribe to a Group"
                        + "\n5. View Feed"
                        + "\n6. Search for User"
                        + "\n7. Search for Group "
                        + "\n8. Search for Hashtag"
                        + "\n9. Logout"
                        + "\n\nPlease enter the number of the option you would like."
                        + "\nEnter any other character to return to the main menu.");
                ans = in.nextLine();
                
                if (ans.equals("1")) {
                    gibbler.MakeAPost(current);
                }//if (ans.equals("Make a Post"))
                if (ans.equals("2")){//edit profile
                    gibbler.EditProfile(current);
                }//edit profile
                if (ans.equals("3")){//Subscribe to user
                    System.out.println("\nWhat is the name of the user you want to subscribe to?");
                    sea = in.nextLine();
                    if(find.SearchByUser(sea, true)){
                        subs.SubscribeToUser(sea, current);
                    }
                }//subscribe to user
                if (ans.equals("4")){ //Subscribe to group 
                    System.out.println("\nWhat is the name of the group you want to subscribe to?");
                    sea = in.nextLine();
                    if(find.SearchByGroup(sea, true)){
                        subs.SubscribeToGroup(sea, current);
                    }
                }//subscribe to group
                if (ans.equals("5")){//View Feed  
                    user.viewFeed(current.getUN());  
                }//view feed
                if (ans.equals("6")){//search for user
                    System.out.println("What is the name of the user you want to search for?");
                    sea = in.nextLine();
                    find.SearchByUser(sea, false);
                }//search for user
                if (ans.equals("7")){//search for group
                    System.out.println("What is the name of the group you want to search for?");
                    sea = in.nextLine();
                    find.SearchByGroup(sea, false);
                }//search for group
                if (ans.equals("8")){//search for hashtag
                    System.out.println("What is the tag that you want to search for?");
                    sea = in.nextLine();
                    find.SearchByHash(sea);
                }//search for hashtag
                if (ans.equals("9")){//Logout
                    System.out.println("Goodbye, Friend");
                    loggedIn = false;
                    current.setUN(null);
                    current.setID(0);
                }//logout
            }//user option
                
            else if (ans.equals("2")){//admin options
                try {
                    String sql;
                    int result;
                    int userID;
                    
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
                    //System.out.println("Connection successful");

                    Statement statement = conn.createStatement();
                    sql = "use mydb;";
                    result = statement.executeUpdate(sql);
                    
                    statement = conn.createStatement();
                    sql = ("SELECT userids FROM users WHERE usernames='" + current.getUN() + "';");
                    ResultSet rs2 = statement.executeQuery(sql);
                    rs2.next();
                    
                    userID = rs2.getInt("userids");
                    
                    if(userID < 1000){
                        System.out.println("\nAdmin Options:"
                            + "\n1. Delete a User"
                            + "\n2. Delete a Group"
                            + "\n\nPlease enter the number of the option you would like."
                            + "\nEnter any other character to return to the main menu.");
                        ans = in.nextLine();

                        if (ans.equals("1")){//delete a user
                            gibbler.DeleteUser(current);
                        }

                        if (ans.equals("2")){//delete a group
                            gibbler.DeleteGroup(current);
                        }
                    }else{
                        System.out.println("\n\nThe current account does not have admin privileges.\n\n");
                    }
                }catch (SQLException ex) {
                    // handle any SQL errors
                    System.out.println("SQLException: " + ex.getMessage());
                    System.out.println("SQLState: " + ex.getSQLState());
                    System.out.println("VendorError: " + ex.getErrorCode());
                }
                
            }//admin options
            
            else if (ans.equals("3")){//group options
             
                System.out.println("\nGroup Options:"
                        + "\n1. Create a Group"
                        + "\n2. Delete a Group"
                        + "\n\nPlease enter the number of the option you would like."
                        + "\nEnter any other character to return to the main menu.");
                ans = in.nextLine();
                
                if (ans.equals("1")) {//create group

                    gibbler.CreateGroup(current);
                    
                }//create group
                
                if (ans.equals("2")){//delete group
                    
                    gibbler.DeleteGroup(current);
                    
                }//delete group
                
            }//group options
            
        }//while(ans.equals("Logout"))
        
        
    }//main
    
    public boolean Register(){
        boolean registered = false;
        boolean alreadyExists = false;
        Scanner scan = new Scanner(System.in);
        String username = "";
        String password = "";
        String confirm = "";
        String sql = "";
        int result = 0;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");
            
            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            //System.out.println("Result of SQL update: " + result);

            System.out.println("Enter your desired username:");
            username = scan.nextLine();

            System.out.println("Enter your password:");
            password = scan.nextLine();

            System.out.println("Confirm your password:");
            confirm = scan.nextLine();

            //make sure passwords match
            while(!confirm.equals(password)){
                System.out.println("Those passwords did not match.");
                System.out.println("Enter your password:");
                password = scan.nextLine();

                System.out.println("Confirm your password:");
                confirm = scan.nextLine();
            }
            
            //get the highest user ID and increment it for a new user ID
            statement = conn.createStatement();
            sql = "SELECT MAX(userids) AS userids FROM users;";
            System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int highestID = rs.getInt("userids");
            //System.out.println(highestID + " is the highest ID");
            int newID = highestID + 1;
            //System.out.println(newID + " is the new ID");
            
            
            //Check if the desired username is taken
            statement = conn.createStatement();
            sql = ("SELECT userids FROM users WHERE usernames = '" 
                    + username +"';");
            ResultSet rs1 = statement.executeQuery(sql);
            alreadyExists = rs1.next();
            
            if(!alreadyExists){
                //desired username is available
                
                //add user to users table
                statement = conn.createStatement();
                sql = ("INSERT INTO users (usernames, userids, password, firstname, lastName) VALUES ('" 
                        + username + "', " + newID + ", '"+ password +"', '', '');");
                System.out.println(sql);
                result = statement.executeUpdate(sql);
                
                
                //create subscriptions table for new user
                statement = conn.createStatement();
                sql = ("CREATE TABLE `mydb`.`" + username + "` (\n" +
                "  `Followers` VARCHAR(45) NULL,\n" +
                "  `Groups` VARCHAR(45) NULL,\n" +
                "  `Users` VARCHAR(45) NULL);");
                //System.out.println(sql);
                result = statement.executeUpdate(sql);
            
                System.out.println("Registration successful. You may now log in.");
                registered = true;
                return registered;
            }
            else{
                //username unavailable
                System.out.println("That username already exists.");
            }
            
            conn.close();
        } catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        System.out.println("Registration failed.");
        return registered;
    }//register
      
    public boolean RegisterAsAdmin(){
        boolean registered = false;
        boolean alreadyExists = false;
        Scanner scan = new Scanner(System.in);
        String username = "";
        String password = "";
        String confirm = "";
        String adminCode = "";
        String sql = "";
        int result = 0;
        
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");
            
            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            //System.out.println("Result of SQL update: " + result);

            //Ask for admin code.
            System.out.println("What is your admin code?");
            adminCode = scan.nextLine();
            
            //Check their code.
            statement = conn.createStatement();
            sql = ("SELECT code FROM admin_codes WHERE code = '" 
                    + adminCode +"';");
            ResultSet rs1 = statement.executeQuery(sql);
            alreadyExists = rs1.next();
            
            if(!alreadyExists){
                
                //Invalid code.
                System.out.println("That code is not valid.");
                
            }else{
                
                //Valid code.
                System.out.println("Enter your desired username:");
                username = scan.nextLine();

                System.out.println("Enter your password:");
                password = scan.nextLine();

                System.out.println("Confirm your password:");
                confirm = scan.nextLine();

                
                //Making sure the passwords match.
                while(!confirm.equals(password)){
                    System.out.println("Those passwords did not match.");
                    System.out.println("Enter your password:");
                    password = scan.nextLine();

                    System.out.println("Confirm your password:");
                    confirm = scan.nextLine();
                }

                
                //Finding the highest admin ID and incrementing it to make a new admin ID.
                statement = conn.createStatement();
                sql = "SELECT userids FROM users WHERE userids < 1000 HAVING MAX(userids);";
                //sql = "SELECT MAX(userids) AS userids FROM users;";
                //System.out.println(sql);
                ResultSet rs = statement.executeQuery(sql);
                rs.next();
                int highestID = rs.getInt("userids");
                //System.out.println(highestID + " is the highest ID");
                int newID = highestID + 1;
                //System.out.println(newID + " is the new ID");

                
                //Checking to see if the desired username already exists.
                statement = conn.createStatement();
                sql = ("SELECT userids FROM users WHERE usernames = '" 
                        + username +"';");
                ResultSet rs2 = statement.executeQuery(sql);
                alreadyExists = rs2.next();

                if(!alreadyExists){
                    //Desired username is available.
                    
                    //Add user to users table.
                    statement = conn.createStatement();
                    sql = ("INSERT INTO users (usernames, userids, password, firstname, lastName) VALUES ('" 
                            + username + "', " + newID + ", '"+ password +"', '', '');");
                    //System.out.println(sql);
                    result = statement.executeUpdate(sql);

                    
                    //Create subscriptions table for new user.
                    statement = conn.createStatement();
                    sql = ("CREATE TABLE `mydb`.`" + username + "` (\n" +
                    "  `Followers` VARCHAR(45) NULL,\n" +
                    "  `Groups` VARCHAR(45) NULL,\n" +
                    "  `Users` VARCHAR(45) NULL);");
                    //System.out.println(sql);
                    result = statement.executeUpdate(sql);

                    System.out.println("Registration successful. You may now log in.");
                    registered = true;
                    return registered;
                }
                else{
                    
                    //username unavailable
                    System.out.println("That username already exists.");
                }
            }
            conn.close();
        } catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        System.out.println("Registration failed.");
        return registered;
    }//register as admin
    
    public User LogIn(){
        User currentUser = new User();
        boolean userExists = true;
        Scanner scan = new Scanner(System.in);
        String username;
        String storedPassword = "";
        String password;
        String sql = "";
        int userID = 0;
        int result = 0;
        int count = 0;
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");
            
            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            //System.out.println("Result of SQL update: " + result);

            System.out.println("Enter your username:");
            username = scan.nextLine();
            
            //Make sure the user exists.
            statement = conn.createStatement();
            sql = ("SELECT usernames FROM users WHERE usernames = '" 
                    + username +"';");
            //System.out.println(sql);
            ResultSet rs1 = statement.executeQuery(sql);
            userExists = rs1.next();
            
            
            if(!userExists){
                //user not found
                System.out.println("That user does not exist.");
            } else {
                //user found
                System.out.println("Enter your password:");
                password = scan.nextLine();

                
                //make sure passwords match
                statement = conn.createStatement();
                sql = ("SELECT password FROM users WHERE usernames = '" 
                        + username +"';");
                //System.out.println(sql);
                ResultSet rs2 = statement.executeQuery(sql);
                rs2.next();
                storedPassword = rs2.getString("password");

                while(!storedPassword.equals(password) && count < 3){
                    //If passwords don't match, enter again (up to 3 three times)
                    System.out.println("Those passwords do not match.");
                    System.out.println("Enter your password:");
                    password = scan.nextLine();
                    
                    count = count + 1;
                }
                
                if(count == 3){
                    System.out.println("You have attempted to enter this password too many times.");
                }else{
                    currentUser.setUN(username);
                    currentUser.setID(userID);
                    System.out.println("You are now logged in.");
                }  
            }
            conn.close();
        }catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        return currentUser;
    }//login
     
    public void MakeAPost(User current){
        Scanner in = new Scanner(System.in);
        String sql;
        int result;
        String text;
        String privacy;
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        String postDate;
        String postAdd;
        String tempTextAmp = "";
        String tempTextHashtag = "";
        String tempTextAt = "";
        
        System.out.println("Is your post public or private?");
        privacy = in.nextLine();

        System.out.println("Type your post!");
        text = in.nextLine();
        text += " ";
        Date date = new Date();
        postDate = dateFormat.format(date);

        //System.out.println("This is your post: " + text);
        //System.out.println("This is the date it was posted: " + postDate);

        for (int i = 0; i < (text.length() - 1); i++){

            if (text.charAt(i) == '&') {

                tempTextAmp = text.substring(i);
                postAdd = text.substring(i, tempTextAmp.indexOf(' ') + i);
                //System.out.println("Your post belongs to this group: " + postAdd);
                tempTextAmp = postAdd;

            }

            if (text.charAt(i) == '#') {

                tempTextHashtag = text.substring(i);
                postAdd = text.substring(i, tempTextHashtag.indexOf(' ') + i);
                //System.out.println("Your post belongs to this tag: " + postAdd);
                tempTextHashtag = postAdd;

            }

            if (text.charAt(i) == '@'){

                tempTextAt = text.substring(i);
                postAdd = text.substring(i, tempTextAt.indexOf(' ') + i);
                //System.out.println("Your post will alert this user: " + postAdd);
                tempTextAt = postAdd;
            }


        }//for loop
        
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");

            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            //System.out.println("Result of SQL update: " + result);
            
            statement = conn.createStatement();
            sql = "SELECT MAX(postid) AS postid FROM posts;";
            //System.out.println(sql);
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            int highestID = rs.getInt("postid");
            //System.out.println(highestID + " is the highest ID");
            int newID = highestID + 1;
            //System.out.println(newID + " is the new ID");
            
            statement = conn.createStatement();
            String username = current.getUN();
            sql = ("INSERT INTO posts (text, privacy, username, date_time, postid)"
                    + " VALUES ('" + text + "', '" + privacy + "', '" + username + "', '" + postDate + "', " + newID +");");
            //System.out.println(sql);
            result = statement.executeUpdate(sql);
            
            statement = conn.createStatement();
            sql = ("UPDATE posts SET groups='" + tempTextAmp + "', hashtags='" + tempTextHashtag + "', "
                    + "users_tagged='" + tempTextAt + "' WHERE postid=" + newID + ";");
            //System.out.println(sql);
            result = statement.executeUpdate(sql);

            conn.close();
        }catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
    }//make a post
  
    public void EditProfile(User current){
        
        Scanner in = new Scanner(System.in);
        String fName;
        String lName;
        String sql;
        int result;
        
        System.out.println("What is your first name?");
        fName = in.nextLine();
        
        System.out.println("What is your last name?");
        lName = in.nextLine();
        
        /* Add their f/l name to their profile. */
        try{
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");

            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            
            statement = conn.createStatement();
            sql = "UPDATE users SET firstname = '" + fName + "', lastname = '"
                    + lName + "' WHERE usernames = '" + current.getUN() + "';";
            result = statement.executeUpdate(sql);
            
            System.out.println("Profile updated.");
            
            conn.close();
        }
        catch (SQLException ex) {
	    // handle any SQL errors
	    System.out.println("SQLException: " + ex.getMessage());
	    System.out.println("SQLState: " + ex.getSQLState());
	    System.out.println("VendorError: " + ex.getErrorCode());
        }
        
        
    }
    
    public void CreateGroup(User current){
        String groupName;
        String sql;
        int result;
        boolean alreadyExists = false;
        Scanner in = new Scanner(System.in);

        System.out.println("What do you want to name your group?");
        groupName = in.nextLine();


        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
            //System.out.println("Connection successful");

            Statement statement = conn.createStatement();
            sql = "use mydb;";
            result = statement.executeUpdate(sql);
            //System.out.println("Result of SQL update: " + result);

            //Check if the desired username is taken
            statement = conn.createStatement();
            sql = ("SELECT name FROM groups WHERE name = '" 
                    + groupName +"_group';");
            ResultSet rs1 = statement.executeQuery(sql);
            alreadyExists = rs1.next();

            if (!alreadyExists) {

                statement = conn.createStatement();
                sql = ("INSERT INTO groups (name) VALUES ('" + groupName.toLowerCase() + "_group');");
                result = statement.executeUpdate(sql);

                statement = conn.createStatement();
                sql = ("CREATE TABLE `mydb`.`" + groupName + "_group` (\n" +
                    "  `users` VARCHAR(45) NULL,\n" +
                    "  `admin` VARCHAR(5) NULL);");
                /*CREATE TABLE `mydb`.`umbreon` (
                  `users` VARCHAR(45) NULL,
                  `admin` VARCHAR(5) NULL);*/
                result = statement.executeUpdate(sql);

                System.out.println("Group " + groupName + " created successfully.");

                statement = conn.createStatement();
                sql = ("INSERT INTO " + groupName + "_group (users, admin) VALUES"
                        + " ('" + current.getUN() + "', 'yes');");
                result = statement.executeUpdate(sql);
                
                statement = conn.createStatement();
                sql = ("INSERT INTO " + current.getUN() + " (Groups) VALUES ('" + groupName + "');");

                System.out.println("User " + current.getUN() + " added to group "
                    + groupName + " as admin.");
                System.out.println(groupName + "added to your followed groups.");

            }else{
                System.out.println("Error: There is already a group with that name.");
            }

            conn.close();
        }catch (SQLException ex) {
            // handle any SQL errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
    
    public void DeleteGroup(User current){
        
        String group;
        String ans;
        Scanner in = new Scanner(System.in);
        Search find = new Search();
        String sql;
        int result;
        
        System.out.println("What is the name of the group you want to delete?");
        group = in.nextLine();
        if(find.SearchByGroup(group, true)){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
                //System.out.println("Connection successful");

                Statement statement = conn.createStatement();
                sql = "use mydb;";
                result = statement.executeUpdate(sql);
                //System.out.println("Result of SQL update: " + result);
                
                statement = conn.createStatement();
                sql = ("SELECT name FROM groups WHERE name = '" 
                        + group +"_group';");
                ResultSet rs1 = statement.executeQuery(sql);
                
                if(rs1.isBeforeFirst()){
                    rs1.next();
                    
                    System.out.println("Are you sure you want to delete the group " + group + "? (Yes/No)");
                    ans = in.nextLine();
                    
                    if(ans.equals("yes") || ans.equals("Yes")){
                        
                        statement = conn.createStatement();
                        sql = ("SELECT admin FROM " + group + "_group WHERE user = '" 
                                + current.getUN() +"';");
                        ResultSet rs2 = statement.executeQuery(sql);
                        
                        rs2.next();
                        
                        if((rs2.getString("admin").equals("yes")) || (current.getID() < 1000)){
                            statement = conn.createStatement();
                            sql = ("DROP TABLE " + group + "_group;");
                            result = statement.executeUpdate(sql);
                            
                            statement = conn.createStatement();
                            sql = ("DELETE FROM groups WHERE name='" + group + "_group';");
                            result = statement.executeUpdate(sql);
                            
                            System.out.println("\nGroup successfully deleted. "
                                    + "\nReturning to main menu.\n");
                            
                        }else{
                            System.out.println("\nThe current account does not have administrative privileges"
                                    + "for the selected group."
                                    + "\nReturning to main menu.\n");
                        }//if admin
                    }//if wants to delete group
                    else{
                        System.out.println("\nGroup not deleted. Returning to main menu.");
                    }
                    
                }else{
                    System.out.println("Error: No group esists with that name. Returning to main menu.");
                }//if rs1.isBeforeFirst()
                
                conn.close();
            }catch (SQLException ex) {
                // handle any SQL errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }//if group found
    }//Delete group
    
    public void DeleteUser(User current){
        
        String user;
        String ans;
        Scanner in = new Scanner(System.in);
        Search find = new Search();
        String sql;
        int result;
        
        System.out.println("What is the name of the user you want to delete?");
        user = in.nextLine();
        if(find.SearchByUser(user, true)){
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "8e7e082e");
                //System.out.println("Connection successful");

                Statement statement = conn.createStatement();
                sql = "use mydb;";
                result = statement.executeUpdate(sql);
                //System.out.println("Result of SQL update: " + result);
                
                statement = conn.createStatement();
                sql = ("SELECT usernames FROM users WHERE usernames = '" 
                        + user +"';");
                ResultSet rs1 = statement.executeQuery(sql);
                
                if(rs1.isBeforeFirst()){
                    rs1.next();
                    
                    System.out.println("Are you sure you want to delete " + rs1.getString("usernames") + "? (Yes/No)");
                    ans = in.nextLine();
                                        
                    if(ans.equals("yes") || ans.equals("Yes")){

                            statement = conn.createStatement();
                            sql = ("DROP TABLE " + user + ";");
                            result = statement.executeUpdate(sql);
                            
                            statement = conn.createStatement();
                            sql = ("DELETE FROM users WHERE usernames='" + user + "';");
                            result = statement.executeUpdate(sql);
                            
                            System.out.println("\nUser " + user + " successfully deleted. "
                                    + "\nReturning to main menu.\n");
                    }//if wants to delete user
                    else{
                        System.out.println("\nUser " + user + " not deleted. Returning to main menu.\n");
                    }
                    
                }else{
                    System.out.println("Error: No user exsists with that name. Returning to main menu.");
                }//if rs1.isBeforeFirst()
                
                conn.close();
            }catch (SQLException ex) {
                // handle any SQL errors
                System.out.println("SQLException: " + ex.getMessage());
                System.out.println("SQLState: " + ex.getSQLState());
                System.out.println("VendorError: " + ex.getErrorCode());
            }
        }//if user found
    }//Delete user
}
