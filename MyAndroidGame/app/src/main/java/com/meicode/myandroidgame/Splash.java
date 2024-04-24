package com.meicode.myandroidgame;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;


public class Splash extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);

        Thread myThread=new Thread(){
            @Override
            public void run() {
                try{

                    sleep(4000);
                    Intent startgame =new Intent(getApplicationContext(),GameMenu.class);
                    startActivity(startgame);
                    finish();




                }catch(InterruptedException e){e.printStackTrace();}

            }//end run
        };//end thread
        myThread.start();

    }//end method




}//end class

