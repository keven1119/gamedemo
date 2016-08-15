package gamedemo.keven.com.gamedemo.widget.surface;

import android.app.Activity;

import java.util.ArrayList;

import gamedemo.keven.com.gamedemo.Impl.Fish;

/**
 * Created by keven on 16/8/12.
 */
public class GamingInfo {

    private int screenWidth;
    private int screenHeight;
    private static GamingInfo gamingInfo = new GamingInfo();
    private boolean isGaming ;
    private boolean isPause;
    private MainSurface surface;
    private Activity activity;
    private ArrayList<Fish> fishs = new ArrayList<Fish>();//存放所有的鱼
//    private ShoalManager shoalManager ;
//    private SoundManager soundManager;
    private float cannonLayoutX;
    private float cannonLayoutY;
    private int score = 100;

    public int getScore(){
        return score;
    }

    public void setScore(int score){
        this.score = score;
    }

    public static void clearGameInfo(){
        gamingInfo = null;
    }

    private GamingInfo(){

    }

    public static GamingInfo getGamingInfo() {
        if (null == gamingInfo){
            gamingInfo = new GamingInfo();
        }
        return gamingInfo;
    }

    public ArrayList<Fish> getFish(){
        return fishs;
    }

    public void setFish(ArrayList<Fish> fish){
        this.fishs = fish;
    }

    public MainSurface getSurface(){
        return surface;
    }

    public void setSurface(MainSurface surface){
        this.surface = surface;
    }

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

//    public SoundManager getSoundManager() {
//        return soundManager;
//    }
//
//    public void setSoundManager(SoundManager soundManager) {
//        this.soundManager = soundManager;
//    }
//
//    public ShoalManager getShoalManager() {
//        return shoalManager;
//    }
//
//    public void setShoalManager(ShoalManager shoalManager) {
//        this.shoalManager = shoalManager;
//    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public float getCannonLayoutX() {
        return cannonLayoutX;
    }

    public void setCannonLayoutX(float cannonLayoutX) {
        this.cannonLayoutX = cannonLayoutX;
    }

    public float getCannonLayoutY() {
        return cannonLayoutY;
    }

    public void setCannonLayoutY(float cannonLayoutY) {
        this.cannonLayoutY = cannonLayoutY;
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean pause) {
        isPause = pause;
    }

    public boolean isGaming(){
        return isGaming;
    }

    public void setGaming(boolean isGaming){
        this.isGaming = isGaming;
    }
}
