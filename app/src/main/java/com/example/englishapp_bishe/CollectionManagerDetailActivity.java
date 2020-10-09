package com.example.englishapp_bishe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adapters.ManagerDetailAdapter;
import entirys.Words;
import interfaces.IMangerDetailCallback;
import presenters.DetailPresent;
import presenters.ManagerDetailPresent;
import utils.LogUtil;
import views.ManagerPopMenu;

public class CollectionManagerDetailActivity extends AppCompatActivity implements IMangerDetailCallback {

    private static final String TAG = "CollectionManagerDetailActivity";
    private ManagerDetailPresent mManagerDetailPresent;
    private RecyclerView mManagerRv;
    private ManagerDetailAdapter mManagerAdapter;
    private ManagerPopMenu mPopWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_manager_detail);

        mManagerDetailPresent = ManagerDetailPresent.getPresent();
        mManagerDetailPresent.regesiterView(this);
        //获取所有ID配对的单词
        mManagerDetailPresent.queryCollectionIDForWords();

        initView();


        //设置头布局
        setHeaderView(mManagerRv);
        mPopWindow = new ManagerPopMenu(CollectionManagerDetailActivity.this);
        initEvent();
    }

    private void setHeaderView(RecyclerView managerRv) {
        View headerView = LayoutInflater.from(this).inflate(R.layout.manager_detail_recycler_header, managerRv, false);
        ImageView managerIv =headerView.findViewById(R.id.manager_detail_header_finish_iv);
        //头布局中的返回按钮 --> 直接结束
        managerIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mManagerAdapter.setHeaderView(headerView);
    }

    private void initEvent() {

        //跳转到收藏夹中的单词 对应详情页
        mManagerAdapter.setOnManagerDetailItemClickListner(new ManagerDetailAdapter.onManagerDetailItemClickListner() {
            @Override
            public void onManagerDetailItemClick(int pos, List<Words> mWords) {
                Intent intent=new Intent(CollectionManagerDetailActivity.this,CikuDetailActivity.class);
                LogUtil.d(TAG,"收藏夹中的详情单词 pos -->"+pos);
                DetailPresent.getPresent().getCikuData(pos,mWords);
                startActivity(intent);
            }
        });


        //长按 -->  弹出Pop  --> 点击删除
        mManagerAdapter.setManagerDetailIteLongmClickListner(new ManagerDetailAdapter.onManagerDetailIteLongmClickListner() {
            @Override
            public void onManagerDetailItemLongClick(int wordsID, List<Words> mWords,View itemView) {

                mPopWindow.show(itemView);

                //获取单词ID
                mManagerDetailPresent.getWordsID(wordsID);


                //点击删除
                mPopWindow.setOnManagerPopDeleClickListener(new ManagerPopMenu.onManagerPopDeleClickListener() {
                    @Override
                    public void onManagerPopDeleClick() {

                        mManagerDetailPresent.doDeleteWords();

                    }
                });

                LogUtil.d(TAG,"长按 -->"+wordsID);
            }
        });

        //消失后更新数据
        mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                //mManagerDetailPresent.queryCollectionIDForWords();
            }
        });

    }

    private void initView() {
        mManagerRv = findViewById(R.id.manager_detail_rv);
        mManagerRv.setLayoutManager(new LinearLayoutManager(this));
        mManagerAdapter = new ManagerDetailAdapter();
        mManagerRv.setAdapter(mManagerAdapter);
    }

    @Override
    public void getWords(List<Words> mWords) {
        //显示每个收藏夹的单词
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mWords != null) {
                    mManagerAdapter.setData(mWords);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mManagerDetailPresent != null) {
            mManagerDetailPresent.unRegesiterView(this);
        }
    }
}
