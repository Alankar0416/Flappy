package com.github.alankargupta.flappy;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by alankargupta on 23/05/16.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private static final String TAG = GameSurface.class.getSimpleName();
    private Context context;
    public static int SCREEN_WIDTH =0;
    public static int SCREEN_HEIGHT=0;
    public static int FIXED_WIDTH =300;
    public static int FIXED_HEIGHT=300;
    private RenderLoop renderLoop;
    private Background background;
    private Bird bird;

    public GameSurface(Context context, int screenWidth, int screenHight) {
        super(context);
        this.context = context;
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHight;
        getHolder().addCallback(this);
        getHolder().setFixedSize(SCREEN_WIDTH,SCREEN_HEIGHT);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        background = new Background(context);
        bird = new Bird(context);
        renderLoop = new RenderLoop(this);
        renderLoop.setRunning(true);
        new Thread(renderLoop).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        /*renderLoop.setRunning(false);
        renderLoop = null;*/
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        renderLoop.setRunning(false);
        renderLoop = null;
    }

    public void update(){
        background.update();
        bird.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        background.run(canvas);
        bird.draw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.d(TAG,"Motion event "+event.getAction());
        background.onTouchEvent(event);
        bird.onTouchEvent(event);
        return true;
    }
}
