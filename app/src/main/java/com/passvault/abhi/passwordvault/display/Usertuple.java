package com.passvault.abhi.passwordvault.display;

import android.app.Activity;
import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.passvault.abhi.passwordvault.Encryption.Decryption;
import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.data.UserTuple;
import java.util.List;

public class Usertuple extends AppCompatActivity implements EntryAdapter.itemClickListener {
    RecyclerView recyclerView ;
    EntryAdapter adapater;
    SnapHelper snapHelper;
    List<UserTuple> entries;
    private Bundle extras;
    private ProgressBar mLoadingIndicator;
    private FetchDataTask asynctask = new FetchDataTask();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);
        extras = getIntent().getExtras();
//        String site= extras.getString("sitename");
//        AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
//                .allowMainThreadQueries()
//                .build();
//        entries =db.entryDao().getSpecific(site);
//        for(UserTuple ut : entries){
//            Log.i("password",ut.getPassword());
//            String st = new Decryption().decrypt(ut.getPassword(),key());
//            Log.i("After decryption",st);
//            ut.setPassword(st);
//        }
//        Log.i("Logins","Size of returned list "+entries.size());
        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapater = new EntryAdapter(this);
        recyclerView.setAdapter(adapater);
        snapHelper= new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        dataView();

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

    @Override
    public void onBackPressed() {
        asynctask.cancel(true);
        super.onBackPressed();
    }

    public void dataView() {
        String site= extras.getString("sitename");
        recyclerView.setVisibility(View.VISIBLE);
        asynctask.execute(site);
    }
    public void showerror(){
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(this,"Error in retrieving",Toast.LENGTH_SHORT).show();

    }

    public class FetchDataTask extends AsyncTask<String, Void, List<UserTuple>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<UserTuple> doInBackground(String... params) {
            Log.i("cancel","in background task");
            if (isCancelled()){
                Log.i("cancel","Background task is cancelled");
                return null;
            }

            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
                return null;
            }

            String site = params[0];

            try {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                        .build();
                entries =db.entryDao().getSpecific(site);
                for(UserTuple ut : entries){
                    Log.i("password",ut.getPassword());
                    String st = new Decryption().decrypt(ut.getPassword(),key());
                    Log.i("After decryption",st);
                    ut.setPassword(st);
                }
                return entries;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<UserTuple> Data) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (Data != null) {
                adapater.setdataEntries(Data);
            } else {
                showerror();
            }
        }
    }

}
