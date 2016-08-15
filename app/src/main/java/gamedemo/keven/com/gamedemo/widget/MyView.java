package gamedemo.keven.com.gamedemo.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

import gamedemo.keven.com.gamedemo.R;


/**
 * Created by keven on 16/8/15.
 */

public class MyView extends View {
    private Bitmap mBitmap;
    private Matrix mMatrix = new Matrix();

    public MyView(Context context) {
        this(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    private void initialize(){
        Bitmap bitmap = ((BitmapDrawable) (getResources().getDrawable(R.drawable.king_arthur))).getBitmap();
        mBitmap = bitmap;

        mMatrix.setScale(100f/bitmap.getWidth(),100f/bitmap.getHeight());

        mMatrix.postTranslate(100,100);

        mMatrix.postSkew(0.2f,0.2f,100,100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,mMatrix,null);
    }
}
