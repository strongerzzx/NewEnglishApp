package views;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.englishapp_bishe.R;

import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/9 13:40
 * <p>
 * 作用： xxxx
 */
public class ManagerPopMenu extends PopupWindow {

    private static final String TAG = "ManagerPopMenu";
    private final View mInflate;
    private TextView mPopdelete;

    private final int[] mLocation=new int[2];//坐标 0:x 1:y
    private Rect mRect=new Rect();//画一个矩形
    private int popuGravvity= Gravity.NO_GRAVITY;//位置不在中心
    private final int LIST_PADDING=0; //列表弹窗的间隔
    private int mScreenWidth,mScreenHeight;//屏幕的宽，高
    private onManagerPopDeleClickListener mOnManagerPopDeleClickListener;



    public ManagerPopMenu(Context context) {
        super(context);

        this.setWidth(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setOutsideTouchable(true);

        mInflate = LayoutInflater.from(context).inflate(R.layout.managere_pop_menu_layot, null);
        this.setContentView(mInflate);

        //获取屏幕宽高
        mScreenWidth=context.getResources().getDisplayMetrics().widthPixels;
        mScreenHeight=context.getResources().getDisplayMetrics().heightPixels;

        initChildView();


        initEvent();

    }

    private void initEvent() {
        //点击事件 --> 回调
            mPopdelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnManagerPopDeleClickListener != null) {
                        mOnManagerPopDeleClickListener.onManagerPopDeleClick();
                        //点击后pop消失
                        dismiss();
                    }
                }
            });
    }

    public void show(View view){
        //获取点击屏幕的位置
        view.getLocationOnScreen(mLocation);

        //设置矩阵的大小
        mRect.set(mLocation[0],mLocation[1],mLocation[0]+view.getWidth(),mLocation[1]+view.getHeight());

        showAtLocation(view,popuGravvity,(mScreenWidth-(getWidth()/2))/2,mRect.bottom);
        int i = mScreenWidth - (getWidth() / 2);
        LogUtil.d(TAG,"getWidth -->"+getWidth());
        LogUtil.d(TAG,"弹窗位置 -->"+i);
    }

    private void initChildView() {
        mPopdelete = mInflate.findViewById(R.id.manager_pop_menu_dele);
    }


    public void setOnManagerPopDeleClickListener(onManagerPopDeleClickListener onManagerPopDeleClickListener) {
        mOnManagerPopDeleClickListener = onManagerPopDeleClickListener;
    }

    public interface onManagerPopDeleClickListener{
        void onManagerPopDeleClick();
    }
}
