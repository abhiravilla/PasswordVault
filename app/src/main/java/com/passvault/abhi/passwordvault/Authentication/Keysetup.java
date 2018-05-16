package com.passvault.abhi.passwordvault.Authentication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.passvault.abhi.passwordvault.R;
import com.passvault.abhi.passwordvault.menu.MainActivity;

public class Keysetup extends AppCompatActivity implements View.OnClickListener {

    private  TextView first,second,third,fourth;
    private Button one,two,three,four,five,six,seven,eight,nine,zero,clear,reset;
    private String keyfirst,keysecond;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.keysetup);
        init();
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


            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("1");
                else if(keyfirst.length()==2) second.setText("1");
                else if(keyfirst.length()==3) third.setText("1");
                else if(keyfirst.length()==4) four.setText("1");
            }
            else{
                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("1");
                    else if(keyfirst.length()==2) second.setText("1");
                    else if(keyfirst.length()==3) third.setText("1");
                    else if(keyfirst.length()==4) four.setText("1");
                }
            }

        }else if (v.getId()==(R.id.two)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("2");
                else if(keyfirst.length()==2) second.setText("2");
                else if(keyfirst.length()==3) third.setText("2");
                else if(keyfirst.length()==4) four.setText("2");
            }
            else{
                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("2");
                    else if(keyfirst.length()==2) second.setText("2");
                    else if(keyfirst.length()==3) third.setText("2");
                    else if(keyfirst.length()==4) four.setText("2");
                }
            }

        }else if (v.getId()==(R.id.three)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("3");
                else if(keyfirst.length()==2) second.setText("3");
                else if(keyfirst.length()==3) third.setText("3");
                else if(keyfirst.length()==4) four.setText("3");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("3");
                    else if(keyfirst.length()==2) second.setText("3");
                    else if(keyfirst.length()==3) third.setText("3");
                    else if(keyfirst.length()==4) four.setText("3");
                }
            }
        }else if (v.getId()==(R.id.four)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("4");
                else if(keyfirst.length()==2) second.setText("4");
                else if(keyfirst.length()==3) third.setText("4");
                else if(keyfirst.length()==4) four.setText("4");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("4");
                    else if(keyfirst.length()==2) second.setText("4");
                    else if(keyfirst.length()==3) third.setText("4");
                    else if(keyfirst.length()==4) four.setText("4");
                }
            }

        }else if (v.getId()==(R.id.five)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("5");
                else if(keyfirst.length()==2) second.setText("5");
                else if(keyfirst.length()==3) third.setText("5");
                else if(keyfirst.length()==4) four.setText("5");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("5");
                    else if(keyfirst.length()==2) second.setText("5");
                    else if(keyfirst.length()==3) third.setText("5");
                    else if(keyfirst.length()==4) four.setText("5");
                }
            }

        }else if (v.getId()==(R.id.six)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("6");
                else if(keyfirst.length()==2) second.setText("6");
                else if(keyfirst.length()==3) third.setText("6");
                else if(keyfirst.length()==4) four.setText("6");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("6");
                    else if(keyfirst.length()==2) second.setText("6");
                    else if(keyfirst.length()==3) third.setText("6");
                    else if(keyfirst.length()==4) four.setText("6");
                }
            }
        }else if (v.getId()==(R.id.seven)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("7");
                else if(keyfirst.length()==2) second.setText("7");
                else if(keyfirst.length()==3) third.setText("7");
                else if(keyfirst.length()==4) four.setText("7");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("7");
                    else if(keyfirst.length()==2) second.setText("7");
                    else if(keyfirst.length()==3) third.setText("7");
                    else if(keyfirst.length()==4) four.setText("7");
                }
            }

        }else if (v.getId()==(R.id.eight)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("8");
                else if(keyfirst.length()==2) second.setText("8");
                else if(keyfirst.length()==3) third.setText("8");
                else if(keyfirst.length()==4) four.setText("8");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("8");
                    else if(keyfirst.length()==2) second.setText("8");
                    else if(keyfirst.length()==3) third.setText("8");
                    else if(keyfirst.length()==4) four.setText("8");
                }
            }

        }else if (v.getId()==(R.id.nine)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("9");
                else if(keyfirst.length()==2) second.setText("9");
                else if(keyfirst.length()==3) third.setText("9");
                else if(keyfirst.length()==4) four.setText("9");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("9");
                    else if(keyfirst.length()==2) second.setText("9");
                    else if(keyfirst.length()==3) third.setText("9");
                    else if(keyfirst.length()==4) four.setText("9");
                }
            }

        }else if (v.getId()==(R.id.zero)){

            if(!check(keyfirst)){
                keyfirst+=one.getText().toString();
                if(keyfirst.length()==1) first.setText("0");
                else if(keyfirst.length()==2) second.setText("0");
                else if(keyfirst.length()==3) third.setText("0");
                else if(keyfirst.length()==4) four.setText("0");
            }
            else{

                if(!checksecond(keysecond)){
                    keysecond+=one.getText().toString();
                    if(keyfirst.length()==1) first.setText("0");
                    else if(keyfirst.length()==2) second.setText("0");
                    else if(keyfirst.length()==3) third.setText("0");
                    else if(keyfirst.length()==4) four.setText("0");
                }
            }

        }else if (v.getId()==R.id.reset){

            }

        }else if (v.getId()==R.id.cancel){


        }
    }

    private void set(String value) {

        if(!check(keyfirst)){
            keyfirst+=one.getText().toString();
            if(keyfirst.length()==1) first.setText(value);
            else if(keyfirst.length()==2) second.setText(value);
            else if(keyfirst.length()==3) third.setText(value);
            else if(keyfirst.length()==4) four.setText(value);
        }
        else{
            if(!checksecond(keysecond)){
                keysecond+=one.getText().toString();
                if(keyfirst.length()==1) first.setText(value);
                else if(keyfirst.length()==2) second.setText(value);
                else if(keyfirst.length()==3) third.setText(value);
                else if(keyfirst.length()==4) four.setText(value);
            }
        }
    }

    private boolean checksecond(String keysecond) {
        if(keysecond.length()==4){
            checkkeys(keyfirst,keysecond);
            return true;
        }
        return false;
    }

    private void checkkeys(String keyfirst, String keysecond) {
        if(keyfirst.equals(keysecond)){
            store(keyfirst);
            Intent main=new Intent(Keysetup.this, MainActivity.class);
            startActivity(main);
        }
    }

    private void store(String keyfirst) {
    }

    private boolean check(String keyfirst){
        if(keyfirst.length()==4){
            Toast.makeText(this,"Re-enter the key",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
}
