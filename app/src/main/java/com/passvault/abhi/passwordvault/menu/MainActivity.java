package com.passvault.abhi.passwordvault.menu;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.passvault.abhi.passwordvault.Authentication.Authenticator;
import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.display.Homeadapter;
import com.passvault.abhi.passwordvault.display.SiteAdapter;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements Homeadapter.itemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewStub vs = (ViewStub)findViewById(R.id.vst);
        vs.setLayoutResource(R.layout.app_bar_main);
        View v=vs.inflate();
        ViewStub vst = (ViewStub)findViewById(R.id.vsbar);
        vst.setLayoutResource(R.layout.activity_logins);
        View vt =vst.inflate();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Home");
        setSupportActionBar(toolbar);
        List<String> entries = Arrays.asList("Logins","Generate","Save","Delete","Settings","Signout");
        RecyclerView rc = (RecyclerView) findViewById(R.id.recycleview);
        Homeadapter adapter = new Homeadapter(this);
        adapter.setdataEntries(entries);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new GridLayoutManager(this,2));
        rc.setAdapter(adapter);
        SnapHelper snapHelper= new LinearSnapHelper();
        snapHelper.attachToRecyclerView(rc);

    }
    public void setvalues(NavigationView navigationView){

        SharedPreferences sharedPref = getSharedPreferences("User", MODE_PRIVATE);
        String defaultname = getResources().getString(R.string.default_name);
        String name = sharedPref.getString(getString(R.string.user_name), defaultname);
        String defaultemail = getResources().getString(R.string.default_email);
        String email = sharedPref.getString(getString(R.string.user_email), defaultemail);
        String url = sharedPref.getString("imageurl", "");
        View headerView = navigationView.getHeaderView(0);
//        ImageView img = (ImageView) findViewById(R.id.imageView);
//        try{
//            URL rl = new URL(url);
//            img.setImageURI(img.setImageURI(url));
//        }catch(Exception e){
//
//        }
//        img.setImageURI(img.setImageURI(rl.toURI));
        TextView uiname = (TextView) headerView.findViewById(R.id.name);
        uiname.setText(name);
        TextView uiemail = (TextView) headerView.findViewById(R.id.email);
        uiemail.setText(email);
    }


    @Override
    public void onBackPressed() {

            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
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



    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public void onItemClick(String activity){
        // Handle navigation view item clicks here.

        if (activity == "Logins") {
            Intent in=new Intent(MainActivity.this,Logins.class);
            startActivity(in);
        } else if (activity == "Delete") {
        } else if (activity == "Settings") {
        } else if (activity == "Save") {
        }
        else if (activity == "Generate"){
            Intent in=new Intent(MainActivity.this,Generate.class);
            startActivity(in);
        }
        else{
             signout();
        }
    }

    private void signout() {
        GoogleSignInClient mGoogleSignInClient;
        todefault();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signOut();
        // web_client_id is used to comunicating with firebase
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_client_id))
                .requestEmail()
                .build();
        // [END config_signin]
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mGoogleSignInClient.signOut();
        // Takes back to login screen so user can login again
        Intent in=new Intent(MainActivity.this,Authenticator.class);
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


}
