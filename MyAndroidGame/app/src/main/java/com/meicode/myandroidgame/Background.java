package com.meicode.myandroidgame;

import android.graphics.Bitmap;
import android.graphics.Canvas;

public class Background {

    private Bitmap image;
    private int x, y, dx;

    /**
     *
     * @param res
     */

    public Background(Bitmap res)
    {

        image = res;
        dx=GamePanel.MOVESPEED;
        //ok now lets move on to our Hero class and create some vars
    }
    public void update()
    {

        //So we need to change the x cord of our bg
        x+=dx;

        /**
         *And we must check if the bg start to move out of the screen...
         */
        //Dont worry we will set the Width in our next lesson...
        if(x<-GamePanel.WIDTH){
            /**
             *GamePanel.WIDTH represents the width of our entire devise screen
             */
            x=0;
        }
    }

    /**
     *In our next lesson we will develop the draw method and we modify the Gamepanel class
     */
    public void draw(Canvas canvas)
    {
        //first we will draw our first bg to the screen
        canvas.drawBitmap(image, x, y,null);
        //and if the bg is of the x limits of our screen
        //then behind the first one we must place again the same bg image
        if(x<0)
        {
            canvas.drawBitmap(image, x+GamePanel.WIDTH, y, null);
        }
    }
    //we also need a method to check every time the new value of x cord

}
