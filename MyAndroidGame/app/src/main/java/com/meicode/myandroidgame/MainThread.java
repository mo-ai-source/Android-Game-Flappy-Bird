package com.meicode.myandroidgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;



public class MainThread extends Thread{

//Vars that we need for the FPS and the Average FBS

    private int FPS = 30;

    private double averageFPS;

    private SurfaceHolder surfaceHolder;

    private GamePanel gamePanel;


    private boolean running;



    /**
     *   The Canvas class holds the "draw" calls. To draw something, you need 4 basic components:
     *           A Bitmap to hold the pixels,
     *            a Canvas to host the draw calls (writing into the bitmap),
     *           a drawing primitive (e.g. Rect, Path, text, Bitmap),
     *			 and a paint
     */
    public static Canvas canvas;

    /**
     *
     * @param surfaceHolder
     * @param gamePanel
     */


    public MainThread(SurfaceHolder surfaceHolder, GamePanel gamePanel) {

        super();
        this.surfaceHolder = surfaceHolder;
        this.gamePanel = gamePanel;

    }//end of constructor

    /**Now we will override the run method. All thread have a run method
     *So to get advantage of the thread we will write our time code inside the run method
     *so as you understand a thread is a "spider with a clock" The wants to Know when
     *an action is occurred in our game!
     */
    @Override
    public void run()
    {
/**
 *Inside the run method we want every second
 * to catch the 30 frames **/
        long startTime;
        long timeMillis;
        long waitTime;
        long totalTime = 0;


        int frameCount =0;

        //targetTime?
        /**
         What is the target time?
         In our game out time is in millis
         So we need to seconds to millis
         1 sec=1000 millis
         We have 30 frames but how long a frame is going to last in our screen?
         1000 millis/30 frames=33,3 millis per frame

         Conclusion 30 Frames time ([1 frame=33,3millis],[2 frame = 33,3],... ,[30 frame = 33,3 millis] = 1000 millis or 1 sec)
         */
        long targetTime = 1000/FPS;

        //before we continue let me show you smthing
        while(running) {


            startTime = System.nanoTime();

            canvas = null;
            //try locking the canvas for pixel editing
            /**
             * we need to use the canvas to paint our object on our screen every frame
             * we will develop our canvas code inside a try{}catch() in case smthing goes wrong
             */
            try{
                //we lock canvas to our content view
                canvas = this.surfaceHolder.lockCanvas();


                synchronized (surfaceHolder){

                    this.gamePanel.update();


                    this.gamePanel.draw(canvas);


                }//end synchronised


            } catch (Exception e) {
            }//end try
            finally{
                if(canvas!=null)
                {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                    catch(Exception e){e.printStackTrace();}
                }
            }

            //time calculation
            timeMillis = (System.nanoTime() - startTime) / 1000000;

            //WHAT IS THAT?
            /**
             *The time we will wait till the next frame enter the loop
             */

            waitTime = targetTime-timeMillis;

            //while we wait WE pause game for sometime
            //Don worry the waitTime between the frames
            //is so little that you cant notice it....

            try{
                this.sleep(waitTime);
            }catch(Exception e){}

            totalTime += System.nanoTime()-startTime;
            frameCount++;



            if(frameCount == FPS)
            {
                averageFPS = 1000/((totalTime/frameCount)/1000000);
                frameCount =0;
                totalTime = 0;
                System.out.println("Average Frame per Sec "+averageFPS);
            }







        }//end while





    }//end of run method

    public void setRunning(boolean b)
    {
        running=b;
    }



}//end of thread class

