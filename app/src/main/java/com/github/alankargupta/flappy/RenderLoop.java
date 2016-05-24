package com.github.alankargupta.flappy;
import android.graphics.Canvas;
import android.provider.Settings;
import android.util.Log;

/**
 * Created by alankargupta on 24/05/16.
 */

//To run GameSurface periodically at a regular interval
public class RenderLoop implements Runnable {

    private String TAG = RenderLoop.class.getSimpleName();
    private final GameSurface gameSurface;
    private Canvas canvas;
    private boolean isRunning;
    private final int FPS = 50;
    private final int delay = 1000/FPS;


    public RenderLoop(GameSurface gameSurface){
        this.gameSurface = gameSurface;
        this.canvas = canvas;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void setRunning(boolean running) {
        isRunning = running;
    }

    @Override
    public void run() {
        while(true){
            while (isRunning){
                long startTime = System.currentTimeMillis();
                Log.d(TAG,"Rendering "+ System.currentTimeMillis());
                gameSurface.update();
                Canvas canvas = gameSurface.getHolder().lockCanvas();
                gameSurface.draw(canvas);
                gameSurface.getHolder().unlockCanvasAndPost(canvas);
                long endTime = System.currentTimeMillis();
                /*try {
                    if(endTime-startTime<delay)
                    Thread.sleep(delay-(endTime-startTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}
