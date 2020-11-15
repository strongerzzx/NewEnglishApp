package com.example.englishapp_bishe;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import entirys.Words;
import fragments.ReciteFinishTaskFragment;
import interfaces.IReciteWordCallback;
import presenters.ReciteWordPresent;
import utils.LogUtil;
import views.UnKnowPopwindow;
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
    private int mCurrentProgress = 0;
    private MyHandler mMyHandler;
    private VerticalScrollTextView mVerScroll;
    private LinearLayout mScrollLayout;
    private List<String> mList = new ArrayList<>();
    private UnKnowPopwindow mUnknowPop;
    private ReciteFinishTaskFragment mFinishTaskFragment;

    public static class MyHandler extends Handler {
        private WeakReference<ReciteWordActivity> rwAct;
        private int progress;

        public MyHandler(ReciteWordActivity reciteWordActivity) {
            rwAct = new WeakReference<ReciteWordActivity>(reciteWordActivity);
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
                        this.progress = reciteWordActivity.mCurrentProgress;


                        //显示第一个
                        Words words = BookWords.get(progress);
                        reciteWordActivity.mTvEnglish.setText(words.getHeadWord());
                        reciteWordActivity.mTvFayin.setText(words.getUkphone());


                        //第一个单词的模糊
                        reciteWordActivity.mList.add("n: " + words.getTran());
                        reciteWordActivity.mList.add(words.getContent());
                        reciteWordActivity.mList.add(words.getCnContent());
                        reciteWordActivity.mList.add(words.getTranOther());
                        reciteWordActivity.mVerScroll.setTextList(reciteWordActivity.mList);

                        reciteWordActivity.mUnknowPop.getData(BookWords, progress);


                        //点击了斩 则全部更新当前页面
                        reciteWordActivity.mUnknowPop.setOnZhanClickListener(new UnKnowPopwindow.onZhanClickListener() {
                            @Override
                            public void onZhanClick() {
                                reciteWordActivity.mList.clear();
                                if (progress < Integer.parseInt(reciteWordActivity.mRange)) {
                                    progress++;

                                    //
                                    Words words = BookWords.get(progress);
                                    reciteWordActivity.mTvEnglish.setText(words.getHeadWord());
                                    reciteWordActivity.mTvFayin.setText(words.getUkphone());
                                    reciteWordActivity.mCurrentProgress++;
                                    reciteWordActivity.mTvCurrentProgress.setText(progress + " / ");


                                    reciteWordActivity.mScrollLayout.setVisibility(View.GONE);
                                    reciteWordActivity.mList.add("n: " + words.getTran());
                                    reciteWordActivity.mList.add(words.getContent());
                                    reciteWordActivity.mList.add(words.getCnContent());
                                    reciteWordActivity.mList.add(words.getTranOther());
                                    reciteWordActivity.mVerScroll.setTextList(reciteWordActivity.mList);


                                    reciteWordActivity.mUnknowPop.setAnimationStyle(R.style.CustonUnknowPopLeft);
                                    reciteWordActivity.mUnknowPop.updateProgress(progress);
                                } else {
                                    //显示成功界面
                                    reciteWordActivity.getSupportFragmentManager()
                                            .beginTransaction()
                                            .add(R.id.recite_word_finish_task, reciteWordActivity.mFinishTaskFragment)
                                            .commit();
                                    Toast.makeText(reciteWordActivity, "恭喜挑战成功", Toast.LENGTH_SHORT).show();
                                }

                                reciteWordActivity.mUnknowPop.dismiss();
                            }
                        });


                        //当为明白的时候 --> 跳到下一个单词
                        reciteWordActivity.mTvKnow.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                reciteWordActivity.mList.clear();
                                if (progress < Integer.parseInt(reciteWordActivity.mRange)) {
                                    Words words = BookWords.get(++progress);
                                    reciteWordActivity.mTvEnglish.setText(words.getHeadWord());
                                    reciteWordActivity.mTvFayin.setText(words.getUkphone());
                                    reciteWordActivity.mCurrentProgress++;
                                    reciteWordActivity.mTvCurrentProgress.setText(progress + " / ");

                                    //下一个数据 --> 更新unKnow中的progress
                                    reciteWordActivity.mUnknowPop.updateProgress(progress);

                                    //加载新的模糊
                                    reciteWordActivity.mScrollLayout.setVisibility(View.GONE);
                                    reciteWordActivity.mList.add("n: " + words.getTran());
                                    reciteWordActivity.mList.add(words.getContent());
                                    reciteWordActivity.mList.add(words.getCnContent());
                                    reciteWordActivity.mList.add(words.getTranOther());
                                    reciteWordActivity.mVerScroll.setTextList(reciteWordActivity.mList);

                                } else {
                                    //显示成功界面
                                    reciteWordActivity.getSupportFragmentManager()
                                            .beginTransaction()
                                            .add(R.id.recite_word_finish_task, reciteWordActivity.mFinishTaskFragment)
                                            .commit();
                                    Toast.makeText(reciteWordActivity, "恭喜挑战成功", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });


                        LogUtil.d(TAG, "当前进度 --> " + progress);
                        //当为模糊的时候 ——> 垂直可控制的走马灯
                        reciteWordActivity.mTvMohu.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                reciteWordActivity.mScrollLayout.setVisibility(View.VISIBLE);
                                reciteWordActivity.mVerScroll.startAutoScroll();

                            }
                        });


                        //当为不认识的时候 --> 跳出一个单词详情页 --> 显示详情页的数据
                        reciteWordActivity.mTvUnkonw.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //点击后整个屏幕被pop占满
                                reciteWordActivity.mUnknowPop.showAtLocation(v, Gravity.CENTER, Gravity.FILL_HORIZONTAL, Gravity.FILL_VERTICAL);
                                System.out.println(reciteWordActivity.mUnknowPop.isShowing());
                            }
                        });
                        break;
                }
            }
        }
    }


    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        if (mUnknowPop != null && mUnknowPop.isShowing()) {
            mUnknowPop.dismiss();
        } else {
            finish();
        }
        LogUtil.d(TAG, "onBackPressed");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite_word);


        mReciteWordPresent = ReciteWordPresent.getPresent();
        mReciteWordPresent.regesiterView(this);
        mMyHandler = new MyHandler(this);

        mUnknowPop = new UnKnowPopwindow(this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
       // mUnknowPop.dismiss();


        initView();

        initEvent();
    }

    private void initEvent() {

        mReciteWordPresent.getWordList();


        //显示最终进度
        Intent intent = getIntent();
        mRange = intent.getStringExtra("range");
        mTvFinalProgress.setText(mRange);

        Bundle bundle = new Bundle();
        bundle.putString("range", mRange);
        mFinishTaskFragment.setArguments(bundle);


        mUnknowPop.getRange(mRange);
    }

    private void initView() {

        mFinishTaskFragment = new ReciteFinishTaskFragment();

        mTvCurrentProgress = findViewById(R.id.tv_recite_current_progress);
        mTvFinalProgress = findViewById(R.id.tv_recite_finaly_progress);

        mTvEnglish = findViewById(R.id.recite_english);
        mTvFayin = findViewById(R.id.recite_fayin);

        mTvKnow = findViewById(R.id.tv_recite_know);
        mTvUnkonw = findViewById(R.id.tv_recitye_unkonow);
        mTvMohu = findViewById(R.id.tv_recitye_mohu);


        mScrollLayout = findViewById(R.id.ver_scroll_bg);
        mVerScroll = findViewById(R.id.ver_scroll_tv);
        mVerScroll.setTextStyle(16, 1, Color.BLACK);
        mVerScroll.setMaxLines(3);
        mVerScroll.setTextStillTime(2000);
        mVerScroll.setAnimTime(500);
    }

    @Override
    public void showAllWordsList(List<Words> BookWords) {
        if (BookWords != null) {
            //回调给主线程
            Message msg = Message.obtain();
            msg.what = RECITE_ALL_WORDS;
            msg.obj = BookWords;
            mMyHandler.sendMessage(msg);

            LogUtil.d(TAG, "mwords size -->" + BookWords.size());
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mReciteWordPresent != null) {
            mReciteWordPresent.unRegesiterView(this);
        }
        if (mUnknowPop != null) {
            mUnknowPop.dismiss();
        }
    }
}
