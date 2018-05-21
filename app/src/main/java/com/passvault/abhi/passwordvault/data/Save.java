package com.passvault.abhi.passwordvault.data;

import android.arch.persistence.room.Room;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.passvault.abhi.passwordvault.Encryption.Encryption;
import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.display.AppDatabase;
import com.passvault.abhi.passwordvault.menu.Generate;

import static android.view.View.GONE;

public class Save extends AppCompatActivity implements View.OnClickListener {

     private String pass,uname,sname,enpass;
     EditText site,username,length;
     private Button save,reset;
     private long sreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewStub vs =(ViewStub)findViewById(R.id.vst);
        vs.setLayoutResource(R.layout.app_bar_main);
        View v=vs.inflate();
        View lay= (View)findViewById(R.id.saveactivity);
        View laymain= (View)findViewById(R.id.home);
        laymain.setVisibility(GONE);
        lay.setVisibility(View.VISIBLE);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Title of Toolbar
        toolbar.setTitle("Save Password");
        setSupportActionBar(toolbar);
        init();

    }
    private void init() {
        Log.i("init","in init");
        site = (EditText) findViewById(R.id.Ssite);
        username = (EditText) findViewById(R.id.Susername);
        length = (EditText) findViewById(R.id.Slength);
        save = (Button) findViewById(R.id.Ssave);
        reset = (Button) findViewById(R.id.Sreset);
        save.setOnClickListener(this);
        reset.setOnClickListener(this);
        Log.i("init","done with init");
    }
    public void onClick(View v) {
        Log.i("view","in onclick listner");
        int i = v.getId();
        if (i == R.id.Ssave){
            Log.i("save","in save");
            pass =length.getText().toString();
            uname =username.getText().toString();
            sname =site.getText().toString();
            if(TextUtils.isEmpty(uname)){
                Toast to = Toast.makeText(this,"Enter Username",Toast.LENGTH_LONG);
                to.show();
                return ;
            }else if(TextUtils.isEmpty(sname)){
                Toast to=Toast.makeText(this,"Enter Site name",Toast.LENGTH_LONG);
                to.show();
                return;
            }else if(TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Enter the Value first",Toast.LENGTH_LONG).show();
                return;
            }else {
                new Save.FetchDataTask().execute("Encrypt");
                save();
            }
        }else if (i == R.id.Sreset){
                reset();
        }
    }
    private void save() {

        new Save.FetchDataTask().execute("store");
    }
    private void onsave(){
        if (sreturn == 1) {
            Toast.makeText(Save.this, "Save Successful", Toast.LENGTH_SHORT).show();
            reset();
        }
        else{
            Toast.makeText(Save.this, "Error while Saving", Toast.LENGTH_SHORT).show();
            reset();
        }
    }
    public class FetchDataTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            if (isCancelled()){
                Log.i("cancel","Background task is cancelled");
                return null;
            }
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
            }
            else {
                Log.i("background","in background store");
                String todo = params[0];
                if (todo.equals("store")) {
                    try {
                        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                                .build();
                        long i = db.entryDao().insert(new Entries(sname.trim(), uname.trim(), enpass));
                        Log.i("index",""+i);
                        if (i > 0) {
                            sreturn = 1;
                            return "store";
                        } else {
                            sreturn = 0;
                            return "store";
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (todo.equals("Encrypt")) {
                    Log.i("background","in background encrypt");
                    enpass = new Encryption().encryp(pass, key());
                    return "encrypt";
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String value) {
            if(value.equals("store")) {
                super.onPostExecute(value);
                Log.i("in post execute", "sdlma;sd");
                onsave();
            }
        }
    }
    private String key(){
        SharedPreferences sharedPref = getSharedPreferences(
                "User", this.MODE_PRIVATE);
        final String passphrase = sharedPref.getString("userid", "none");
        Log.i("getkey",""+passphrase);
        return passphrase;
    }
    public void reset(){
        save.setVisibility(View.VISIBLE);
        reset.setVisibility(View.VISIBLE);
        site.setText("");
        username.setText("");
        length.setText("");
    }




}
