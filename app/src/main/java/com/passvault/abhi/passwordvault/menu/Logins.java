package com.passvault.abhi.passwordvault.menu;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.passvault.abhi.passwordvault.Authentication.Authenticator;
import com.passvault.abhi.passwordvault.Encryption.Decryption;
import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.data.UserTuple;
import com.passvault.abhi.passwordvault.display.AppDatabase;
import com.passvault.abhi.passwordvault.display.SiteAdapter;
import com.passvault.abhi.passwordvault.display.Usertuple;

import java.util.List;
import static android.content.res.Configuration.ORIENTATION_LANDSCAPE;
import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

public class Logins extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener , SiteAdapter.itemClickListener {
    RecyclerView recyclerView ;
    SiteAdapter adapater;
    SnapHelper snapHelper;
    List<String> sites;
    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Inflate the layouts into activity_main
        ViewStub vs =(ViewStub)findViewById(R.id.vst);
        vs.setLayoutResource(R.layout.app_bar_main);
        View v=vs.inflate();
        ViewStub vst = (ViewStub)findViewById(R.id.vsbar);
        vst.setLayoutResource(R.layout.activity_logins);
        View vt =vst.inflate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Title of Toolbar
        toolbar.setTitle("Site Names");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        hideItem();
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapater = new SiteAdapter(this);
        recyclerView.setAdapter(adapater);
        snapHelper= new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        mLoadingIndicator = (ProgressBar) findViewById(R.id.pb_loading_indicator);
        dataView();

    }
    public void dataView() {
        recyclerView.setVisibility(View.VISIBLE);
        new Logins.FetchDataTask().execute();
    }
    public void showerror(){
        recyclerView.setVisibility(View.GONE);
        Toast.makeText(this,"Error in retrieving",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {

        Log.i("landscape","In OnConfig");
        if(newConfig.orientation==ORIENTATION_LANDSCAPE){
            recyclerView.setLayoutManager(new GridLayoutManager(this,2));
            recyclerView.setAdapter(adapater);
            super.onConfigurationChanged(newConfig);
        }
        else if (newConfig.orientation==ORIENTATION_PORTRAIT){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(adapater);
            super.onConfigurationChanged(newConfig);

        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    private void hideItem()
    {
        // Hides the Logins option in menu since it is the current Activity
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu nav_Menu = navigationView.getMenu();
        nav_Menu.findItem(R.id.nav_logins).setVisible(false);
    }
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected (MenuItem item){
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            Intent in=new Intent(this,MainActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_delete) {

        } else if (id == R.id.nav_settings) {

        } else if (id == R.id.nav_signout) {
            signout();
        }
        else if (id == R.id.nav_generate){
            Intent in=new Intent(this,Generate.class);
            startActivity(in);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void signout() {
        GoogleSignInClient mGoogleSignInClient;
        todefault();
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
        Intent in=new Intent(Logins.this,Authenticator.class);
        startActivity(in);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

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

    @Override
    public void onItemClick(String sitename) {

        Intent in = new Intent(Logins.this, Usertuple.class);
        in.putExtra("sitename",sitename);
        startActivity(in);
    }

    public class FetchDataTask extends AsyncTask<Void, Void, List<String>> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<String> doInBackground(Void... params) {

            /* If there's no zip code, there's nothing to look up. */
            try {
                AppDatabase db = Room.databaseBuilder(getApplicationContext(),AppDatabase.class, "production")
                        .build();
                sites =db.entryDao().getSnames();
                Log.i("Background","returned list "+sites.size());
                return sites;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<String> Data) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (Data != null) {
                adapater.setdataEntries(Data);
            } else {
                showerror();
            }
        }
    }
}
