package com.meicode.myandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;


public class RaccoonFriends extends GameObject{
    private Bitmap spritesheet;




    private Animation animation = new Animation();
    private long startTime;

    /**
     *
     * @param res
     * @param x
     * @param y
     * @param w
     * @param h
     * @param numFrames
     */

    public RaccoonFriends(Bitmap res,int x,int y, int w, int h, int numFrames) {

        super.x = x;
        super.y = y;


        height = h;
        width = w;

        Bitmap[] image = new Bitmap[numFrames];
        spritesheet = res;

        for (int i = 0; i < image.length; i++)
        {
            image[i] = Bitmap.createBitmap(spritesheet, i*width, 0, width, height);
        }

        animation.setFrames(image);
        animation.setDelay(10);
        startTime = System.nanoTime();

    }



    public void update()
    {

        animation.update();


    }
    public void draw(Canvas canvas)
    {
        try{
            canvas.drawBitmap(animation.getImage(),x,y,null);
        }catch(Exception e){}
    }

}

