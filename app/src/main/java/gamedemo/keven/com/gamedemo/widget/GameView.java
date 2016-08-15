package gamedemo.keven.com.gamedemo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;

import gamedemo.keven.com.gamedemo.inter.Drawable;

/**
 * Created by keven on 16/8/11.
 */

public class GameView extends View {

    //图片的图层分布
    private HashMap<Integer,ArrayList<Drawable>> picLayer = new HashMap<Integer, ArrayList<Drawable>>();
    //修改后的图片的涂层分布,
    //添加的元素
    private HashMap<Integer,ArrayList<Drawable>> addPicLayer = new HashMap<Integer, ArrayList<Drawable>>();
    //删除的元素
    private HashMap<Integer,ArrayList<Drawable>> removePicLayer = new HashMap<Integer, ArrayList<Drawable>>();

    public GameView(Context context) {
        this(context,null);
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        updataPicLayer(CHANGE_MODE_UPDATE,0,null);
//        for(int id : picLayerId){
//            for (Drawable drawable : picLayer.get(id)){
//                canvas.drawBitmap(drawable.getCurrentPic(),drawable.getPicHeight(),paint);
//            }
//        }
    }
}
