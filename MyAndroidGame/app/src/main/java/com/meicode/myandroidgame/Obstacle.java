package com.meicode.myandroidgame;

import android.graphics.Canvas;
import android.graphics.Bitmap;
import java.util.Random;




public class Obstacle extends GameObject {

    private int score;
    private int speed;
    private Random rand = new Random();
    private Animation animation = new Animation();
    private Bitmap spritesheet;


    /**
     *
     * @param res
     * @param x
     * @param y
     * @param w
     * @param h
     * @param s
     * @param numFrames
     */


    public Obstacle(Bitmap res, int x, int y, int w, int h, int s, int numFrames){
        super.x = x;
        super.y = y;
        width = w;
        height = h;
        score = s;


        speed = 11;

        Bitmap [] image = new Bitmap[numFrames];

        spritesheet = res;

        for(int i = 0; i<image.length;i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        //we animate the obstacle
        animation.setFrames(image);
        //we set the delay of animation
        animation.setDelay(100-speed);




    }// end
    public void update(){

        x-=speed;
        animation.update();
    }

    public void draw(Canvas canvas){

        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}




    }

    public int getWidth()
    {
        //offset slightly for more realistic collision detection
        return width-10;
    }
}

