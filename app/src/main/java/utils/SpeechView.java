package utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.example.englishapp_bishe.R;


/**
 * 作者：zzx on 2020/10/26 19:38
 *  作用： xxxx
 */
public class SpeechView  extends View implements View.OnTouchListener {

    private Paint mPaint;
    private Rect mRect;
    private int norlmalRes;
    private String normalText;
    private int pressRes;
    private String pressText;
    private boolean isPressed=false;
    private boolean isOut;//点击是否在范围内
    private onPressCallback mOnPressCallback;
    private float mDownY;
    private float mDownX;
    private Dialog mDialog;

    public SpeechView(Context context) {
        this(context,null);
    }

    public SpeechView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public SpeechView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initPaint();

    }

    private void initPaint() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(UiUtil.dp2px(getContext(),20));
        mPaint.setColor(Color.WHITE);

        mRect = new Rect();

        //设置属性
        norlmalRes= R.drawable.shape_speech_normal;
        normalText="按住说话";

        pressRes=R.drawable.shape_speech_press;
        pressText="松开结束";

        setOnTouchListener(this);

        initSpeechDialog();
    }

    private void initSpeechDialog() {
        mDialog = new Dialog(getContext());
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mDialog.setContentView(R.layout.speech_item_view);
        mDialog.setCanceledOnTouchOutside(true);

        WindowManager windowManager = mDialog.getWindow().getWindowManager();


        ImageView speechIv = mDialog.findViewById(R.id.item_speech_iv);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int totalWidth=widthMode==MeasureSpec.EXACTLY?250:widthSize;
        int totalHeight=heightMode==MeasureSpec.EXACTLY?100:heightSize;

        setMeasuredDimension(totalWidth,totalHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mRect.set(0,0,getWidth(),getHeight());
        if (!isPressed){
            setBackgroundResource(norlmalRes);
            drawText(canvas,mRect,normalText);
        }else{
            setBackgroundResource(pressRes);
            drawText(canvas,mRect,pressText);
        }
    }

    private void drawText(Canvas canvas, Rect rect, String text) {
        Paint.FontMetricsInt fontMetricsInt = mPaint.getFontMetricsInt();
        //计算baseline
        int baseLine=rect.top+(rect.bottom-rect.top-fontMetricsInt.bottom+fontMetricsInt.top)/2 - fontMetricsInt.top;
        mPaint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(text,rect.centerX(),baseLine,mPaint);
    }


    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                mDownX = getX();
                mDownY = getY();
                isOut=false;

                if (!isPressed){
                    isPressed=true;
                    postInvalidate();
                    if (mOnPressCallback != null) {
                        mOnPressCallback.onStartRecord();

                        //弹出对话框
                        mDialog.show();
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (isPressed && mOnPressCallback!=null){
                    float moveY = event.getY();
                    float moveX = event.getX();

                    if (mDownY- moveY<getHeight()){
                        if (isOut){
                            isOut=false;
                            //默认布局
                        }else{
                            if (!isOut){
                                isOut=true;
                                //取消布局
                            }
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_UP:
                if (isPressed){
                    isPressed=false;
                    postInvalidate();
                    if (mOnPressCallback != null) {
                        float upY = event.getY();
                        float upX = event.getX();
                        if (mDownY-upY<getHeight()){
                            //录音结束
                            mOnPressCallback.onStopRecord();
                        }else {
                            mOnPressCallback.onCancleRecord();
                        }
                        mDialog.dismiss();
                    }
                }
                break;

        }
        return true;
    }

    public onPressCallback getOnPressCallback() {
        return mOnPressCallback;
    }

    public void setOnPressCallback(onPressCallback onPressCallback) {
        mOnPressCallback = onPressCallback;
    }

    public interface  onPressCallback{
        //开始录音
        void onStartRecord();

        //结束录音
        void onStopRecord();

        //取消录音
        void onCancleRecord();
    }
}

