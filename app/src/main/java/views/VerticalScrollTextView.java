package views;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zzx on 2020/10/10 15:16
 * <p>
 * 作用： xxxx
 */
public class VerticalScrollTextView extends TextSwitcher implements ViewSwitcher.ViewFactory  {
    private static final int FLAG_START_AUTO_SCROLL = 0;
    private static final int FLAG_STOP_AUTO_SCROLL = 1;
    private static final int FLAG_START_FIRST_SCROLL = 2;//第一次滚动不用间隔,后续滚动有间隔

    private float mTextSize = 15 ;
    private int mPadding = 5;
    private int textColor = Color.BLACK;
    private int maxLines = 1;//默认最多一行

    /**
     * @param textSize 字号
     * @param padding 内边距
     * @param textColor 字体颜色
     */
    public void setTextStyle(float textSize, int padding, int textColor) {
        mTextSize = textSize;
        mPadding = padding;
        this.textColor = textColor;
    }

    /**
     * @param maxLines 最大行数
     */
    public void setMaxLines(int maxLines) {
        this.maxLines = maxLines;
    }
    private OnItemClickListener itemClickListener;
    private Context mContext;
    private int currentId = -1;
    private ArrayList<String> textList;
    private static Handler handler;

    public VerticalScrollTextView(Context context) {
        this(context, null);
    }

    public VerticalScrollTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        textList = new ArrayList<String>();
    }

    /**
     * 渐进渐出时间间隔
     * @param animDuration
     */
    public void setAnimTime(long animDuration) {
        setFactory(this);
        Animation in = new TranslateAnimation(0, 0, animDuration, 0);
        in.setDuration(animDuration);
        in.setInterpolator(new AccelerateInterpolator());
        Animation out = new TranslateAnimation(0, 0, 0, -animDuration);
        out.setDuration(animDuration);
        out.setInterpolator(new AccelerateInterpolator());
        setInAnimation(in);
        setOutAnimation(out);
    }

    /**
     * 设置文本
     * @param time
     */
    public void setTextStillTime(final long time){
        handler =new Handler() {
            @Override
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case FLAG_START_AUTO_SCROLL:
                        if (textList.size() > 0) {
                            currentId++;
                            setText(textList.get(currentId % textList.size()));
                        }
                        //handler.sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL,time);
                        break;
                    case FLAG_STOP_AUTO_SCROLL:
                        handler.removeMessages(FLAG_START_AUTO_SCROLL);
                        break;
                    case FLAG_START_FIRST_SCROLL:
                        handler.sendEmptyMessageDelayed(FLAG_START_AUTO_SCROLL,0);
                        break;
                }
            }
        };
    }
    /**
     * 设置数据源
     * @param titles
     */
    public void setTextList(List<String> titles) {
        textList.clear();
        textList.addAll(titles);
        currentId = -1;
    }

    /**
     * 开始滚动
     */
    public void startAutoScroll() {
        handler.sendEmptyMessage(FLAG_START_FIRST_SCROLL);
    }

    /**
     * 停止滚动
     */
    public void stopAutoScroll() {
        handler.sendEmptyMessage(FLAG_STOP_AUTO_SCROLL);
    }

    @Override
    public View makeView() {
        TextView t = new TextView(mContext);
        t.setGravity(Gravity.LEFT);
        t.setMaxLines(maxLines);
        t.setPadding(mPadding, mPadding, mPadding, mPadding);
        t.setTextColor(textColor);
        t.setTextSize(mTextSize);
        t.setClickable(true);
        t.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener != null && textList.size() > 0 && currentId != -1) {
                    itemClickListener.onItemClick(currentId % textList.size());
                }
            }
        });
        return t;
    }

    /**
     * 设置点击事件监听
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    /**
     * 轮播文本点击监听器
     */
    public interface OnItemClickListener {
        /**
         * 点击回调
         * @param position 当前点击ID
         */
        void onItemClick(int position);
    }
}
