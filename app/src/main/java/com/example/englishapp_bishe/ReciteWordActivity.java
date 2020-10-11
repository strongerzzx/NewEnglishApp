package com.example.englishapp_bishe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import entirys.Words;
import interfaces.IReciteWordCallback;
import presenters.ReciteWordPresent;
import utils.LogUtil;
import views.VerticalScrollTextView;

public class ReciteWordActivity extends AppCompatActivity implements IReciteWordCallback {

    private static final String TAG = "ReciteWordActivity";
    private static final int RECITE_ALL_WORDS = 1;
    private TextView mTvProgress;
    private TextView mTvEnglish;
    private TextView mTvFayin;
    private TextView mTvKnow;
    private TextView mTvUnkonw;
    private TextView mTvMohu;
    private ReciteWordPresent mReciteWordPresent;
    private String mRange;
    private TextView mTvFinalProgress;
    private TextView mTvCurrentProgress;
    private int mCurrentProgress=0;
    private MyHandler mMyHandler;
    private VerticalScrollTextView mVerScroll;
    private LinearLayout mScrollLayout;
    private List<String> mList=new ArrayList<>();

    public static class MyHandler extends Handler{
        private  WeakReference<ReciteWordActivity> rwAct;

        public MyHandler(ReciteWordActivity reciteWordActivity) {
            rwAct=new WeakReference<ReciteWordActivity>(reciteWordActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            ReciteWordActivity reciteWordActivity = rwAct.get();
            super.handleMessage(msg);
            if (reciteWordActivity != null) {
                switch (msg.what) {
                    case RECITE_ALL_WORDS:
                        //获取P层回调的数据 --> 主线程
                        List<Words> BookWords = (List<Words>) msg.obj;

                        //显示第一个
                        Words words = BookWords.get(reciteWordActivity.mCurrentProgress);
                        reciteWordActivity.mTvEnglish.setText(words.getHeadWord());
                        reciteWordActivity.mTvFayin.setText(words.getUkphone());


                        //第一个单词的模糊
                        reciteWordActivity.mList.add("n: "+words.getTran());
                        reciteWordActivity.mList.add(words.getContent());
                        reciteWordActivity.mList.add(words.getCnContent());
                        reciteWordActivity.mList.add(words.getTranOther());
                        reciteWordActivity.mVerScroll.setTextList(reciteWordActivity.mList);


                        //当为明白的时候 --> 跳到下一个单词
                        reciteWordActivity.mTvKnow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                reciteWordActivity.mList.clear();
                                if (reciteWordActivity.mCurrentProgress < Integer.parseInt(reciteWordActivity.mRange)){
                                    Words words = BookWords.get(reciteWordActivity.mCurrentProgress+1);
                                    reciteWordActivity.mTvEnglish.setText(words.getHeadWord());
                                    reciteWordActivity.mTvFayin.setText(words.getUkphone());
                                    reciteWordActivity.mCurrentProgress++;
                                    reciteWordActivity.mTvCurrentProgress.setText(reciteWordActivity.mCurrentProgress+"/");


                                    //加载新的模糊
                                    reciteWordActivity.mScrollLayout.setVisibility(View.GONE);
                                    reciteWordActivity.mList.add("n: "+words.getTran());
                                    reciteWordActivity.mList.add(words.getContent());
                                    reciteWordActivity.mList.add(words.getCnContent());
                                    reciteWordActivity.mList.add(words.getTranOther());
                                    reciteWordActivity.mVerScroll.setTextList(reciteWordActivity.mList);

                                }else{
                                    Toast.makeText(reciteWordActivity, "恭喜挑战成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        //当为模糊的时候 ——> 垂直可控制的走马灯
                        reciteWordActivity.mTvMohu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                reciteWordActivity.mScrollLayout.setVisibility(View.VISIBLE);
                                reciteWordActivity.mVerScroll.startAutoScroll();


                               // reciteWordActivity.mTvEnglishOther.setVisibility(View.VISIBLE);
                            }
                        });


                        //TODO:当为不认识的时候 --> 跳出一个单词详情页
                        reciteWordActivity.mTvUnkonw.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {



                            }
                        });


                        LogUtil.d(TAG,"book size -->"+BookWords.size());
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite_word);


        mReciteWordPresent = ReciteWordPresent.getPresent();
        mReciteWordPresent.regesiterView(this);
        mMyHandler = new MyHandler(this);

        initView();

        initEvent();
    }

    private void initEvent() {

        mReciteWordPresent.getWordList();


        //显示最终进度
        Intent intent = getIntent();
        mRange = intent.getStringExtra("range");
        mTvFinalProgress.setText(mRange);
    }

    private void initView() {

        mTvCurrentProgress = findViewById(R.id.tv_recite_current_progress);
        mTvFinalProgress = findViewById(R.id.tv_recite_finaly_progress);

        mTvEnglish = findViewById(R.id.recite_english);
        mTvFayin = findViewById(R.id.recite_fayin);

        mTvKnow = findViewById(R.id.tv_recite_know);
        mTvUnkonw = findViewById(R.id.tv_recitye_unkonow);
        mTvMohu = findViewById(R.id.tv_recitye_mohu);


        mScrollLayout = findViewById(R.id.ver_scroll_bg);
        mVerScroll = findViewById(R.id.ver_scroll_tv);
        mVerScroll.setTextStyle(16,1, Color.BLACK);
        mVerScroll.setMaxLines(3);
        mVerScroll.setTextStillTime(2000);
        mVerScroll.setAnimTime(500);
    }

    @Override
    public void showRange(String range) {

    }

    @Override
    public void showAllWordsList(List<Words> BookWords) {
        if (BookWords != null) {
            //回调给主线程
            Message msg=Message.obtain();
            msg.what=RECITE_ALL_WORDS;
            msg.obj=BookWords;
            mMyHandler.sendMessage(msg);

            LogUtil.d(TAG,"mwords size -->"+BookWords.size());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReciteWordPresent != null) {
            mReciteWordPresent.unRegesiterView(this);
        }
    }
}
