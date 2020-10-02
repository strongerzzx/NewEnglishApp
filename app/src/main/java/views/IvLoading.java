package views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.englishapp_bishe.R;

import utils.LogUtil;

@SuppressLint("AppCompatCustomView")
public class IvLoading extends ImageView {
    private static final String TAG = "IvLoading";
    private int degree=0;
    private boolean isRoate=false;

    public IvLoading(Context context) {
        this(context,null);
    }

    public IvLoading(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public IvLoading(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();

    }
    private void initView() {
        setImageResource(R.mipmap.ic_loading);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.rotate(degree,getWidth()/2,getHeight()/2);
       // LogUtil.d(TAG,"degree --> "+degree);
        super.onDraw(canvas);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        isRoate=true;
        post(new Runnable() {
            @Override
            public void run() {
                degree+=30;
                degree=degree>360?0:degree;
                invalidate();
                if (isRoate){
                    postDelayed(this,50);
                }
            }
        });

        LogUtil.d(TAG,"degree --> "+degree);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRoate=false;
    }
}
