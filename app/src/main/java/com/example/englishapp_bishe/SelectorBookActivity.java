package com.example.englishapp_bishe;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import adapters.SelectorBookAdapter;
import beans.SelectBookBeans;
import interfaces.ISelectBookCallback;
import presenters.SelectorPresenter;
import views.UILoader;

public class SelectorBookActivity extends AppCompatActivity implements ISelectBookCallback {

    private static final String TAG = "SelectorBookActivity";
    public static final int TYPE_RELEASE_ZIP_PROGRESS=1;
    public static final int TYPE_READ_JSON_PROGRESS=2;
    private SelectorPresenter mSelectorPresenter;
    private FrameLayout mContentLayout;
    private UILoader mUiLoader;
    private TwinklingRefreshLayout mRefreshLayout;
    private View mSuccessView;
    private SelectorBookAdapter mSelectorAdapter;
    private List<SelectBookBeans.CatesBean.BookListBean> allBookList=new ArrayList<>();

    private boolean isRefresh=true;
    private ProgressBar mBar;
    public static SelectorHandler mSelectorHandler;



    public static class SelectorHandler extends Handler{
        WeakReference<SelectorBookActivity> mSelectorAct;
        public SelectorHandler(SelectorBookActivity selectorActivity) {
            mSelectorAct=new WeakReference<SelectorBookActivity>(selectorActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            SelectorBookActivity selectorACT = mSelectorAct.get();
            super.handleMessage(msg);
            if (selectorACT != null) {
                switch (msg.what){
                    case TYPE_RELEASE_ZIP_PROGRESS:
                        break;
                    case TYPE_READ_JSON_PROGRESS:
                            mSelectorAct.get().mBar.setVisibility(View.GONE);
                        break;
                }
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selector_book);

        mSelectorPresenter = SelectorPresenter.getInstance();
        mSelectorPresenter.regestierSelectBookCallback(this);

        mSelectorHandler = new SelectorHandler(this);

        initView();

        initEvent();
    }

    private void initEvent() {
        //获取数据
        mSelectorPresenter.requestBook();

        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                    mSelectorPresenter.doLoadMore();
                    isRefresh=true;
            }
        });

        mSelectorAdapter.setOnSelectorItemClickListener(new SelectorBookAdapter.onSelectorItemClickListener() {
            @Override
            public void onSelectorItemClicker(int position) {
                //TODO:弹出一个Dialog --> 数据结束
                mSelectorPresenter.requestPositionZip(position);
                mBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initView() {
        mContentLayout = findViewById(R.id.select_book_content);
        mBar = findViewById(R.id.text_pro);

        if (mUiLoader == null) {
            mUiLoader = new UILoader(this) {
                @Override
                protected View getSuccessView(ViewGroup container) {
                    return createSuccessView(container);
                }
            };
        }

        if (mUiLoader.getParent() instanceof ViewGroup){
            ((ViewGroup) mUiLoader.getParent()).removeView(mUiLoader);
        }

        mContentLayout.addView(mUiLoader);

    }

    private View createSuccessView(ViewGroup container) {
        mSuccessView = LayoutInflater.from(this).inflate(R.layout.select_book_recycler_view, container, false);
        mRefreshLayout = mSuccessView.findViewById(R.id.select_twink);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableOverScroll(false);
        RecyclerView rvSuccess =  mSuccessView.findViewById(R.id.rv_select);
        rvSuccess.setLayoutManager(new LinearLayoutManager(this));
        rvSuccess.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom=8;
                outRect.top=5;
            }
        });
        mSelectorAdapter = new SelectorBookAdapter();
        rvSuccess.setAdapter(mSelectorAdapter);
        return mSuccessView;
    }

    @Override
    public void showBookList(List<SelectBookBeans.CatesBean.BookListBean> bookListBeans) {
        if (bookListBeans != null && isRefresh) {
            mUiLoader.updateStatus(UILoader.UIStatus.SUCCESS);
            mSelectorAdapter.setData(bookListBeans);
        }
    }

    @Override
    public void pullRefreshMore(int size) {
        if (size>0){
            Toast.makeText(this, "加载了"+size+"条数据...", Toast.LENGTH_SHORT).show();
        }else{
            isRefresh=false;
            Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
        }
        mRefreshLayout.finishLoadmore();
    }

    @Override
    public void onLoading() {
        mUiLoader.updateStatus(UILoader.UIStatus.LOADING);
    }

    @Override
    public void onNetworkError() {
        mUiLoader.updateStatus(UILoader.UIStatus.NETWORK_ERROR);
    }

    @Override
    public void downZipProgress(int currentPos, long finalPos) {
        mBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSelectorPresenter != null) {
            mSelectorPresenter.unRegestierSelectBookCallback(this);
        }
    }
}