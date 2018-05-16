package com.passvault.abhi.passwordvault.Authentication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.passvault.abhi.passwordvault.R;

public class Authenticator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewStub vs =(ViewStub)findViewById(R.id.vst);
        vs.setLayoutResource(R.layout.app_bar_main);
        View v=vs.inflate();
        ViewStub vst = (ViewStub)findViewById(R.id.vsbar);
        vst.setLayoutResource(R.layout.activity_authenticator);
        View vt =vst.inflate();
    }
}
