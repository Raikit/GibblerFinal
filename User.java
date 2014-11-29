/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Kira
 */
public class User {
    
    public void User(){
        
        name = null;
        iD = 0;
    }
    
    public void User(String uN, int uID){
        
        name = uN;
        iD = uID;
    }
    
    public void setUN(String uN){
        name = uN;
    }
    
    public void setID(int uID){
        iD = uID;
    }
    
    public String getUN(){
        return name;
    }
    
    public int getID(){
        return iD;
    }
    
    String name;
    int iD;
}
