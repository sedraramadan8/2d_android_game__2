package com.sedraram8.android2dgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

public class Game extends SurfaceView implements SurfaceHolder.Callback {
    private final Player player;
    private GameLoop gameLoop;

    public Game(Context context) {
        super(context);
        //get surface holder and add callback
        SurfaceHolder surfaceHolder= getHolder();
        surfaceHolder.addCallback(this);
         gameLoop = new GameLoop(this, surfaceHolder);

         player = new Player(getContext(),500,500,30);
        setFocusable(true);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                player.setPosition((double)event.getX(), (double)event.getY());
                return true;
            case  MotionEvent.ACTION_MOVE:
                player.setPosition((double)event.getX(), (double)event.getY());
                return true;
        }
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder surfaceHolder) {
        gameLoop.startLoop();

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder surfaceHolder, int i, int i1, int i2) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder surfaceHolder) {

    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        drawUps(canvas);
        drawFps(canvas);
        player.draw(canvas);
    }

    public  void drawUps(Canvas canvas){
        String averageUps ;
        averageUps= Double.toString(gameLoop.getAverageUPS());
        Paint paint =new Paint();
        int color = ContextCompat.getColor(getContext() , R.color.black );
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("UPS : "+averageUps,100, 10 , paint);
    }
    public  void drawFps(Canvas canvas){
        String averageFps ;
        averageFps= Double.toString(gameLoop.getAverageFPS());
        Paint paint =new Paint();
        int color = ContextCompat.getColor(getContext() , R.color.black );
        paint.setColor(color);
        paint.setTextSize(50);
        canvas.drawText("FPS : "+averageFps,100, 200 , paint);
    }

    public void update() {
        player.update();
    }
}
