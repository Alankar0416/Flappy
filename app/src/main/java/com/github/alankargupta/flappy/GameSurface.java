package com.github.alankargupta.flappy;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by alankargupta on 23/05/16.
 */

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private Context context;
    public static int SCREEN_WIDTH =0;
    public static int SCREEN_HEIGHT=0;
    private RenderLoop renderLoop;
    private Background background;

    public GameSurface(Context context, int screenWidth, int screenHight) {
        super(context);
        this.context = context;
        SCREEN_WIDTH = screenWidth;
        SCREEN_HEIGHT = screenHight;
        getHolder().addCallback(this);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        background = new Background(context);
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
    }

    @Override
    public void draw(Canvas canvas) {
        if(canvas==null)
            return;
        canvas.drawColor(0, PorterDuff.Mode.CLEAR);
        background.run(canvas);
    }
}
