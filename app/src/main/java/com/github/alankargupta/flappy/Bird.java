package com.github.alankargupta.flappy;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;

/**
 * Created by alankargupta on 25/05/16.
 */
public class Bird extends GameObject{

    private static final String TAG = Bird.class.getSimpleName();
    private final Bitmap one;
    private final Bitmap two;
    private final Bitmap three;
    private final Bitmap four;
    private final Rect src;
    private Rect dest;
    private long cycleTime = 1000;
    private long cycleStartTime = 0;
    private int dxa,dya,dy,ddya;
    private int g=20,time=1;
    private boolean isTouching;

    public Bird(Context context){
        one = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_1);
        two = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_2);
        three = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_3);
        four = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_4);
        x=GameSurface.SCREEN_WIDTH/11;
        y=GameSurface.SCREEN_HEIGHT/2-GameSurface.SCREEN_HEIGHT/5;
        src = new Rect(0,0,one.getWidth(),one.getHeight());
    }

    public void update(){
        Log.d(TAG,"dy "+dya+" y = "+y);
        x+=dxa;
        //dya=dya+ddya;
        int netAcceleration = isTouching?dya:g;
        dy = dy + netAcceleration*time;
        y= dy*time + 1*netAcceleration*time*time;
    }

    public void draw(Canvas canvas){
        width=x+GameSurface.SCREEN_WIDTH/8;
        height=y+GameSurface.SCREEN_HEIGHT*2/5;
        dest = new Rect(x,y,width,height);
        long drawtime = System.currentTimeMillis();
        if(cycleStartTime==0 || drawtime-cycleStartTime>cycleTime)
            cycleStartTime=System.currentTimeMillis();

        if(drawtime-cycleStartTime<cycleTime/4){
            //first cycle
            canvas.drawBitmap(one,src,dest,null);
        }else if(drawtime-cycleStartTime<cycleTime/2){
            //second cycle
            canvas.drawBitmap(two,src,dest,null);
        }else if(drawtime-cycleStartTime<cycleTime*3/4){
            //third cycle
            canvas.drawBitmap(three,src,dest,null);
        }else if(drawtime-cycleStartTime<cycleTime){
            //fourth cycle
            canvas.drawBitmap(four,src,dest,null);
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"Motion event "+event.getAction());
        if(event.getAction()==MotionEvent.ACTION_DOWN){
            isTouching = true;
            dya -= 10;
        }else if(event.getAction()==MotionEvent.ACTION_UP){
            isTouching = false;
            dya += 10;
        }
        return true;
    }
}
