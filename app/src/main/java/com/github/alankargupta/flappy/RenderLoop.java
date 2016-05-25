package com.github.alankargupta.flappy;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
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
    private final int FPS = 30;
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
                if(!gameSurface.getHolder().getSurface().isValid())
                    continue;
                long startTime = System.currentTimeMillis();
                Canvas canvas = gameSurface.getHolder().lockCanvas();
                if(canvas==null)
                    return;
                gameSurface.update();
                gameSurface.draw(canvas);
                gameSurface.getHolder().unlockCanvasAndPost(canvas);
                canvas.drawColor(0, PorterDuff.Mode.CLEAR);
                long endTime = System.currentTimeMillis();
                Log.d(TAG,"FPS: "+1000/(endTime-startTime));
                Log.d(TAG,"DrawTime: "+(endTime-startTime));
                try {
                    if(delay-(endTime-startTime)>3)
                    Thread.sleep(delay-(endTime-startTime));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
