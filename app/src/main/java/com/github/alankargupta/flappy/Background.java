package com.github.alankargupta.flappy;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.MotionEvent;

import static com.github.alankargupta.flappy.GameSurface.FIXED_HEIGHT;
import static com.github.alankargupta.flappy.GameSurface.FIXED_WIDTH;
import static com.github.alankargupta.flappy.GameSurface.SCREEN_HEIGHT;
import static com.github.alankargupta.flappy.GameSurface.SCREEN_WIDTH;

/**
 * Created by alankargupta on 24/05/16.
 */

public class Background extends GameObject {

    private final Bitmap background;
    private final Rect src;

    public Background(Context context){
        x=0;
        y=0;
        width=SCREEN_WIDTH;
        height=SCREEN_HEIGHT;
        background = BitmapFactory.decodeResource(context.getResources(),R.drawable.desert);
        src = new Rect(0,0,background.getWidth(),background.getHeight());
    }

    public void update(){
        x-=5;
    }

    public void run(Canvas canvas){
        Rect dest = new Rect(x,y, width+x,height+y);
        canvas.drawBitmap(background,src,dest,null);
        Rect dest2 = new Rect(x+width,y,x+width+width,height);
        canvas.drawBitmap(background,src,dest2,null);
        x=x<=-width?x+width:x;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

}
