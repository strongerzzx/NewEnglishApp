package views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishapp_bishe.R;

import bases.BaseAppciation;

/*
   这个类里就是做 根据类型来加载页面
 */
public abstract class UILoader extends FrameLayout {

    private View mLoadingView;
    private View mMSuccessView;
    private View mNetworkErrorView;
    private View mEmptyView;

    //在没网络时，点击后重新加载
    private onRetryGetLoader mRetry;

    public enum UIStatus{
        LOADING,SUCCESS,NETWORK_ERROR,EMPETY_DATA,NONE;
    }
    private UIStatus mCurrentStatus= UIStatus.NONE;

    public UILoader(@NonNull Context context) {
        this(context,null);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView();
    }

    //初始化每个状态的UI
    private void initView() {
        swithUIByCurrentStatus();
    }

    private void swithUIByCurrentStatus() {
        //加载中
        if (mLoadingView == null) {
            mLoadingView = getLoadingView();
            //加入到FrameLayout中
            addView(mLoadingView);
        }
        //根据状态设置页面是否可见
        mLoadingView.setVisibility(mCurrentStatus== UIStatus.LOADING?VISIBLE:GONE);

        //成功
        if (mMSuccessView == null) {
            mMSuccessView = getSuccessView(this);
            addView(mMSuccessView);
        }
        //根据状态设置页面是否可见
        mMSuccessView.setVisibility(mCurrentStatus== UIStatus.SUCCESS?VISIBLE:GONE);

        //网络错误的页面
        if (mNetworkErrorView == null) {
            mNetworkErrorView = getNetworkErrorView();
            addView(mNetworkErrorView);
        }
        //根据状态设置页面是否可见
        mNetworkErrorView.setVisibility(mCurrentStatus== UIStatus.NETWORK_ERROR?VISIBLE:GONE);

        //空数据
        if (mEmptyView == null) {
            mEmptyView = getEmptyView();
            addView(mEmptyView);
        }
        //根据状态设置页面是否可见
        mEmptyView.setVisibility(mCurrentStatus== UIStatus.EMPETY_DATA?VISIBLE:GONE);
    }

    //更新UI
    public void updateStatus(UIStatus status){
        mCurrentStatus=status;
        //一定要在主线程上
        BaseAppciation.getHandler().post(new Runnable() {
            @Override
            public void run() {
                swithUIByCurrentStatus();
            }
        });
    }


    //不知道调用什么页面 让他的孩子去实现
    protected abstract View getSuccessView(ViewGroup container);

    private View getEmptyView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.load_empty_error,null);
    }

    private View getNetworkErrorView() {
        View networkError = LayoutInflater.from(getContext()).inflate(R.layout.load_network_error, null);

        return networkError;
    }


    private View getLoadingView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.load_loading_error,null);
    }

    public interface onRetryGetLoader{
        void onRetryLoad();
    }

    public void setRetry(onRetryGetLoader retry) {
        mRetry = retry;
    }
}
