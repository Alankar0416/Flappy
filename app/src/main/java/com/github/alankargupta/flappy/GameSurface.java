package com.github.alankargupta.flappy;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

/**
 * Created by alankargupta on 23/05/16.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private final int screenWidth;
    private final int screenHeight;

    public GameSurface(Context context, int screenWidth, int screenHight) {
        super(context);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHight;
        getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Canvas canvas = getHolder().lockCanvas();
        draw(canvas);
        getHolder().unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    @Override
    public void draw(Canvas canvas) {
        Bitmap background = BitmapFactory.decodeResource(getResources(),R.drawable.background);
        Rect src = new Rect(0,0,background.getWidth(),background.getHeight());
        Rect dest = new Rect(0,0,screenWidth,screenHeight);
        canvas.drawBitmap(background,src,dest,null);
    }
}
