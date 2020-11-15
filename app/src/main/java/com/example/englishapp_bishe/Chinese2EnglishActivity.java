package com.example.englishapp_bishe;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import entirys.Words;
import interfaces.IChineses2EnglishCallback;
import presenters.Chinese2EnglishPresenter;
import utils.LogUtil;
import views.OverDialog;
import views.UnKnowPopwindow;

public class Chinese2EnglishActivity extends AppCompatActivity implements IChineses2EnglishCallback, TextToSpeech.OnInitListener {

    private static final String TAG = "Chinese2EnglishActivity";
    private static final int CASE_UPDATE_CONTENT = 1;
    private static final int CASE_RANDOM_CORRENT_ENGLISH = 2;
    private static final int CASE_SHOW_CURRENT_PROGRESS=3;
    private ImageView mFinish;
    private TextView mContent;
    private Button mFirst;
    private Button mSecond;
    private Button mThird;
    private Button mFourth;
    private Chinese2EnglishPresenter mChinesePresenter;
    private int mCurrentIndex = 0;
    private MyHandler mCnHnader;
    private String mCurrentCrroct;
    private ImageView mFirstIv;
    private ImageView mSecondIv;
    private ImageView mThirdIv;
    private ImageView mFourthIv;
    private Drawable mDrawableError;
    private Drawable mDrawableNormal;
    private Drawable mDrawableSuccess;
    private TextView mFinalProgress;
    private TextView mCurrentProgress;
    private int mRangeProgress;
    private int mCurrentActProgress;
    private TextToSpeech mTts;
    private UnKnowPopwindow mPop;

    private List<Words> mWordsList=new ArrayList<>();
    private OverDialog mDialog;
    private ImageView mTishi;
    private ImageView mLound;
    private TextView mPass;
    private String mCurrentCorrect;

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            mTts.setLanguage(Locale.ENGLISH);

            mTts.speak(mCurrentCorrect,TextToSpeech.QUEUE_FLUSH,null,"123");

        }else{
            if (status==TextToSpeech.ERROR){
                LogUtil.d(TAG,"语音播报初始化失败");
            }
        }
    }

    public static class MyHandler extends Handler {
        private WeakReference<Chinese2EnglishActivity> mAct;

        public MyHandler(Chinese2EnglishActivity act) {
            mAct = new WeakReference<Chinese2EnglishActivity>(act);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            Chinese2EnglishActivity activity = mAct.get();
            super.handleMessage(msg);
            if (activity != null) {
                switch (msg.what) {
                    //显示Content --> 中文
                    case CASE_UPDATE_CONTENT:
                        String content = (String) msg.obj;
                        activity.mContent.setText("n:" + content);
                        break;

                    //随机在哪个按钮显示
                    case CASE_RANDOM_CORRENT_ENGLISH:
                        int first = 0;
                        int second = 1;
                        int third = 2;
                        int fourth = 3;
                        Bundle bundle = msg.getData();
                        String correct = bundle.getString("correct");
                        String error1 = bundle.getString("error1");
                        String error2 = bundle.getString("error2");
                        String error3 = bundle.getString("error3");
                        String error4 = bundle.getString("error4");
                        activity.mCurrentCrroct=correct;

                        Random random = new Random();
                        int anInt = random.nextInt(4);
                        activity.mFirst.setText(first == anInt ? correct : error1);
                        activity.mSecond.setText(second == anInt ? correct : error2);
                        activity.mThird.setText(third == anInt ? correct : error3);
                        activity.mFourth.setText(fourth == anInt ? correct : error4);

                        LogUtil.d(TAG, "animt --> " + anInt);
                        LogUtil.d(TAG, correct + ":" + error1 + ":" + error2 + ":" + error3 + ":" + error4);
                        break;

                        //显示当前的完成进度
                    case CASE_SHOW_CURRENT_PROGRESS:
                        int progress = msg.arg1;
                        activity.mCurrentActProgress=progress;
                        activity.mCurrentProgress.setText(progress+" /  ");
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chinese2_english);

        initView();

        mCnHnader = new MyHandler(this);
        mChinesePresenter = Chinese2EnglishPresenter.getPresenter();
        mChinesePresenter.regesiterView(this);
        mChinesePresenter.doClear();//清除原先的
        mChinesePresenter.requestData();//获取数据
        mChinesePresenter.doFinalProgress();//获取最终进度

        mPop=new UnKnowPopwindow(Chinese2EnglishActivity.this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mDialog = new OverDialog(this);
        mDialog.setActivity(this);

        mTts = new TextToSpeech(Chinese2EnglishActivity.this,this);




        this.mCurrentActProgress=0;
        initEvent();
    }



    private void initEvent() {


        mLound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTts.speak(mCurrentCorrect,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

        //直接下一个
        mPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentActProgress>=mRangeProgress){
                    mChinesePresenter.doInsertChineseRecord();
                    mDialog.show();
                }else{
                    mChinesePresenter.requestData();
                }
            }
        });

        mTishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.showAtLocation(v, Gravity.CENTER, Gravity.FILL_HORIZONTAL, Gravity.FILL_VERTICAL);
            }
        });

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //点击正确的 才会进入下一个 --> 完成上限后则停止刷新
        mFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mFirst.getText().equals(mCurrentCrroct)){


                    onCorrectChange(mFirst,mFirstIv);

                }else{

                    onErrorChange(mFirst,mFirstIv);
                }


            }
        });

        mSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mSecond.getText().equals(mCurrentCrroct)){


                    onCorrectChange(mSecond,mSecondIv);

                }else {

                    onErrorChange(mSecond,mSecondIv);
                }
            }
        });

        mThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mThird.getText().equals(mCurrentCrroct)){


                    onCorrectChange(mThird,mThirdIv);

                }else {

                    onErrorChange(mThird,mThirdIv);

                }
            }
        });

        mFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mFourth.getText().equals(mCurrentCrroct)){

                    onCorrectChange(mFourth,mFourthIv);

                }else{

                    onErrorChange(mFourth,mFourthIv);

                }
            }
        });
    }

    private void onCorrectChange(Button view,ImageView iv){
        view.setBackground(mDrawableSuccess);
        iv.setImageResource(R.mipmap.ic_chinese_correct);


        mTts.speak(view.getText().toString(),TextToSpeech.QUEUE_FLUSH,null,"123");


        if (mCurrentActProgress >= mRangeProgress ){
            view.setBackground(mDrawableNormal);
            iv.setImageDrawable(null);

            //插入通关记录 -->数据库
            mChinesePresenter.doInsertChineseRecord();

            //弹出通关dialog


            mDialog.show();


            LogUtil.d(TAG,"完成");
        }else{
            view.postDelayed(new Runnable() {
                @Override
                public void run() {
                    view.setBackground(mDrawableNormal);
                    iv.setImageDrawable(null);
                    mChinesePresenter.requestData();
                    view.removeCallbacks(this);
                }
            },200);
        }
    }

    private void onErrorChange(View view,ImageView iv){
        view.setBackground(mDrawableError);
        iv.setImageResource(R.mipmap.ic_chinese_error);

        mPop.showAtLocation(view, Gravity.CENTER, Gravity.FILL_HORIZONTAL, Gravity.FILL_VERTICAL);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageDrawable(null);
                view.setBackground(mDrawableNormal);
                view.removeCallbacks(this);
            }
        },200);
    }


    private void initView() {

        mFinish = findViewById(R.id.chinese_finish);
        mContent = findViewById(R.id.chinese_content);
        mFirst = findViewById(R.id.btn_first_english);
        mSecond = findViewById(R.id.btn_second_english);
        mThird = findViewById(R.id.btn_third_english);
        mFourth = findViewById(R.id.btn_fourth_english);

        mCurrentProgress = findViewById(R.id.chinese_current_progress);
        mFinalProgress = findViewById(R.id.chinese_finaly_progress);

        mFirstIv = findViewById(R.id.first_iv);
        mSecondIv = findViewById(R.id.second_iv);
        mThirdIv = findViewById(R.id.third_iv);
        mFourthIv = findViewById(R.id.fourth_iv);

        mTishi = findViewById(R.id.chinese_tishi);
        mLound = findViewById(R.id.chinese_shengyin);
        mPass = findViewById(R.id.chieses_pass);

        mDrawableError = getResources().getDrawable(R.drawable.shape_text);
        mDrawableNormal = getResources().getDrawable(R.drawable.shape_chien2eng_normal);
        mDrawableSuccess = getResources().getDrawable(R.drawable.shape_chinese_correct);
    }

    @Override
    public void showContent(String content) {
        if (content!=null){
            Message msg = Message.obtain();
            msg.what = CASE_UPDATE_CONTENT;
            msg.obj = content;
            mCnHnader.sendMessage(msg);
        }
    }


    //把获取的随机数进行 随机显示处理
    @Override
    public void showAllStr(String correct, String error1, String error2, String error3, String error4) {
        Message msg = Message.obtain();
        msg.what = CASE_RANDOM_CORRENT_ENGLISH;
        Bundle bundle = new Bundle();
        bundle.putString("correct", correct);
        bundle.putString("error1", error1);
        bundle.putString("error2", error2);
        bundle.putString("error3", error3);
        bundle.putString("error4", error4);
        msg.setData(bundle);
        mCnHnader.sendMessage(msg);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCurrentCorrect=correct;
            }
        });

        LogUtil.d(TAG, correct + ":" + error1 + ":" + error2 + ":" + error3);
    }

    @Override
    public void showCurrentProgress(int currentProgress) {

        Message msg=Message.obtain();
        msg.what=CASE_SHOW_CURRENT_PROGRESS;
        msg.arg1=currentProgress;
        mCnHnader.sendMessage(msg);
        LogUtil.d(TAG,"currentProgress --> "+currentProgress);
    }

    @Override
    public void showAllProgress(int sumProgress) {
        this.mRangeProgress=sumProgress;
        mFinalProgress.setText(sumProgress+" ");

        LogUtil.d(TAG,"sumProgress --> "+sumProgress);
    }

    @Override
    public void getData2Pop(List<Words> mWords, int currentIndex, int finalyProgress, int currentProgress) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //mWordsList.addAll(mWords);
                mPop.getData(mWords,currentIndex);
                mPop.getRange(String.valueOf(finalyProgress));
                mPop.setOnZhanClickListener(new UnKnowPopwindow.onZhanClickListener() {
                    @Override
                    public void onZhanClick() {
                        //斩
                        if (mCurrentActProgress>=mRangeProgress){
                            mChinesePresenter.doInsertChineseRecord();
                            mDialog.show();
                            mPop.dismiss();
                        }else{
                            mChinesePresenter.requestData();
                        }
                        mPop.dismiss();
                    }
                });
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mChinesePresenter != null) {
            mChinesePresenter.unRegesiterView(this);
        }
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        if (mDialog != null) {
            mDialog.dismiss();
        }
        LogUtil.d(TAG,"onDestroy");
    }
}
