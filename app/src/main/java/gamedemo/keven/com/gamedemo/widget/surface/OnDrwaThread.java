package gamedemo.keven.com.gamedemo.widget.surface;

import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

import gamedemo.keven.com.gamedemo.inter.Drawable;

/**
 * Created by keven on 16/8/12.
 */

public class OnDrwaThread extends Thread {

    private MainSurface surface;
    private SurfaceHolder sh;
    private int drawSpeed;

    public OnDrwaThread(MainSurface surface){
        super();
        this.surface = surface;
        sh = surface.getHolder();
        drawSpeed = (1000/Constant.NO_DRAW_SLEEP);
    }

    @Override
    public void run() {
        super.run();
        Canvas canvas = null;
        while (GamingInfo.getGamingInfo().isGaming()){
            try {
                canvas = sh.lockCanvas(null);
                if(null != canvas){
                    surface.draw(canvas);
                }
            }catch (Exception e){
                Log.e(this.getName(),e.toString());
                e.printStackTrace();
            }finally {
                try {
                    if(null != sh){
                        sh.unlockCanvasAndPost(canvas);
                    }
                }catch (Exception e){
                    Log.e(this.getName(),e.toString());
                    e.printStackTrace();
                }
            }

            try {
                Thread.sleep(drawSpeed);
            } catch (InterruptedException e) {

            }

        }
    }

    public void stopDraw() {
    }
}
