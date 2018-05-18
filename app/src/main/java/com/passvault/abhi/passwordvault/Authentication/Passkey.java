package com.passvault.abhi.passwordvault.Authentication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.menu.MainActivity;

public class Passkey extends AppCompatActivity implements View.OnClickListener {

    TextView first,second,third,fourth;
    private Button one,two,three,four,five,six,seven,eight,nine,zero,clear,forgot;
    private String key="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passkey);
        init();

    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }

    private void init() {
        first = (TextView) findViewById(R.id.first);
        second = (TextView) findViewById(R.id.second);
        third = (TextView) findViewById(R.id.third);
        fourth = (TextView) findViewById(R.id.fourth);
        one= (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button)findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        forgot = (Button) findViewById(R.id.forgot);
        clear = (Button) findViewById(R.id.cancel);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        zero.setOnClickListener(this);
        clear.setOnClickListener(this);
        forgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==(R.id.one)){
            set("1");

        }else if (v.getId()==(R.id.two)){
            set("2");

        }else if (v.getId()==(R.id.three)){
            set("3");

        }else if (v.getId()==(R.id.four)){
            set("4");

        }else if (v.getId()==(R.id.five)){
            set("5");

        }else if (v.getId()==(R.id.six)){
            set("6");
        }else if (v.getId()==(R.id.seven)){
            set("7");
        }else if (v.getId()==(R.id.eight)){
            set("8");
        }else if (v.getId()==(R.id.nine)){
            set("9");
        }else if (v.getId()==(R.id.zero)){
            set("0");
        }else if (v.getId()==R.id.forgot){
            forgot();
        }else if (v.getId()==R.id.cancel){
            cancel();
        }
    }

    private void forgot() {
    }

    private void set(String value) {
            key+=value;
            Log.i("key","key "+key);
            if(key.length()==1){
                first.setVisibility(View.VISIBLE);
                clear.setVisibility(View.VISIBLE);
            }
            else if(key.length()==2) second.setVisibility(View.VISIBLE);
            else if(key.length()==3) third.setVisibility(View.VISIBLE);
            else if(key.length()==4){
                fourth.setVisibility(View.VISIBLE);
                check();
            }
        }

    private void check() {
        SharedPreferences sharedPref = getSharedPreferences("passvaultabhikey", MODE_PRIVATE);
        String k = sharedPref.getString("passcode", "");
        if(k==""){
            Toast.makeText(this,"Error no passkey",Toast.LENGTH_SHORT).show();
        }else{
            if(k.equals(key)){
                Intent in = new Intent(Passkey.this, MainActivity.class);
                startActivity(in);
            }
            else{
                Toast.makeText(this,"Wrong Password",Toast.LENGTH_SHORT).show();
              reset();
            }
        }
    }
    private void reset(){
        first.setVisibility(View.GONE);
        second.setVisibility(View.GONE);
        third.setVisibility(View.GONE);
        fourth.setVisibility(View.GONE);
        key="";
        clear.setVisibility(View.GONE);
    }
    public void cancel (){
            if (key.length() == 1){
                clear.setVisibility(View.GONE);
                key = "";
                first.setVisibility(View.GONE);
            }
            else if (key.length() == 2){
                key = key.substring(0,1);
                second.setVisibility(View.GONE);
            }
            else if (key.length() == 3){
                key = key.substring(0,2);
                third.setVisibility(View.GONE);
            }
    }

}

