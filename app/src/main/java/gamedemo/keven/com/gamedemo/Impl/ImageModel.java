package gamedemo.keven.com.gamedemo.Impl;

import android.graphics.Bitmap;
import android.graphics.Matrix;

import gamedemo.keven.com.gamedemo.inter.Drawable;

/**
 * Created by keven on 16/8/11.
 */

public class ImageModel implements Drawable {

    private Bitmap img;
    private Matrix matrix = new Matrix();

    public void setPicMatrix(Matrix matrix){
        this.matrix = matrix;
    }
    @Override
    public Matrix getPicMatrix() {
        return matrix;
    }

    public void setCurrentPic(Bitmap bitmap){
        img = bitmap;
    }

    @Override
    public Bitmap getCurrentPic() {
        return img;
    }

    @Override
    public int getPicWidth() {
        return img.getWidth();
    }

    @Override
    public int getPicHeight() {
        return img.getHeight();
    }
}
