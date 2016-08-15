package gamedemo.keven.com.gamedemo;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

import gamedemo.keven.com.gamedemo.widget.surface.MainSurface;

/**
 * Created by keven on 16/8/11.
 */

public class OpenGLActivity extends Activity {

    GLSurfaceView mGLSurfaceView;

    MainSurface mMainSurface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        mGLSurfaceView = new GLSurfaceView(this);
//        mMainSurface = new MainSurface(this);
//        final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
//        final ConfigurationInfo deviceConfigurationInfo = activityManager.getDeviceConfigurationInfo();
//        final boolean supportsEs2 = deviceConfigurationInfo.reqGlEsVersion >= 0x20000;
//
//        if(supportsEs2){
//            mGLSurfaceView.setEGLContextClientVersion(2);
////            mGLSurfaceView.setRenderer(new Less);
//        }else {
//            return;
//        }
//        setContentView(mGLSurfaceView);
    }


}
