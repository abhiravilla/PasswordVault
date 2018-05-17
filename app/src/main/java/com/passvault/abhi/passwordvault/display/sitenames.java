package com.passvault.abhi.passwordvault.display;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;

import com.passvault.abhi.passwordvault.R;


public class sitenames extends Activity {
    RecyclerView recyclerView ;
    RecyclerView.Adapter adpater;
    SnapHelper snapHelper;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logins);

    }
}
