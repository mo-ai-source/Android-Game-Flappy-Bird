package com.meicode.myandroidgame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.Window;

import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {


    GamePanel gameView;//extends SurfaceView
    FrameLayout game;// Sort of "holder" for everything we are placing
    RelativeLayout GameButtons;//Holder for the buttons

    @SuppressLint({"SetTextI18n","ResourceType"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gameView = new GamePanel(this);
        game = new FrameLayout(this);
        GameButtons = new RelativeLayout(this);

        //first button
        Button butOne = new Button(this);
        butOne.setText("Button1");
        butOne.setId(123456); //good to set ID to some random number because defaults new button id's all to same number

        //Second Button
        Button butTwo = new Button(this);
        butTwo.setText("Button2");
        butTwo.setId(789111); //good to set ID to some random number because defaults new button id's all to same number


        //Define the layout parameter for the button to wrap the content for both width and height
        RelativeLayout.LayoutParams b1 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        //Now for Button 2
        RelativeLayout.LayoutParams b2 = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

        //Wrapping the Content of the buttons, need to define the RelativeLayout width and height

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);

        //Now we set the match parent layout to the RelativeLayout that we named it as GameButton
        GameButtons.setLayoutParams(params);

        //add buttons to Relative layout wit the use of the addView method
        GameButtons.addView(butOne);
        GameButtons.addView(butTwo);

        //button 1 position in the Relative Layout
        b1.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        b1.addRule(RelativeLayout.ALIGN_PARENT_TOP, RelativeLayout.TRUE);
        butOne.setLayoutParams(b1);

        //button 2 position in the Relative Layout
        b2.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        b2.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        butTwo.setLayoutParams(b2);

        //turn off title
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        //set to full screen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        //Frame Layout
        game.addView(gameView);
        game.addView(GameButtons);

        //new content viewing ;)
        setContentView(game);


        setContentView(new GamePanel(this));


    }
}