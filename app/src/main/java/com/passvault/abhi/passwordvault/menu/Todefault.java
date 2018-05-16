package com.passvault.abhi.passwordvault.menu;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import com.passvault.abhi.passwordvault.R;

public class Todefault extends AppCompatActivity{
    public  void todefault(Context ct) {
        SharedPreferences userpref = getSharedPreferences("User", ct.MODE_PRIVATE);
        SharedPreferences.Editor file = userpref.edit();
        file.putString("Email", "" + getResources().getString(R.string.default_email));
        file.putString("userid", "" + getResources().getString(R.string.default_id));
        file.putString("name", "" + getResources().getString(R.string.default_name));
        file.apply();
    }
}
