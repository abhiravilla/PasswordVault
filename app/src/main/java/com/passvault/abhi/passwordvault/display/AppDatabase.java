package com.passvault.abhi.passwordvault.display;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.passvault.abhi.passwordvault.data.Entries;
import com.passvault.abhi.passwordvault.data.EntryDao;

@Database(entities = {Entries.class},version=1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract EntryDao entryDao();
}