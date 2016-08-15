package gamedemo.keven.com.gamedemo.thread;

import gamedemo.keven.com.gamedemo.Impl.Fish;

/**
 * Created by keven on 16/8/12.
 */

public class FishRunThread extends Thread {
    private Fish fish;
    private boolean isRun;
    private int screenWidth;

    public FishRunThread(Fish fish,int screenWidth){
        this.fish = fish;
        this.screenWidth = screenWidth;
    }

    public void stopFishRun(){
        isRun = false;
    }

    @Override
    public void run() {
        super.run();
        isRun = true;
        int fishx = 0;
        while (isRun){
            //累加x,让鱼一直向右移动
            fish.getPicMatrix().setTranslate(fishx,0);
            fishx++;
            if(fishx > screenWidth){
                //减去宽度是为了让鱼的起始位置位于屏幕左边没有出现的位置
                fishx = 0 - fish.getCurrentPic().getWidth();
            }
            try {
                Thread.sleep(50);
            }catch (Exception e){

            }
        }
    }
}
