package com.passvault.abhi.passwordvault.menu;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.passvault.abhi.passwordvault.Authentication.Authenticator;
import com.passvault.abhi.passwordvault.Encryption.Decryption;
import com.passvault.abhi.passwordvault.Encryption.Encryption;
import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.background.Passgen;
import com.passvault.abhi.passwordvault.data.Entries;
import com.passvault.abhi.passwordvault.data.UserTuple;
import com.passvault.abhi.passwordvault.display.AppDatabase;

import java.util.List;

import static android.view.View.GONE;

public class Generate extends AppCompatActivity implements View.OnClickListener {
    private int le=0;
    Context context;
    String enpass,uname,sname,epass;
    EditText site,username,length,exclusions;
    TextView spassword,ssitename,susername,texvalues,texclusions;
    private Button gen,regen,save,reset;
    CardView cardView, cardView2;
    String dpass="Password", duser="Username", dsite="Site Name";
    private String pass;
    String dec;
    private long sreturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inflate the layouts into activity_main
        ViewStub vs =(ViewStub)findViewById(R.id.vst);
        vs.setLayoutResource(R.layout.app_bar_main);
        View v=vs.inflate();
        ViewStub vst = (ViewStub)findViewById(R.id.vsbar);
        vst.setLayoutResource(R.layout.activity_generate);
        View vt =vst.inflate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Title of Toolbar
        toolbar.setTitle("Generate Password");
        setSupportActionBar(toolbar);
        init();
    }

    private void init() {

        site = (EditText) findViewById(R.id.site);
        username = (EditText) findViewById(R.id.username);
        length = (EditText) findViewById(R.id.length);
        exclusions = (EditText) findViewById(R.id.exclusions);
        spassword = (TextView) findViewById(R.id.spassword);
        ssitename = (TextView) findViewById(R.id.ssitename);
        susername = (TextView) findViewById(R.id.susername);
        texvalues = (TextView) findViewById(R.id.texvalues);
        texclusions = (TextView) findViewById(R.id.texclusions);
        gen = (Button) findViewById(R.id.gen);
        regen = (Button) findViewById(R.id.regen);
        save = (Button) findViewById(R.id.save);
        reset = (Button) findViewById(R.id.reset);
        cardView = (CardView) findViewById(R.id.cardview);
        cardView2 = (CardView) findViewById(R.id.cvgenerated);
        gen.setOnClickListener(this);
        regen.setOnClickListener(this);
        save.setOnClickListener(this);
        reset.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            new FetchDataTask().cancel(true);
            super.onBackPressed();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private void signout() {
        GoogleSignInClient mGoogleSignInClient;
        todefault();
        new Todefault().todefault(this);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        // web_client_id is used to comunicating with firebase
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
        // Takes back to login screen so user can login again
        Intent in=new Intent(Generate.this,Authenticator.class);
        startActivity(in);
    }
    private  void todefault() {
        // The user data is all set to default at the time of signout
        SharedPreferences userpref = getSharedPreferences("User", this.MODE_PRIVATE);
        SharedPreferences.Editor file = userpref.edit();
        file.putString("Email", "" + getResources().getString(R.string.default_email));
        file.putString("userid", "" + getResources().getString(R.string.default_id));
        file.putString("name", "" + getResources().getString(R.string.default_name));
        file.apply();
    }
    public String generate(int len){
        Passgen pg=new Passgen(len);
        return(pg.generate());
    }
    private String key(){
        SharedPreferences sharedPref = getSharedPreferences(
                "User", this.MODE_PRIVATE);
        final String passphrase = sharedPref.getString("userid", "none");
        Log.i("getkey",""+passphrase);
        return passphrase;
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.gen) {
            String len =length.getText().toString();

            uname =username.getText().toString();
            sname =site.getText().toString();
            if(TextUtils.isEmpty(uname)){
                Toast to = Toast.makeText(context,"Enter Username",Toast.LENGTH_LONG);
                to.show();
                return ;
            }else if(TextUtils.isEmpty(sname)){
                Toast to=Toast.makeText(context,"Enter Site name",Toast.LENGTH_LONG);
                to.show();
                return;
            }else if(TextUtils.isEmpty(len)){
                Toast.makeText(this,"Enter the Value first",Toast.LENGTH_LONG).show();
                return;
            }
              else {
                le=Integer.parseInt(len);
                pass  = generate(le);
                String exclu = exclusions.getText().toString();
                String exclusion = "["+exclu+"]";
                new FetchDataTask().execute("Encrypt");
                cardView.setVisibility(GONE);
                cardView2.setVisibility(View.VISIBLE);
                spassword.setText(pass);
                ssitename.setText(sname);
                susername.setText(uname);
                if(TextUtils.isEmpty(exclu)) {
                    texvalues.setVisibility(GONE);
                    texclusions.setVisibility(GONE);
                }
                regen.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                reset.setVisibility(View.VISIBLE);
            }

        }else if (i == R.id.regen) {
            pass = generate(le);
            spassword.setText(pass);
             new Generate.FetchDataTask().execute("Encrypt");

        }else if (i == R.id.save){
            save();
        }else if (i == R.id.reset){

        }
    }

    private void save() {
        new FetchDataTask().execute("store");
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (sreturn == 1) {
                   Toast.makeText(Generate.this, "Save Successful", Toast.LENGTH_SHORT).show();
                   reset();
                }
                else{
                   Toast.makeText(Generate.this, "Error while Saving", Toast.LENGTH_SHORT).show();
                 }
            }
            }, 100);
    }

    public void reset(){
        spassword.setText(dpass);
        ssitename.setText(dsite);
        susername.setText(duser);
        texvalues.setText("Values");
        texclusions.setVisibility(View.VISIBLE);
        cardView2.setVisibility(GONE);
        cardView.setVisibility(View.VISIBLE);
        save.setVisibility(GONE);
        reset.setVisibility(GONE);
        regen.setVisibility(GONE);
        gen.setVisibility(View.VISIBLE);
        site.setText("");
        username.setText("");
        length.setText("");
        exclusions.setText("");
    }

    public class FetchDataTask extends AsyncTask<String, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... params) {
            if (isCancelled()){
                Log.i("cancel","Background task is cancelled");
                return null;
            }
            /* If there's no zip code, there's nothing to look up. */
            if (params.length == 0) {
            }
            else {
                String todo = params[0];
                if (todo.equals("store")) {
                    try {
                        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "production")
                                .build();
                        long i = db.entryDao().insert(new Entries(sname.trim(), uname.trim(), enpass));
                        Log.i("index",""+i);
                        if (i > 0) {
                           sreturn = 1;
                        } else {
                            sreturn = 0;
                        }
                        Log.i("sreturn",""+sreturn);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else if (todo.equals("Encrypt")) {
                    enpass = new Encryption().encryp(pass, key());
                } else if (todo.equals("Decrypt")) {
                    Log.i("background","enpass "+enpass+" key "+key());
                    dec = new Decryption().decrypt(enpass, key());
                }
            }
            return null;
        }

    }

}

