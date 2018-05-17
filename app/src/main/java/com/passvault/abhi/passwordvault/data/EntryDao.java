package com.passvault.abhi.passwordvault.data;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface EntryDao {

    @Query("select Sitename,Username,Password from data")
    public List<Entries> getAllEntries();

    @Insert
    public long insert(Entries entry);

    @Update
    public int update(Entries entry);

    @Delete
    public int deleteUsers(Entries entry);

    @Query("select * from data where Sitename=:sitename and Username=:username")
    public Entries getrow(String sitename, String username);

    @Query("Select DISTINCT(Sitename) from data ")
    public List<String> getSnames();

    @Query("select Username,Password from data where Sitename =:sitename")
    public List<UserTuple> getSpecific(String sitename);

}