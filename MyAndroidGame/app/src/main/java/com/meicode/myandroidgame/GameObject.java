package com.meicode.myandroidgame;


import android.graphics.Rect;

public abstract class GameObject {


    protected int x;
    protected int y;
    protected int dx;
    protected int dy;
    protected int width;
    protected int height;

    //we mst create the set and get methods
    public void setX(int x)
    {
        this.x = x;
    }

    public int getX()
    {
        return x;
    }
    //same for y cord
    public void setY(int y)
    {
        this.y = y;
    }
    public int getY()
    {
        return y;
    }

    //now we need the height and the width of our objects...we already set the values of them when we created them with Gimp


    public int getHeight()
    {
        return height;
    }
    public int getWidth()
    {
        return width;
    }

    //now we need the rect method to create a rectangle around our image..we will use later for collision checking
    //so


    public Rect getRectangle()
    {
        return new Rect(x, y, x+width, y+height);
    }


}
