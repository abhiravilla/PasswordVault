package com.passvault.abhi.passwordvault.display;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;

import com.passvault.abhi.passwordvault.Encryption.Decryption;
import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.data.UserTuple;
import java.util.List;

public class Usertuple extends AppCompatActivity implements EntryAdapter.itemClickListener {
    RecyclerView recyclerView ;
    RecyclerView.Adapter adpater;
    SnapHelper snapHelper;
    List<UserTuple> entries;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        Bundle extras = getIntent().getExtras();
        String site= extras.getString("sitename");
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                .allowMainThreadQueries()
                .build();
        entries =db.entryDao().getSpecific(site);
        for(UserTuple ut : entries){
            Log.i("password",ut.getPassword());
            String st = new Decryption().decrypt(ut.getPassword(),key());
            Log.i("After decryption",st);
            ut.setPassword(st);
        }
        Log.i("Logins","Size of returned list "+entries.size());
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adpater = new EntryAdapter(entries,this);
        recyclerView.setAdapter(adpater);
        snapHelper= new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void onItemClick(UserTuple utuple) {

    }
    private String key(){
        SharedPreferences sharedPref = getSharedPreferences(
                "User", this.MODE_PRIVATE);
        final String passphrase = sharedPref.getString("userid", "none");
        return passphrase;
    }
}
