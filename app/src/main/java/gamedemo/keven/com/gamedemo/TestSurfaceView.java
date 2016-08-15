package gamedemo.keven.com.gamedemo;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by keven on 16/8/12.
 */

public class TestSurfaceView extends Activity implements View.OnClickListener{
    Button btnSimpleDraw;
    Button btnTimerDraw;
    SurfaceView sfv;
    SurfaceHolder sfh;
    private Timer mTimer;
    private MyTimerTask mMyTimerTask;
    Paint mPaint;
    int Y_axis[],//保存正弦波的y轴上的点
    centerY,//中心线
    oldX,oldY,//上一个XY点
    currentX;//当前绘制到的X轴上的点


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_surface_activity);
        btnSimpleDraw = (Button) findViewById(R.id.button01);
        btnTimerDraw = (Button) findViewById(R.id.button02);
        btnSimpleDraw.setOnClickListener(this);
        btnTimerDraw.setOnClickListener(this);

        sfv = (SurfaceView) findViewById(R.id.surfaceView01);
        sfh = sfv.getHolder();

        mTimer = new Timer();
        mMyTimerTask = new MyTimerTask();

        centerY = (getWindowManager().getDefaultDisplay().getHeight() - sfv.getTop())/2 ;

        Y_axis = new int[getWindowManager().getDefaultDisplay().getWidth()];
        for (int i = 1; i < Y_axis.length;i++ ){
            Y_axis[i - 1] = centerY - (int)(100 * Math.sin(i*2*Math.PI/180));
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.button01){
            simpleDraw(Y_axis.length - 1);
        }else if(id == R.id.button02){
            oldY = centerY;
            mTimer.schedule(mMyTimerTask,0,5);
        }

    }

    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
           simpleDraw(currentX);
            currentX ++;
            if(currentX == Y_axis.length - 1){
                ClearDraw();
                currentX = 0;
                oldY = centerY;
            }
        }
    }

    void simpleDraw(int length){
        if(length == 0){
            oldX = 0;
        }
        Canvas canvas = sfh.lockCanvas(new Rect(oldX,0,oldX + length,
                getWindowManager().getDefaultDisplay().getHeight()));

        Log.i("Canvas:",String.valueOf(oldX) + "," + String.valueOf(oldX + length));

        if(null == mPaint) {
            mPaint = new Paint();

            mPaint.setColor(Color.WHITE);
            mPaint.setStrokeWidth(2);
        }

        int y;
        for (int i = oldX + 1; i < length; i++){
            y = Y_axis[i - 1];
            canvas.drawLine(oldX,oldY,i,y,mPaint);
            oldX = i;
            oldY = y;
        }
        sfh.unlockCanvasAndPost(canvas);
    }

    void ClearDraw(){
        Canvas canvas = sfh.lockCanvas(null);
        canvas.drawColor(Color.BLACK);
        sfh.unlockCanvasAndPost(canvas);
    }
}
