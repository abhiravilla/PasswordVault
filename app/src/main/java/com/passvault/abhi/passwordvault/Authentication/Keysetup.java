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

public class Keysetup extends AppCompatActivity implements View.OnClickListener {

    private  TextView first,second,third,fourth;
    private Button one,two,three,four,five,six,seven,eight,nine,zero,clear,reset;
    private String keyfirst="",keysecond="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keysetup);
        init();
    }
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
    private void init(){

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
        reset = (Button) findViewById(R.id.reset);
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
        reset.setOnClickListener(this);
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
        }else if (v.getId()==R.id.reset){
            reset();
        }else if (v.getId()==R.id.cancel){
            cancel();
        }
}
    private void set(String value) {
        if(!check(keyfirst,value)){
            keyfirst+=value;
            Log.i("keysetup","keyFirst "+keyfirst);
            if(keyfirst.length()==1) first.setText(value);
            else if(keyfirst.length()==2) second.setText(value);
            else if(keyfirst.length()==3) third.setText(value);
            else if(keyfirst.length()==4) four.setText(value);
        }
    }
    public void cancel (){
        if(keyfirst.length()<4) {
            if (keyfirst.length() == 1){
                clear.setVisibility(View.GONE);
                reset.setVisibility(View.GONE);
                keyfirst = "";
                first.setText("*");
            }
            else if (keyfirst.length() == 2){
                keyfirst = keyfirst.substring(0,1);
                second.setText("*");
            }
            else if (keyfirst.length() == 3){
                keyfirst = keyfirst.substring(0,2);
                third.setText("*");
            }
        }
        else{
            if (keysecond.length() == 1){
                clear.setVisibility(View.GONE);
                keysecond="";
                first.setText("*");
            }
            else if (keysecond.length() == 2){
                keysecond=keysecond.substring(0,1);
                second.setText("*");
            }
            else if (keysecond.length() == 3){
                keysecond=keysecond.substring(0,1);
                third.setText("*");
            }
        }

    }
    private void reset(){
        first.setText("*");
        second.setText("*");
        third.setText("*");
        fourth.setText("*");
        keyfirst="";
        keysecond="";
        reset.setVisibility(View.GONE);
        clear.setVisibility(View.GONE);
    }

    // store key here in the database
    private void store(String keyfirst) {
        SharedPreferences userpref = getSharedPreferences("passvaultabhikey", this.MODE_PRIVATE);
        SharedPreferences.Editor file = userpref.edit();
        file.putString("passcode", ""+keyfirst);
        file.apply();

    }
    private boolean check(String keyfirst,String value){
        if(keyfirst.length()==1) {
            reset.setVisibility(View.VISIBLE);
            clear.setVisibility(View.VISIBLE);
        }
        if(keyfirst.length()==3){
                this.keyfirst+=value;
            Log.i("keysetup","keyFirst "+this.keyfirst);
            four.setText(value);
                clear.setVisibility(View.GONE);
                first.setText("*");
                second.setText("*");
                third.setText("*");
                fourth.setText("*");
                Toast.makeText(this, "Re-enter the key", Toast.LENGTH_SHORT).show();
                return  true;
        }
        else if (keyfirst.length()==4){
            checksecond(keysecond,value);
            return true;

        }
        else
            return false;
    }
    private void checksecond(String keysecond,String value) {
        if(keysecond.length()==1){
            clear.setVisibility(View.VISIBLE);
        }
        if(keysecond.length()==3){
            Log.i("keysetup","keysecond "+this.keysecond);
            this.keysecond+=value;
            four.setText(value);
            first.setText("*");
            second.setText("*");
            third.setText("*");
            fourth.setText("*");
            checkkeys(keyfirst,this.keysecond);
        }
        else{
            Log.i("keysetup","keysecond "+keysecond);
            this.keysecond+=value;
            if(this.keysecond.length()==1) first.setText(value);
            else if(this.keysecond.length()==2) second.setText(value);
            else if(this.keysecond.length()==3) third.setText(value);
        }
    }
    private void checkkeys(String keyfirst, String keysecond) {
        if(keyfirst.equals(keysecond)){
            store(keyfirst);
            reset();
            Intent main=new Intent(Keysetup.this, MainActivity.class);
            startActivity(main);
        }
        else{
            Toast.makeText(this,"Keys do not match......Please try again",Toast.LENGTH_SHORT).show();
            reset();
        }
    }
}
