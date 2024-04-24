package com.meicode.myandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

//ops we forgot the GameObject
public class Hero extends GameObject{

    //hero vars we will need

    //we need a bitmap var cause our image player is gonna be 2 images actually
    private Bitmap spritesheet;

    //we need a score
    private int score;

    //A var that has the y value every time we touch the screen
    private double dya;

    //a boolean var to know if hero is going up or down
    private boolean up;

    //another boolean var to know if we start  the game..
    private boolean playing;

    /**
     *
     * In this  lesson will explain what bitmap does
     * and how we can do animations with our images!
     */

    /**
     *First we need to get advantage of our Animation class
     *so lets create an object here.
     *and also we need a timer for our player
     */
    private Animation animation = new Animation();
    private long startTime;




    //So to create a hero we need

    /**
     *
     * @param res
     * @param w
     * @param h
     * @param numFrames
     */
    public Hero(Bitmap res, int w, int h, int numFrames){

//When we start the our hero well have already a position
//in the middle and to the left
        x = 100;//what?
        y = GamePanel.HEIGHT / 2;


        dy = 0;

        //we need a score var in the beginning is going to be 0
        score = 0;

        //we also need the w and h of the image so we can create a bitmap
        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;
//loop
        for (int i = 0; i < image.length; i++)
        {
            /**
             *Our hero image has 3 frames/sprites so [0] is the first sprite [1] the second [2] the third
             */
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }
        //now that we know all the info about the bitmap image
// we need to set the animation and the delay
        animation.setFrames(image);
        animation.setDelay(10);

        //Now we initiate the timer so we can use in the update method
        startTime = System.nanoTime();
    }//end hero constructor
//to be continued

    //We need to create a boolean method to know if we press the screen and hero goes up
    public void setUp(boolean b){up = b;}



    public void update(){
        //here is the timer of our player millis
        long elapsed = (System.nanoTime()-startTime)/1000000;

        //Now when our timer gets past 100 millis we want the score to auto increment by the time
        if(elapsed>100)
        {
            score++;
            startTime = System.nanoTime();
        }
        animation.update();


        if(up){
            dy = (int)(dya-=1.1);

        }
        else{
            dy = (int)(dya+=1.1);
        }
//Now we must set a limit if the player is pressing all the time the screen
//the hero is gonna have to much speed (speed limitation)
        if(dy>14)dy = 14;
        if(dy<-14)dy = -14;

        y += dy*2;
        dy = 0;


    }//end update



    public void draw(Canvas canvas){
        canvas.drawBitmap(animation.getImage(),x,y,null);
    }//end draw

    //last we need the below methods so we can use them
    public int getScore(){return score;}
    public boolean getPlaying(){return playing;}
    public void setPlaying(boolean b){playing = b;}
    public void resetDYA(){dya = 0;}
    public void resetScore(){score = 0;}




}//end class
