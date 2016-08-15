package gamedemo.keven.com.gamedemo.inter;


import android.graphics.Bitmap;
import android.graphics.Matrix;

/**
 * Created by keven on 16/8/11.
 */

public interface Drawable {

    public Matrix getPicMatrix();
    public Bitmap getCurrentPic();
    public int getPicWidth();
    public int getPicHeight();
}
