package com.passvault.abhi.passwordvault.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Ignore;

public class UserTuple {
    @ColumnInfo(name = "Username")
    private String username;
    @ColumnInfo(name="Password")
    private  String password;

    public UserTuple(String username,String password){
        this.username = username;
        this.password=password;
    }
    @Ignore
    public UserTuple(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String Password) {
        password = Password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String Username) {
        username = Username;
    }
}