package com.example.englishapp_bishe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.List;

import adapters.CikuAdapter;
import entirys.Words;
import interfaces.ICikuCallback;
import presenters.CIkuPresent;
import utils.LogUtil;

public class CikuActivity extends AppCompatActivity implements ICikuCallback {

    private static final String TAG = "CikuActivity";
    private RecyclerView mCikuRv;
    private TwinklingRefreshLayout mRefreshLayout;
    private CikuAdapter mAdapter;
    private CIkuPresent mCIkuPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciku);

        mCIkuPresent = CIkuPresent.getPresent();
        mCIkuPresent.regesiterView(this);
        mCIkuPresent.queryAllWords();

        initView();

        initEvent();
    }

    private void initEvent() {

        //加载更多
        mRefreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                //开始加载更多
                mCIkuPresent.doLoader();
            }
        });

        //跳转到详情页
        mAdapter.setOnCiKuItemClickListener(new CikuAdapter.onCiKuItemClickListener() {
            @Override
            public void onCiKuClickListener(int position) {
                Intent intent=new Intent(CikuActivity.this,CikuDetailActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mCikuRv = findViewById(R.id.ci_ku_recyle_view);
        mRefreshLayout = findViewById(R.id.ci_ku_tw_refresh);
        mRefreshLayout.setEnableRefresh(false);
        mRefreshLayout.setEnableOverScroll(false);


        mCikuRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CikuAdapter();
        mCikuRv.setAdapter(mAdapter);

        //设置头布局
        setHeadView(mCikuRv);
    }

    private void setHeadView(RecyclerView cikuRv) {
        View headerView = LayoutInflater.from(this).inflate(R.layout.ciku_rv_header, cikuRv, false);
        mAdapter.setHeaderView(headerView);
    }

    @Override
    public void showAllWords(List<Words> wordsList) {
        mAdapter.setData(wordsList);
        LogUtil.d(TAG,"wordsListSize --> "+wordsList.size());
    }

    @Override
    public void onPullRefresh(int size) {
        if (size>0) {
            mRefreshLayout.finishLoadmore();
            Toast.makeText(this, "加载了"+size+"条数据", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "没有更多数据", Toast.LENGTH_SHORT).show();
        };
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCIkuPresent != null) {
            mCIkuPresent.unRegesiterView(this);
        }
    }
}
