package com.meicode.myandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Bullet extends GameObject{

    //vars we will need
//the speed of the bullet
    private int speed;

    //the animation to animate the bullet image
    private Animation animation = new Animation();
    //a bitmap reference of the image
    private Bitmap spritesheet;


    /**
     *
     * @param res
     * @param x
     * @param y
     * @param w
     * @param h
     * @param numFrames
     */
    public Bullet(Bitmap res,int x, int y,int w, int h, int numFrames){
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        //the first bullet is gonna have 13 speed
        speed = 13;

        //we need all the frames of our bullet image
        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        //this loop help us to save all the frames of the bullet image to a new table
//so image[0]=is the 1st version of our image .... image[3]=the 4th version of the bullet
        for(int i = 0; i<image.length;i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, 0,  i*height, width, height);
        }

        //then we have all the info of the img and we can do the animation
        animation.setFrames(image);

        //then we set the delay of the animation between the frames
        animation.setDelay(120-speed);






    }//end

    public void update(){

//every sec we want for our example to change the speed of the bullet
        x+=speed-4;

        animation.update();

    }//end update

    public void draw(Canvas canvas){

//end then finally we draw the bullet on the screen
        try{

            canvas.drawBitmap(animation.getImage(),x-30,y,null);
        }catch(Exception e){}
    }//end draw



}//end class

