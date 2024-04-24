package com.meicode.myandroidgame;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.Random;


//YOU NEED TO PLACE THE BORDERS
public class GamePanel extends SurfaceView implements SurfaceHolder.Callback {
    private Random rand = new Random();


    //Now we will set our game screen width and height
    public static final int WIDTH = 856;
    public static final int HEIGHT = 480;


//hero lifes
    Bitmap hearta;
    Bitmap heartb;
    Bitmap heartc;

    Bitmap mypanelscore;
    Bitmap raccoonbm;


    //Frontground
    Bitmap bottomsurfaceimage;

    private int hearts=3;
    //Fellow Raccoons
    private  ArrayList<RaccoonFriends> friendA;
    private  ArrayList<RaccoonFriends> friendB;
    private  ArrayList<RaccoonFriends> friendC;
    //counter how many raccoons are rescued
    private int wormrescued;
    //coin
    Bitmap coinbonus;
    public int herocoins;
    private long bonusStartTime;
    private ArrayList<Coin> mycoins;


    //music and sound
    MediaPlayer mp;
    SoundPool coinsound;
    int coinsoundid;

    //mypanel
    Bitmap mypanel;


    private int maxBorderHeight;
    private int minBorderHeight;

    //increase to slow down difficulty progression, decrease to speed up difficulty progression
    private int progressDenom = 20;

    private boolean topDown = true;
    private boolean botDown = true;


    public static final int MOVESPEED = -5;


    //We also need an obj reference of the Background class
    private Background bg;

    //The same process as Background we create an object ref of our hero
    private Hero hero;
    //and now lets do some coding at the surfaceCreated method



    private ArrayList<Bullet> bullet;

    private long bulletStartTime;

    //step 1:  we set refs for an Arraylist and the enemy Timer

    private ArrayList<Enemy> alien;
    private long  alienStartTime;

    private ArrayList<Enemy> aliensecond;
    private long  secondalienStartTime;

    private ArrayList<Enemy> birdenemy;
    private long  ChickenStartTime;

    private ArrayList<Mine> landmine;
    private long  mineStartTime;

    private ArrayList<PrisonerWorm> prisoner;
    private long  prisonerStartTime;


    private ArrayList<Obstacle> obstacletop;
    private long  obstacletopStartTime;

    //if alien is killed

    SoundPool alienonedeadsound;
    int alienonedeadsoundid;

//if aliensecond is killed

    SoundPool alienseconddeadsound;
    int alienseconddeadsoundid;

//My Friends

    SoundPool fartsound;
    int fartsoundid;

    SoundPool fartlunchsound;
    int fartlunchsoundid;

    //if player get hurt

    SoundPool playerpain;
    int playerpainid;

    //if enemybird is killed

    SoundPool birdhurtsound;
    int birdhurtsoundid;

    private ArrayList<Obstacle> obstacle;
    private long  obstacleStartTime;

    //add border ref
    private ArrayList<Borderbottom> botborder;

    private long  borderStartTime;


    //vars to reset the game
    private boolean newGameCreated;



    private long startReset;

    private boolean reset;


    //we forgot to use this var
    //We need this var to disappear our hero from the screen when we lose
    //and appear again when we reset the game newgame()
    private boolean dissapear;

    private boolean started;

    private Explosion explosion;

    private int best;








    //reference of the MainThread obj
    private MainThread thread;

    //lets create the constructor of our new class,that is going to help us calling objects and methods!
    public  GamePanel(Context context){

 //As the name suggests, it's the context of current state of the application/object.
 // It lets newly-created objects understand what has been going on.

        super(context);

        //game music
        mp = MediaPlayer.create(context, R.raw.arcademusicloop);

        //coin sound
        coinsound = new SoundPool(99, AudioManager.STREAM_MUSIC,0);
        coinsoundid= coinsound.load(context, R.raw.pickedcoin,1);

        playerpain= new SoundPool(99, AudioManager.STREAM_MUSIC,0);
        playerpainid= playerpain.load(context, R.raw.playerhurt,1);

        alienonedeadsound= new SoundPool(99, AudioManager.STREAM_MUSIC,0);
        alienonedeadsoundid= alienonedeadsound.load(context, R.raw.aliendeath,1);

        alienseconddeadsound= new SoundPool(99, AudioManager.STREAM_MUSIC,0);
        alienseconddeadsoundid= alienseconddeadsound.load(context, R.raw.enemydeath,1);

        //Friends sound
        fartsound= new SoundPool(99, AudioManager.STREAM_MUSIC,0);
        fartsoundid= fartsound.load(context, R.raw.fart,1);

        //Friends sound
        fartlunchsound= new SoundPool(99, AudioManager.STREAM_MUSIC,0);
        fartlunchsoundid= fartlunchsound.load(context, R.raw.fartlunch,1);

        birdhurtsound= new SoundPool(99, AudioManager.STREAM_MUSIC,0);
        birdhurtsoundid= birdhurtsound.load(context, R.raw.birddeath,1);

        //add the callback to the surfaceholder to intercept events
        getHolder().addCallback(this);

        //object


        //make gamePanel focusable so it can handle events
        setFocusable(true);




    }//end of content view construcron



    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        thread = new MainThread(getHolder(), this);
        //in our screen we draw our image
        bg = new Background(BitmapFactory.decodeResource(getResources(), R.drawable.background));
        //we create the hero obj
        hero = new Hero(BitmapFactory.decodeResource(getResources(), R.drawable.hero), 45, 45, 2);

        bullet = new ArrayList<Bullet>();
        bulletStartTime=  System.nanoTime();

        //step 2: we create the enemy obj
        alien=new ArrayList<Enemy>();
        alienStartTime= System.nanoTime();

        aliensecond=new ArrayList<Enemy>();
        secondalienStartTime= System.nanoTime();

        obstacle=new ArrayList<Obstacle>();
        obstacleStartTime= System.nanoTime();

        obstacletop=new ArrayList<Obstacle>();
        obstacletopStartTime= System.nanoTime();

        //create obj bot border
        botborder=new ArrayList<Borderbottom>();
        borderStartTime= System.nanoTime();



        //create coin obj and timer
        mycoins = new ArrayList<Coin>();
        bonusStartTime = System.nanoTime();

        //friends
        friendA=new ArrayList<RaccoonFriends>();
        friendB=new ArrayList<RaccoonFriends>();
        friendC=new ArrayList<RaccoonFriends>();


        prisoner=new ArrayList<PrisonerWorm>();
        prisonerStartTime= System.nanoTime();


        birdenemy=new ArrayList<Enemy>();
        ChickenStartTime= System.nanoTime();

        landmine=new ArrayList<Mine>();
        mineStartTime= System.nanoTime();
//?top border? nothing

        //the hard part now




        //lets set our dx -5 so our bg image slowly move off the screen with -5 speed


        //if you like you can set dx different values and see what happens when we eventually run the game...
        //we can safely start the game loop


        //we can safely start the game loop
        thread.setRunning(true);
        thread.start();
    }


    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;

        int counter=0;
        //we need to set a counter so we dont have an infinite while for our thread
        //and we forgot to set the retry var to false (to inform the system that what created the thread and stop trying...)
        while(retry && counter<1000)
        {
            try{
                thread.setRunning(false);

                thread.join();


                retry=false;
                thread=null;

            }catch (InterruptedException e){e.printStackTrace();}




        }//end while

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //and here we want our hero do smthing when we touch the screen


        if(event.getAction()==MotionEvent.ACTION_DOWN){
            if(!hero.getPlaying() && newGameCreated && reset)
            {
                hero.setPlaying(true);
                hero.setUp(true);
            }
            if(hero.getPlaying())
            {
                if(!started)started = true;
                reset = false;
                //this is false... we need the setUp to  be true in this case
                hero.setUp(true);
            }
            return true;
        }
        if(event.getAction()==MotionEvent.ACTION_UP)
        {
            hero.setUp(false);
            return true;
        }


        return super.onTouchEvent(event);
    }


    //in our content view (Gamepanel we must constantly update our image )





    //update method
    public void update()
    {
        //here we will update the hero actions every sec
        if(hero.getPlaying()) {
            mp.start();



            if(botborder.isEmpty())
            {
                hero.setPlaying(false);
                return;
            }

            bg.update();
            hero.update();

            //add  the raccoon prisoners
            int prisonerselection=0;
            prisonerselection+=(rand.nextInt(500));

            long prisonertime = (System.nanoTime() - prisonerStartTime)/1000000;

            //check the delay of the raccons that appears on screen
            if(((prisonertime >(20000 - hero.getScore()/4)) && prisonerselection<10) ){
                //positioning of the raccoons
                prisoner.add(new PrisonerWorm((BitmapFactory.decodeResource(getResources(), R.drawable.saveraccoon)),
                        WIDTH + 1, (int) (rand.nextDouble() * (HEIGHT - (maxBorderHeight * 2)) + maxBorderHeight), 50, 50, 9));
                prisonerStartTime = System.nanoTime();
            }

            for(int i = 0; i<prisoner.size();i++)
            {
                prisoner.get(i).update();
                if(prisoner.get(i).getX()<-10)
                {
                    prisoner.remove(i);

                }

//if raccoons is collites with the player
                if (collision(prisoner.get(i), hero)) {

                    prisoner.remove(i);
                    fartsound.play(fartsoundid,0.99f, 0.99f, 1, 0, 0.99f);
                    wormrescued += 1;

                    if(wormrescued%3==0 ){

                        friendA.add(new RaccoonFriends((BitmapFactory.decodeResource(getResources(), R.drawable.frienda)),hero.getX()+rand.nextInt(70)+150,hero.getY()+rand.nextInt(150),50, 50, 3));
                        friendB.add(new RaccoonFriends((BitmapFactory.decodeResource(getResources(), R.drawable.friendb)),hero.getX()+rand.nextInt(70)+90, hero.getY()-rand.nextInt(90),50, 50, 3));
                        friendC.add(new RaccoonFriends((BitmapFactory.decodeResource(getResources(), R.drawable.friendc)),hero.getX()+rand.nextInt(70)+50, hero.getY()+rand.nextInt(50),50, 50, 3));
                        fartlunchsound.play( fartlunchsoundid,0.99f, 0.99f, 1, 0, 0.99f);

                    }

                    break;
                }


                //remove prisoner if it is way off the screen
                if(prisoner.get(i).getX()<-100)
                {
                    prisoner.remove(i);

                    break;
                }




            }//end for


            //calculate the threshold of height the border can have based on the score
            //max and min border heart are updated, and the border switched direction when either max or
            //min is met

            maxBorderHeight = 30+hero.getScore()/progressDenom;
            //cap max border height so that borders can only take up a total of 1/2 the screen
            if(maxBorderHeight > HEIGHT/4)maxBorderHeight = HEIGHT/4;
            minBorderHeight = 5+hero.getScore()/progressDenom;

            //check bottom border collision
            for(int i = 0; i<botborder.size(); i++)
            {

                if(collision(botborder.get(i), hero)) {
                    //playerpain.play( playerpainid,5,5,1,0,1);
                    hero.setPlaying(false);
                }



            }

            //check top border collision
            for(int i = 0; i <botborder.size(); i++)
            {
                if(collision(botborder.get(i),hero)) {
                    // playerpain.play( playerpainid,5,5,1,0,1);
                    hero.setPlaying(false);
                }





            }




            //udpate bottom border
            this.updateBottomBorder();







//add coin behavior
            //start the coin timer
            long bonustime = (System.nanoTime() - bonusStartTime)/1000000;

            //if we are good player then coins will be appeared more often on screen...
            if(((bonustime >(3000 - hero.getScore()/4)) ) ){

                //we create a coin
                mycoins.add(new Coin((BitmapFactory.decodeResource(getResources(), R.drawable.coin)),
                        WIDTH + 1, (int) (rand.nextDouble() * (HEIGHT -200)), 20, 20, 1));
                bonusStartTime = System.nanoTime();

            }//end if

            //we need the for loop in case you have more than one sprites to animate...
            for(int i = 0; i<mycoins.size();i++)
            {
                mycoins.get(i).update();

                //every time we collide with a coin we increase the herocoin var
                if (collision(mycoins.get(i), hero)) {

                    //sound
                    coinsound.play(coinsoundid,0.99f, 0.99f, 1, 0, 0.99f);



                    mycoins.remove(i);
                    herocoins+=1;
                    break;
                }

                //we remove the coins that are off the screen...
                if(mycoins.get(i).getX()<-100)
                {
                    mycoins.remove(i);

                    break;
                }

            }//end for




























            //add bot border behavior

            long borderElapsed = (System.nanoTime()-borderStartTime)/1000000;

            if(borderElapsed >100 ) {
                //dynamic bottom border
                botborder.add(new Borderbottom(BitmapFactory.decodeResource(getResources(), R.drawable.borderbottom),
                        botborder.get(botborder.size()-1).getX()+10,(int)((rand.nextDouble()
                        *maxBorderHeight)+(HEIGHT-maxBorderHeight))));
                //add top border?
                botborder.add(new Borderbottom(BitmapFactory.decodeResource(getResources(), R.drawable.bordertop), WIDTH + 5,((HEIGHT -600)+rand.nextInt(10))));


                borderStartTime = System.nanoTime();


            }//end if

            //loop through every border block and check collision and remove
            for(int i = 0; i<botborder.size();i++) {
                //update obstacle
                botborder.get(i).update();


                if (collision(botborder.get(i), hero)) {

                    hero.setPlaying(false);

                    break;

                }


                //if statement to remove border if is of the screen limits
                if( botborder.get(i).getX()<10)
                {
                    botborder.remove(i);
                }
            }//end








            //add top obstacle


            long obstacletopElapsed = (System.nanoTime()-obstacletopStartTime)/1000000;




            if(obstacletopElapsed >(15000 - hero.getScore()/4)){

                //bot obstacle appear.
                obstacletop.add(new Obstacle(BitmapFactory.decodeResource(getResources(), R.drawable.obstacletop),
                        WIDTH + 10, 1 -150-rand.nextInt(150) , 90, 300, hero.getScore(), 1));




                //reset timer
                obstacletopStartTime = System.nanoTime();
            }

            //loop through every alien and check collision and remove
            for(int i = 0; i<obstacletop.size();i++) {
                //update missile
                obstacletop.get(i).update();


                if (collision(obstacletop.get(i), hero)) {
                    playerpain.play( playerpainid,0.99f,0.99f,1,0,0.99f);
                    hero.setPlaying(false);

                    break;
                }
            }












            //add bot obstacle

            long obstacleElapsed = (System.nanoTime()-obstacleStartTime)/1000000;




            if(obstacleElapsed >(15000 - hero.getScore()/4)){

                //bot obstacle appear.
                obstacle.add(new Obstacle(BitmapFactory.decodeResource(getResources(), R.drawable.obstacle),
                        WIDTH + 10,  HEIGHT -290+rand.nextInt(150) , 90, 300, hero.getScore(), 1));

                //the height of the obstacle is going to be between 190px and 340px
                //remember the game HEIGHT is 480px



                //reset timer
                obstacleStartTime = System.nanoTime();
            }

            //loop through every alien and check collision and remove
            for(int i = 0; i<obstacle.size();i++) {
                //update obstacle
                obstacle.get(i).update();


                if (collision(obstacle.get(i), hero)) {

                    hero.setPlaying(false);

                    break;
                }
            }














            long bullettimer = (System.nanoTime() - bulletStartTime)/1000000;

            //check the delay among bullets fired from the hero
            //in simple words when a bullet will appear on the screen
            //and we want every sec our next bullet to be faster than the previous one
            //that depends of how good our hero is (high score we fire faster)
            if(bullettimer >(2500 - hero.getScore()/4)){
                //positioning of amo fire from hero

                bullet.add(new Bullet((BitmapFactory.decodeResource(getResources(), R.drawable.bullet)),hero.getX()+60, hero.getY()+24,15,7,7));
                bulletStartTime = System.nanoTime();

            }
//this is the common for loop to animate and update the frames of the bullet image
            //bullet.png has 7 frames
            for(int i = 0; i<bullet.size();i++)
            {
                bullet.get(i).update();
                //if statement to remove bullet if is of the screen limits
                if(bullet.get(i).getX()<-10)
                {
                    bullet.remove(i);
                }
            }



//step 3: Behavior/movement of the enemy
            //add enemy aliens


//set alien timer to millis
            long alienElapsed = (System.nanoTime()-alienStartTime)/1000000;



//the first enemy will appear after 10 sec and then more often by the time
            if(alienElapsed >(5000 - hero.getScore()/4)){


                alien.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.enemy),
                        WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT - 50)), 40, 60, hero.getScore(), 3));

                //reset timer
                alienStartTime = System.nanoTime();
            }

            //loop through every alien
            for(int i = 0; i<alien.size();i++) {
                //update alien
                alien.get(i).update();

                if (collision(alien.get(i), hero)) {

                    alien.remove(i);
//lose a life if collide
                    hearts--;
                    // hero.setPlaying(false);

                    break;
                }



                //remove alien if it is way off the screen
                if (alien.get(i).getX() < -100) {
                    alien.remove(i);
                    break;
                }

                //collision alien with bullet (fire)
                for (int j = 0; j < bullet.size(); j++) {


                    if(collision(alien.get(i),bullet.get(j)))
                    {
                        alienonedeadsound.play( alienonedeadsoundid,0.99f, 0.99f, 1, 0, 0.99f);
//explosion if we hit an alien
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),alien.get(i).getX(),
                                alien.get(i).getY(), 100, 100, 15);

                        bullet.remove(j);
                        alien.remove(i);




                        best+=30;


                        break;
                    }
                    bullet.get(j).update();




                }//end alien bullet collision



            }//end enemy





            //add secondary aliens
            //add aliens


            long secondalienElapsed = (System.nanoTime()-secondalienStartTime)/1000000;




            if(secondalienElapsed >(10000 - hero.getScore()/4)){

                //voice of alien
                alienseconddeadsound.play( alienseconddeadsoundid,0.99f, 0.99f, 1, 0, 0.99f);
                aliensecond.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.alienorange),
                        WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT - (maxBorderHeight * 2)) + maxBorderHeight), 40, 60, hero.getScore(), 3));




                //reset timer

                secondalienStartTime = System.nanoTime();
            }

            //loop through every alien and check collision and remove
            for(int i = 0; i<aliensecond.size();i++) {
                //update alien
                aliensecond.get(i).update();


                if (collision(aliensecond.get(i), hero)) {
                    playerpain.play( playerpainid,0.99f, 0.99f, 1, 0, 0.99f);
                    aliensecond.remove(i);
                    //lose a life
                    hearts--;

                    //player.setPlaying(false);

                    break;
                }


                //if alien hits some fellow raccoon
                for (int k = 0; k < friendA.size(); k++) {
                    if (collision(aliensecond.get(i), friendA.get(k))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.alienorangeexplode), aliensecond.get(i).getX(),
                                aliensecond.get(i).getY(), 100, 100, 15);
                        friendA.remove(k);
                        aliensecond.remove(i);

                        break;
                    }
                    friendA.get(k).update();
                    explosion.update();
                }
                for (int h = 0; h < friendB.size(); h++) {
                    if (collision(aliensecond.get(i), friendB.get(h))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.alienorangeexplode), aliensecond.get(i).getX(),
                                aliensecond.get(i).getY(), 100, 100, 15);
                        friendB.remove(h);
                        aliensecond.remove(i);

                        break;
                    }
                    friendB.get(h).update();
                    explosion.update();
                }
                for (int g = 0; g < friendC.size(); g++) {
                    if (collision(aliensecond.get(i), friendC.get(g))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.alienorangeexplode), aliensecond.get(i).getX(),
                                aliensecond.get(i).getY(), 100, 100, 15);
                        friendC.remove(g);
                        aliensecond.remove(i);

                        break;
                    }
                    friendC.get(g).update();
                    explosion.update();
                }





                //remove alien if it is way off the screen
                if (aliensecond.get(i).getX() < -100) {
                    aliensecond.remove(i);
                    break;
                }






                //collition missile with bullet (fire)
                for (int j = 0; j < bullet.size(); j++) {


                    if(collision(aliensecond.get(i),bullet.get(j)))
                    {
                        //every time the player hit a missile then it destroys and gain 20 points

                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.alienorangeexplode),aliensecond.get(i).getX(),
                                aliensecond.get(i).getY(), 100, 100, 15);

                        alienseconddeadsound.play(alienseconddeadsoundid,3,3,1,0,1);
                        aliensecond.remove(i);
                        bullet.remove(j);

                        best += 30;
                        break;
                    }
                    bullet.get(j).update();
                    explosion.update();

                }









            }//end 2nd enemy

            //Enemy Appear Bird





            //add chicken bird


            long ChickenElapsed = (System.nanoTime()-ChickenStartTime)/1000000;




            if(ChickenElapsed >(10000 - hero.getScore()/4)){


                //redbirdvoice.play( redbirdvoiceid,3,3,1,0,1);
                birdenemy.add(new Enemy(BitmapFactory.decodeResource(getResources(), R.drawable.enemybird),
                        WIDTH + 10, (int) (rand.nextDouble() * (HEIGHT - (maxBorderHeight * 2)) + maxBorderHeight), 40, 32, hero.getScore(), 2));




                //reset timer
                ChickenStartTime = System.nanoTime();
            }

            //loop through every chicken bird and check collision and remove
            for(int i = 0; i<birdenemy.size();i++) {
                //update missile
                birdenemy.get(i).update();


                if (collision(birdenemy.get(i), hero)) {
                    playerpain.play( playerpainid,0.99f,0.99f,1,0,0.99f);
                    birdenemy.remove(i);
                    //lose a life
                    hearts--;

                    //player.setPlaying(false);

                    break;
                }




                //if bird hits some fellow raccoon
                for (int k = 0; k < friendA.size(); k++) {
                    if (collision(birdenemy.get(i), friendA.get(k))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.birdexplode), birdenemy.get(i).getX(),
                                birdenemy.get(i).getY(), 100, 100, 15);
                        friendA.remove(k);
                        birdenemy.remove(i);

                        break;
                    }
                    friendA.get(k).update();
                    explosion.update();
                }
                for (int h = 0; h < friendB.size(); h++) {
                    if (collision(birdenemy.get(i), friendB.get(h))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.birdexplode), birdenemy.get(i).getX(),
                                birdenemy.get(i).getY(), 100, 100, 15);
                        friendB.remove(h);
                        birdenemy.remove(i);

                        break;
                    }
                    friendB.get(h).update();
                    explosion.update();
                }
                for (int g = 0; g < friendC.size(); g++) {
                    if (collision(birdenemy.get(i), friendC.get(g))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.birdexplode), birdenemy.get(i).getX(),
                                birdenemy.get(i).getY(), 100, 100, 15);
                        friendC.remove(g);
                        birdenemy.remove(i);

                        break;
                    }
                    friendC.get(g).update();
                    explosion.update();
                }












                //remove bird if it is way off the screen
                if(birdenemy.get(i).getX()<-100)
                {
                    birdenemy.remove(i);
                    break;
                }


                //collition missile with bullet (fire)
                for (int j = 0; j < bullet.size(); j++) {


                    if(collision(birdenemy.get(i),bullet.get(j)))
                    {
                        //every time the player hit a missile then it destroys and gain 20 points

                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.birdexplode),birdenemy.get(i).getX(),
                                birdenemy.get(i).getY(), 100, 100, 15);

                        birdhurtsound.play( birdhurtsoundid,4,4,1,0,1);
                        birdenemy.remove(i);
                        bullet.remove(j);

                        best += 30;
                        break;
                    }
                    bullet.get(j).update();
                    explosion.update();

                }








            }//end bird enemy


            //add   mine obstacles

            int mineselection=0;
            mineselection+=rand.nextInt(500);

            long minestime = (System.nanoTime() - mineStartTime)/1000000;

            //check the delay of the mines that appearing on screen
            if(((minestime >(6000 - hero.getScore()/4)) && mineselection<5) ){
                //positioning of the upgrade package
                landmine.add(new Mine((BitmapFactory.decodeResource(getResources(), R.drawable.mine)),
                        WIDTH + 1, (int) (rand.nextDouble() * (HEIGHT - (maxBorderHeight * 2)) + maxBorderHeight), 40, 40, hero.getScore(), 10));
                mineStartTime = System.nanoTime();
            }

            for(int i = 0; i<landmine.size();i++)
            {
                landmine.get(i).update();
                if(landmine.get(i).getX()<-10)
                {
                    landmine.remove(i);

                }


//if the mine collides with the player
                if (collision(landmine.get(i), hero)) {
                    explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.explosion),landmine.get(i).getX(),
                            landmine.get(i).getY(), 100, 100, 15);
                    landmine.remove(i);
                    playerpain.play(playerpainid, 5, 5, 1, 0, 1);
                    //player.setPlaying(false);
                    hearts--;

                    break;
                }

                //if landmine hits some fellow raccoon
                for (int k = 0; k < friendA.size(); k++) {
                    if (collision(landmine.get(i), friendA.get(k))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.explosion), landmine.get(i).getX(),
                                landmine.get(i).getY(), 100, 100, 15);
                        friendA.remove(k);
                        landmine.remove(i);

                        break;
                    }
                    friendA.get(k).update();
                    explosion.update();
                }
                for (int h = 0; h < friendB.size(); h++) {
                    if (collision(landmine.get(i), friendB.get(h))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.explosion), landmine.get(i).getX(),
                                landmine.get(i).getY(), 100, 100, 15);
                        friendB.remove(h);
                        landmine.remove(i);

                        break;
                    }
                    friendB.get(h).update();
                    explosion.update();
                }
                for (int g = 0; g < friendC.size(); g++) {
                    if (collision(landmine.get(i), friendC.get(g))) {
                        explosion = new Explosion(BitmapFactory.decodeResource(getResources(), R.drawable.explosion), landmine.get(i).getX(),
                                landmine.get(i).getY(), 100, 100, 15);
                        friendC.remove(g);
                        landmine.remove(i);

                        break;
                    }
                    friendC.get(g).update();
                    explosion.update();
                }








                //remove mine if it is way off the screen
                if(landmine.get(i).getX()<-100)
                {

                    landmine.remove(i);

                    break;
                }
            }//end for


















        }//end if playing

        else{
            hero.resetDYA();

            if(!reset){
                newGameCreated = false;
                startReset = System.nanoTime();
                reset = true;
                dissapear = true;
//initiate explosion when getplaying=false
                explosion = new Explosion(BitmapFactory.decodeResource(getResources(),R.drawable.explosion),hero.getX(),
                        hero.getY(), 100, 100, 15);
            }

            explosion.update();

            //reset game
            long resetElapsed = (System.nanoTime()-startReset)/1000000;

            if( resetElapsed>2500 && !newGameCreated) {

                newGame();
            }//end if


        }//end else




    }//end update


    //and now the hard part....We will develop the Gamepanel draw method in our next lesson

    //we need a draw method also...



    public boolean collision(GameObject a, GameObject b)
    {
        if(Rect.intersects(a.getRectangle(), b.getRectangle()))
        {
            return true;
        }
        return false;
    }










    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);




        final float scaleFactorX = getWidth()/(WIDTH*1.f);
        final float scaleFactorY = getHeight()/(HEIGHT*1.f);

        //so if smthing appears on our screen we must scale it

        if(canvas!=null) {
            final int savedState = canvas.save();
            canvas.scale(scaleFactorX, scaleFactorY);
            bg.draw(canvas);
            //and last we draw the hero
            if(!dissapear) {
                hero.draw(canvas);
            }

            for(Bullet fp: bullet)
            {
                fp.draw(canvas);
            }

            //step 4: we draw the enemy
            //draw alien slug
            for(Enemy aln: alien)
            {
                aln.draw(canvas);
            }
            //draw second alien enemy
            for(Enemy saln: aliensecond)
            {
                saln.draw(canvas);
            }

            //draw bird enemy
            for(Enemy en: birdenemy)
            {
                en.draw(canvas);
            }
            //draw bot obstacle
            for(Obstacle tobsb: obstacletop)
            {
                tobsb.draw(canvas);
            }
            //draw bot obstacle
            for(Obstacle obsb: obstacle)
            {
                obsb.draw(canvas);
            }

            //draw bot border
            for(Borderbottom brb: botborder)
            {
                brb.draw(canvas);
            }
            //draw prisoners worms
            for(PrisonerWorm pw: prisoner)
            {
                pw.draw(canvas);
            }
            //draw coins
            for(Coin mb:mycoins)
            {
                mb.draw(canvas);
            }
            //draw mine on screen
            for(Mine lm:landmine)
            {
                lm.draw(canvas);
            }

//draw raccoon friends





            for (RaccoonFriends rfr : friendA) {
                rfr.draw(canvas);
            }



            for (RaccoonFriends rfrb : friendB) {
                rfrb.draw(canvas);
            }



            for (RaccoonFriends rfrc : friendC) {
                rfrc.draw(canvas);
            }

//draw the explosion when we lose
            if(started)
            {
                explosion.draw(canvas);
            }


            drawText(canvas);
            canvas.restoreToCount(savedState);
        }//end canvas











    }//end draw
    public void updateBottomBorder()
    {
        //every 40 points, insert randomly placed bottom blocks that break pattern
        if(hero.getScore()%40 == 0)
        {
            botborder.add(new Borderbottom(BitmapFactory.decodeResource(getResources(), R.drawable.borderbottom),
                    botborder.get(botborder.size()-1).getX()+10,(int)((rand.nextDouble()
                    *maxBorderHeight)+(HEIGHT-maxBorderHeight))));
        }
        //update bottom border
        for(int i = 0; i<botborder.size(); i++)
        {
            botborder.get(i).update();
            //if border is moving off screen, remove it and add a corresponding new one
            if(botborder.get(i).getX()<-10) {
                botborder.remove(i);
                //determine if border will be moving up or down
                if (botborder.get(botborder.size() - 1).getY() <= HEIGHT-maxBorderHeight) {
                    botDown = true;
                }
                if (botborder.get(botborder.size() - 1).getY() >= HEIGHT - minBorderHeight) {
                    botDown = false;
                }

                if (botDown) {
                    botborder.add(new Borderbottom(BitmapFactory.decodeResource(getResources(), R.drawable.borderbottom
                    ), botborder.get(botborder.size() - 1).getX() + 10, botborder.get(botborder.size() - 1
                    ).getY() + 1));
                } else {
                    botborder.add(new Borderbottom(BitmapFactory.decodeResource(getResources(), R.drawable.borderbottom
                    ), botborder.get(botborder.size() - 1).getX() + 10, botborder.get(botborder.size() - 1
                    ).getY() - 1));
                }
            }
        }
    }

    public void newGame()
    {
        dissapear = false;

        alien.clear();
        aliensecond.clear();
        obstacle.clear();
        //we forgot to clear the borders and the bullet
        botborder.clear();
        bullet.clear();
        birdenemy.clear();
        landmine.clear();
        obstacletop.clear();
        friendA.clear();
        friendB.clear();
        friendC.clear();
        prisoner.clear();
        hero.resetDYA();
        hero.resetScore();
        mycoins.clear();

        hero.setY(HEIGHT/2);

        //we also need the newgamecreated var to be true so we can play
        //initial bottom border
        for(int i = 0; i*20<WIDTH+40; i++)
        {
            //first border ever created
            if(i==0)
            {
                botborder.add(new Borderbottom(BitmapFactory.decodeResource(getResources(), R.drawable.borderbottom)
                        ,i*20,HEIGHT - minBorderHeight));
            }
            //adding borders until the initial screen is filed
            else
            {
                botborder.add(new Borderbottom(BitmapFactory.decodeResource(getResources(), R.drawable.borderbottom),
                        i * 20, botborder.get(i - 1).getY() - 1));
            }
        }
        newGameCreated=true;



    }//end method

    public void drawText(Canvas canvas)
    {

        //FrontGround
        bottomsurfaceimage= BitmapFactory.decodeResource(getResources(),R. drawable.botsurface);
        canvas.drawBitmap(bottomsurfaceimage, 0,HEIGHT - 184, null);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        paint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        canvas.drawText("Distance: " + (hero.getScore()*2), 10, HEIGHT - 10, paint);
        canvas.drawText("Score: " + best, WIDTH - 215, HEIGHT - 10, paint);

        coinbonus = BitmapFactory.decodeResource(getResources(), R.drawable.coin);
        canvas.drawBitmap(coinbonus, WIDTH - 130, 0, null);
        canvas.drawText(" " + herocoins, WIDTH - 90, 25, paint);




        if (hearts==3) {
            hearta = BitmapFactory.decodeResource(getResources(), R.drawable.lifea);
            canvas.drawBitmap( hearta, WIDTH/2 -120, 0, null);
            heartb = BitmapFactory.decodeResource(getResources(), R.drawable.lifeb);
            canvas.drawBitmap( heartb, WIDTH/2 -80, 0, null);
            heartc = BitmapFactory.decodeResource(getResources(), R.drawable.lifec);
            canvas.drawBitmap( heartc, WIDTH/2 -40, 0, null);

        }
        if (hearts==2) {
            hearta = BitmapFactory.decodeResource(getResources(), R.drawable.lifea);
            canvas.drawBitmap( hearta, WIDTH/2 -120, 0, null);
            heartb = BitmapFactory.decodeResource(getResources(), R.drawable.lifeb);
            canvas.drawBitmap( heartb, WIDTH/2 -80, 0, null);

        }

        if (hearts==1) {
            hearta = BitmapFactory.decodeResource(getResources(), R.drawable.lifea);
            canvas.drawBitmap( hearta, WIDTH/2 -120, 0, null);

        }
        if (hearts==0) {
            hero.setPlaying(false);
            hearts=3;

        }
        //intro panel

        if(!hero.getPlaying()&&newGameCreated&&reset&&herocoins==0)
        {
            Paint paint1 = new Paint();
            paint1.setTextSize(25);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

            mypanel = BitmapFactory.decodeResource(getResources(), R.drawable.scorepanel);
            canvas.drawBitmap(mypanel, WIDTH/2-210,HEIGHT/2-120, null);

            canvas.drawText("PRESS TO START", WIDTH/2-100, HEIGHT/2-70, paint1);

            canvas.drawText("PRESS AND HOLD TO GO UP", WIDTH/2-130, HEIGHT/2 - 20, paint1);

            canvas.drawText("RELEASE TO GO DOWN", WIDTH/2-130, HEIGHT/2 + 20, paint1);
            canvas.drawText("Save 3 Raccoons!", WIDTH/2-130, HEIGHT/2 + 50, paint1);







        }//end panel
        raccoonbm = BitmapFactory.decodeResource(getResources(), R.drawable.raccoonscore);
        canvas.drawBitmap(raccoonbm, 5,0, null);
        canvas.drawText(" " + wormrescued , 70, 25, paint);
        if(!hero.getPlaying()&&newGameCreated&&reset&&herocoins>0)
        {
            Paint paint1 = new Paint();
            mypanelscore = BitmapFactory.decodeResource(getResources(), R.drawable.scorepanelb);
            canvas.drawBitmap(mypanelscore, WIDTH/2-275,HEIGHT/2-150, null);
            paint1.setTextSize(25);
            paint1.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
            canvas.drawText("PRESS TO START", WIDTH/2-100, HEIGHT/2-80, paint1);
            paint1.setTextSize(20);
            canvas.drawText("Menu options", WIDTH/2-50, HEIGHT/2 - 50, paint1);
            canvas.drawText("Coins: "+ herocoins, WIDTH/2-50, HEIGHT/2 - 20, paint1);
            canvas.drawText("Score: "+ best, WIDTH/2-50, HEIGHT/2 +10, paint1);
            canvas.drawText("Raccoons: "+ wormrescued , WIDTH/2-50, HEIGHT/2 +40, paint1);

        }






    }//end method




}//end of class

