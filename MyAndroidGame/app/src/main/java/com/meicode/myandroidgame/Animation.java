package com.meicode.myandroidgame;

import android.graphics.Bitmap;

//The Animation class will help us to animate the images through their sprites...
//We will create our own Animation class and not Animation from the android package
public class Animation {
    //So what vars we will need for that purpose
    //a bitamp table to keep the numbers of the image frames [0]frame [1] frame [2]frame
    private Bitmap[] frames;

    //we need also to know in witch frame of the image we are every time
    private int currentFrame;

    //we need a timer for the animation
    private long startTime;

    //and a delay (is the delay between the frames *How fast our animation is gonna be*)
    private long delay;

    //we need also a boolean for the images that are going to animate once in our screen
    //for example the explosion image we want to happened once when we kill an enemy!
    private boolean playedOnce;


    //in our constructor to create an animation we need the frames of an image
    //in witch frame we are.. and a timer


    public void setFrames(Bitmap[] frames)
    {
        //we get the frame images
        this.frames = frames;
        //every image will start from the 1st frame/sprite
        currentFrame = 0;
        //we set the time of our animation to our systems timer
        startTime = System.nanoTime();


    }//end constructor

    //Now we will set the Delay and the Frames
    public void setDelay(long d){delay = d;}
    public void setFrame(int i){currentFrame= i;}

    //We will create an update method
    public void update()
    {
        //this timer determines witch frame of the image is gonna be return every time
        //In General we use Timers in our game to determine what time an object will appear
        //in our screen and in the meantime what actions will do
        //All objects (hero,enemy,etc) have a timer...
        long elapsed = (System.nanoTime()-startTime)/1000000;

//Now if we set the delay of the animation hero to 10 millis


        if(elapsed>delay)
        {
            currentFrame++;
            startTime = System.nanoTime();
        }
        if(currentFrame == frames.length){
            currentFrame = 0;
            playedOnce = true;
        }


    }//end update




    public Bitmap getImage(){
        return frames[currentFrame];
    }

    /**
     * Last we need  get the Frames and to know the boolean value of the playedOnce var
     */

    public int getFrame(){return currentFrame;}
    public boolean playedOnce(){return playedOnce;}




}//end class
