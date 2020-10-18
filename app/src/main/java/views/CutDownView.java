package views;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.englishapp_bishe.R;

/**
 * 作者：zzx on 2020/10/16 15:18
 * <p>
 * 作用： 自定义计时器
 */
public class CutDownView extends View {

    private int mCurrentTime = 500;//当前时间
    private int mSumTime = 100;//总时间
    private int mInnerColor;
    private int mOutColor;
    private int mTextColor;
    private int mInnerWidth;
    private int mOutWidth;
    private int mTextWidth;
    private Paint mOutPaint;
    private Paint mInnerPaint;
    private Paint mTextPaint;

    public CutDownView(Context context) {
        this(context,null);
    }

    public CutDownView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CutDownView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);


        initAttrs(context,attrs);

        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int lpWidth = getLayoutParams().width;
        int lpHeight = getLayoutParams().height;

        if (widthMode==MeasureSpec.EXACTLY && heightMode==MeasureSpec.EXACTLY){
            width=lpWidth;
            height=lpHeight;
        }


        setMeasuredDimension(width,height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //外面大圆

        int raduis=(getMeasuredWidth()-2*mOutWidth)/2;
        int center=getMeasuredWidth()/2;
        canvas.drawCircle(center,center,raduis,mOutPaint);

        //里面的根据时间流逝来画
        if (mCurrentTime==0)
            return;
        //当前的百分比
        int per = mCurrentTime / mSumTime;
        RectF rectF=new RectF(center-raduis,center-raduis,center+raduis,center+raduis);
        canvas.drawArc(rectF,-90,per*220,false,mInnerPaint);

    }

    private void initPaint() {
        //外圆
        mOutPaint = new Paint();
        mOutPaint.setColor(mOutColor);
        mOutPaint.setStrokeWidth(mOutWidth);
        mOutPaint.setAntiAlias(true);

        //内圆
        mInnerPaint = new Paint();
        mInnerPaint.setColor(mInnerColor);
        mInnerPaint.setStrokeWidth(mInnerWidth);
        mInnerPaint.setAntiAlias(true);

        //文字画笔
        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStrokeWidth(mTextWidth);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.STROKE);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.CutDownView);
        mInnerColor = ta.getColor(R.styleable.CutDownView_innerColor, -1);
        mOutColor = ta.getColor(R.styleable.CutDownView_outColor, -1);
        mTextColor = ta.getColor(R.styleable.CutDownView_textColor, -1);
        mInnerWidth = (int) ta.getDimension(R.styleable.CutDownView_innerCircleWidth, 20);
        mOutWidth = (int) ta.getDimension(R.styleable.CutDownView_outCircleWidth, 20);
        mTextWidth = (int) ta.getDimension(R.styleable.CutDownView_textWidth, 10);
        ta.recycle();
    }


    public int getCurrentTime() {
        return mCurrentTime;
    }

    public void setCurrentTime(int currentTime) {
        mCurrentTime = currentTime;
    }

    public int getSumTime() {
        return mSumTime;
    }

    public void setSumTime(int sumTime) {
        mSumTime = sumTime;
    }

    public int getInnerColor() {
        return mInnerColor;
    }

    public void setInnerColor(int innerColor) {
        mInnerColor = innerColor;
    }

    public int getOutColor() {
        return mOutColor;
    }

    public void setOutColor(int outColor) {
        mOutColor = outColor;
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    public int getInnerWidth() {
        return mInnerWidth;
    }

    public void setInnerWidth(int innerWidth) {
        mInnerWidth = innerWidth;
    }

    public int getOutWidth() {
        return mOutWidth;
    }

    public void setOutWidth(int outWidth) {
        mOutWidth = outWidth;
    }

    public int getTextWidth() {
        return mTextWidth;
    }

    public void setTextWidth(int textWidth) {
        mTextWidth = textWidth;
    }
}
