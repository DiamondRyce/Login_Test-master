package com.example.patrick.login_test;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Patrick on 11/21/2015.
 */
public class UserLocalStore {

    public static final String SP_NAME = "userDetails";
    SharedPreferences userLocalDatabase;

    public UserLocalStore(Context context){
        userLocalDatabase = context.getSharedPreferences(SP_NAME, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putInt("StudentID",user.studentID);
        spEditor.putString("password",user.password);
        spEditor.putInt("roomNumber",user.roomNumber);
        spEditor.commit();
    }
    public User getLoggedInUser(){
        int studentID = userLocalDatabase.getInt("studentID", 0);
        String password = userLocalDatabase.getString("password", 0 + "");
        int roomNumber = userLocalDatabase.getInt("roomNumber", 0);

        User storedUser = new User(studentID,password,roomNumber);
        return storedUser;

    }
    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggin(){
        if(userLocalDatabase.getBoolean("loggedIn", false)== true){
            return true;
        } else{
            return false;
        }

    }
    public void clearUserData(){
        SharedPreferences.Editor spEditor = userLocalDatabase.edit();
        spEditor.clear();
        spEditor.commit();
    }
}
