package com.passvault.abhi.passwordvault.data;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.support.annotation.NonNull;

// First few lines to say the table has composite primary key and generates composite index for Sitename and Username. If we have single primarykey we can use
// @Primarykey annotation
@Entity(primaryKeys = {"Sitename","Username"},
        tableName = "data",
        indices = { @Index(value = {"Sitename", "Username"})})
public class Entries {
    @NonNull
    private String Sitename;
    @NonNull
    private String Username;
    @ColumnInfo(name = "Password")
    private  String Password;

    public Entries(String Sitename, String Username, String Password) {
        this.Sitename = Sitename;
        this.Username= Username;
        this.Password = Password;
    }
    public String getPassword() { return Password; }

    public void setPassword(String password) {
        Password = password;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getSitename() {
        return Sitename;
    }

    public void setSitename(String sitename) {
        Sitename = sitename;
    }
}
