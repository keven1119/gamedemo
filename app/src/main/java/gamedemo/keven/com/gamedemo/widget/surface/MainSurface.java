package gamedemo.keven.com.gamedemo.widget.surface;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.HashMap;

import gamedemo.keven.com.gamedemo.inter.Drawable;

/**
 * Created by keven on 16/8/12.
 */

public class MainSurface extends SurfaceView implements SurfaceHolder.Callback{

    //更新图层
    private final static int CHANGE_MODE_UPDATE = 0;
    //添加元素到图层
    private final static int CHANGE_MODE_ADD = 1;
    //从图层删除元素
    private final static int CHANGE_MODE_REMOVE = 2;

    //图片的图层分布
    private HashMap<Integer,ArrayList<Drawable>> picLayer = new HashMap<Integer, ArrayList<Drawable>>();
    //修改后的图片的涂层分布,
    //添加的元素
    private HashMap<Integer,ArrayList<Drawable>> addPicLayer = new HashMap<Integer, ArrayList<Drawable>>();
    //删除的元素
    private HashMap<Integer,ArrayList<Drawable>> removePicLayer = new HashMap<Integer, ArrayList<Drawable>>();

    //是否修改过图层
    private boolean changeLayer = false;
    //定义一个图层ID ,加速获取图层绘制(省去了从map中获取各个图层排序问题)
    private int picLayerId[];
    private Paint paint;

    //屏幕绘制线程,用语控制绘制帧数,周期性调用onDraw方法
    private OnDrwaThread odt;


    public MainSurface(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.getHolder().addCallback(this);
        paint = new Paint();
        paint.setColor(Color.WHITE);
        odt = new OnDrwaThread(this);
    }

    public MainSurface(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MainSurface(Context context) {
        this(context,null,0);
    }

    @Override
    //绘制方法由线程控制,周期性调用
    public void onDraw(Canvas canvas){
        //更新图层内容
        updataPicLayer(CHANGE_MODE_UPDATE,0,null);
        //遍历所有图层,按图层的先后顺序绘制
        for (int id:picLayerId){
            for (Drawable drawable : picLayer.get(id)){
                canvas.drawBitmap(drawable.getCurrentPic(),drawable.getPicMatrix(),paint);
            }
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        odt.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        paint = null;
        picLayer = null;
        picLayerId = null;
    }

    /**
     * 更新图层,这里分为三种操作方法,分别是更新临时图层中的内容到绘制图层中
     *          删除绘制图层中的元素,
     *          添加绘制图层中的元素
     *
     * 加线程锁,保证多线程下操作图层的安全性
     * @param mode
     * @param layerId
     * @param draw
     */
    private synchronized void updataPicLayer(int mode,int layerId,Drawable draw){
        switch (mode){
            //将临时图层中的内容更新至绘制图层中
            case CHANGE_MODE_UPDATE:
                if(changeLayer){
                    //向图层添加新的元素
                    for(Integer id:addPicLayer.keySet()){
                        for (Drawable d:addPicLayer.get(id)){
                            //如果添加的元素所出的图层不存在,测创建这个图层,并更新图层ID数组
                            if(this.picLayer.get(id) == null){
                                this.picLayer.put(id,new ArrayList<Drawable>());
                                updataLayerIds(id);
                            }
                            this.picLayer.get(id).add(d);
                        }
                    }
                    addPicLayer.clear();
                    //删除图层中的元素
                    for (int id : removePicLayer.keySet()){
                        for (Drawable d:removePicLayer.get(id)){
                            this.picLayer.get(id).remove(d);
                        }
                    }
                    removePicLayer.clear();
                    changeLayer = false;
                }
                break;
            /**
             * 无论是向绘图图层中添加还是删除元素,都不能直接操作绘制图层,都存放在对应的临时图层中,等待绘制
             * 方法将绘制周期变化的内容更新到绘制图层中,
             */
            // 添加一个元素
            case CHANGE_MODE_ADD:
                ArrayList<Drawable> al = addPicLayer.get(layerId);
                if(null == al){
                    al = new ArrayList<Drawable>();
                    addPicLayer.put(layerId,al);
                }
                al.add(draw);
                changeLayer = true;
                break;

            //删除一个元素
            case CHANGE_MODE_REMOVE:
                ArrayList<Drawable> removeList = removePicLayer.get(layerId);
                if(null == removeList){
                    removeList = new ArrayList<Drawable>();
                    removePicLayer.put(layerId,removeList);
                }
                removeList.add(draw);
                changeLayer = true;
                break;
        }
    }

    /**
     * 将一个可绘制的图添加到图层中
     * @param layer 图层号虽然是int,但是实际上 只支持到byte,原因是图层没有必要那么多
     * @param pic   可绘制的图
     */
    public void putDrawablePic(int layer,Drawable pic){
        updataPicLayer(CHANGE_MODE_ADD,layer,pic);
    }

    /**
     * 将一个可绘制的图从图层中移走
     * @param layer
     * @param pic
     */
    public void removeDrawablePic(int layer,Drawable pic){
        updataPicLayer(CHANGE_MODE_REMOVE,layer,pic);
    }

    /***
     * 更新图层id
     * @param newLayerId
     */
    private void updataLayerIds(int newLayerId){
        if(null == picLayerId){
            picLayerId = new int[1];
            picLayerId[0] = newLayerId;
        }else {
            //创建一个新的图层数组,长度比原来的大1位
            int picLayerIdFlag[] = new int[picLayerId.length+1];

            for (int i = 0;i < picLayerId.length ; i++){
                //排序操作,如果新的图层ID小于当前图层Id,将新的图层ID插入其中
                if(picLayerId[i] > newLayerId){
                    for(int f = picLayerIdFlag.length - 1;f > i;f--){
                        picLayerIdFlag[f]  = picLayerId[f-1];
                    }
                    picLayerIdFlag[i] = newLayerId;
                    break;
                }else {
                    picLayerIdFlag[i] = picLayerId[i];
                }
                //如果到了最后,都没有比新图层ID打的,就将新的图层ID存入最后
                if(i == picLayerId.length - 1){
                    picLayerIdFlag[picLayerIdFlag.length - 1] = newLayerId;
                }
            }
            //将新的图层ID数组覆盖原有的
            this.picLayerId = picLayerIdFlag;
        }
    }

    /**
     * 这个方法停止绘制线程,一旦停止,Surface的onDraw方法将不会在调用
     */
    public void stopDraw(){
        odt.stopDraw();
    }
}
