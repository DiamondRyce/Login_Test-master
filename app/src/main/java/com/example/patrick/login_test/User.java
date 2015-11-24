package com.example.patrick.login_test;

/**
 * Created by Patrick on 11/21/2015.
 */
public class User {
    String password;
    int studentID, roomNumber;

    public User(int studentID,String password,int roomNumber){
        this.studentID= studentID;
        this.password= password;
        this.roomNumber=roomNumber;
    }



}
