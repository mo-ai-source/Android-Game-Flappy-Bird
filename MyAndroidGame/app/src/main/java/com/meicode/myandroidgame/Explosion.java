package com.meicode.myandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class Explosion {

    //?

    private int x;
    private int y;
    private int width;
    private int height;
    private int row;
    private Animation animation = new Animation();
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
    public Explosion(Bitmap res, int x, int y, int w, int h, int numFrames){

        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;

        Bitmap[] image = new Bitmap[numFrames];

        spritesheet = res;

        for(int i = 0; i<image.length; i++)
        {
            if(i%5==0&&i>0)row++;
            image[i] = Bitmap.createBitmap(spritesheet, (i-(5*row))*width, row*height, width, height);
        }
        animation.setFrames(image);
        animation.setDelay(7);

    }
    public void update(){

        if(!animation.playedOnce())
        {
            animation.update();
        }






    }

    public void draw(Canvas canvas){

        if(!animation.playedOnce())
        {
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }

    }

    public int getHeight(){return height;}


}

