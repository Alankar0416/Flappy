package com.github.alankargupta.flappy;

import android.graphics.Canvas;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        GameSurface gameSurface = new GameSurface(this,screenSize.x,screenSize.y);
        setContentView(gameSurface);
    }
}
