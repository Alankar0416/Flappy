package com.github.alankargupta.flappy;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by alankargupta on 25/05/16.
 */
public class Bird extends GameObject{

    private final Bitmap one;
    private final Bitmap two;
    private final Bitmap three;
    private final Bitmap four;
    private final Rect src;
    private final Rect dest;
    private long cycleTime = 1000;
    private long cycleStartTime = 0;

    public Bird(Context context){
        one = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_1);
        two = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_2);
        three = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_3);
        four = BitmapFactory.decodeResource(context.getResources(),R.drawable.bird_a_4);
        x=GameSurface.SCREEN_WIDTH/11;
        y=GameSurface.SCREEN_HEIGHT/2-GameSurface.SCREEN_HEIGHT/5;
        width=x+GameSurface.SCREEN_WIDTH/8;
        height=y+GameSurface.SCREEN_HEIGHT*2/5;
        src = new Rect(0,0,one.getWidth(),one.getHeight());
        dest = new Rect(x,y,width,height);
    }

    public void update(){

    }

    public void draw(Canvas canvas){
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
}
