package com.example.englishapp_bishe;

import android.graphics.drawable.AnimationDrawable;
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
import java.util.List;
import java.util.Locale;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import entirys.Words;
import interfaces.IListener2SelectorCallback;
import presenters.Listener2SelectorPresenter;
import utils.LogUtil;
import views.OverDialog;
import views.UnKnowPopwindow;

public class Listen2SelectorActivity extends AppCompatActivity implements TextToSpeech.OnInitListener, IListener2SelectorCallback {

    private static final String TAG = "Listen2SelectorActivity";
    private static final int CASE_SHOW_ERROR_MSG = 1;
    private static final int CASE_SHOW_CORRECT_MSG=2;

    @BindView(R.id.listener_iv_content)
    public ImageView content;
    @BindView(R.id.listener_finish)
    public ImageView finish;
    @BindView(R.id.listener_first)
    public Button first;
    @BindView(R.id.listener_second)
    public Button second;
    @BindView(R.id.listener_third)
    public Button third;
    @BindView(R.id.listener_fourth)
    public Button fourth;
    @BindView(R.id.listener_pass)
    public TextView pass;
    @BindView(R.id.listener_tishi)
    public ImageView tishi;
    @BindView(R.id.listener_shengyin)
    public ImageView syin;
    @BindView(R.id.listener_firist_iv)
    public ImageView firstIv;
    @BindView(R.id.listener_second_iv)
    public ImageView secondIv;
    @BindView(R.id.listener_third_iv)
    public ImageView thirdIv;
    @BindView(R.id.listener_fourth_iv)
    public ImageView fourthIv;
    @BindView(R.id.listener_current_progress)
    public TextView tvCurrentProgress;
    @BindView(R.id.listener_finaly_progress)
    public TextView tvFinalyProgress;

    private TextToSpeech mTts;
    private Listener2SelectorPresenter mListener2SelectorPresenterresenter;
    private ListenerHandler mListenerHandler;
    private String mCurrentCorrect;
    private Drawable mDrawableError;
    private Drawable mDrawableNormal;
    private Drawable mDrawableSuccess;
    private int mCurrentIndex;
    private int mMaxRange;
    private OverDialog mDialog;
    private UnKnowPopwindow mPop;

    public static class ListenerHandler extends Handler{
        private WeakReference<Listen2SelectorActivity>mListenerAct;

        public ListenerHandler(Listen2SelectorActivity Act) {
            mListenerAct=new WeakReference<Listen2SelectorActivity>(Act);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            Listen2SelectorActivity listeneAct = mListenerAct.get();
            super.handleMessage(msg);
            if (listeneAct!=null){
                switch (msg.what) {
                    case CASE_SHOW_ERROR_MSG:
                        //随机显示正确答案
                        Random random=new Random();
                        int index = random.nextInt(4);
                        int first=0;
                        int second=1;
                        int third=2;
                        int fourth=3;
                        Bundle bundle = msg.getData();
                        String correctChinese = bundle.getString("correctChinese");
                        String error1 = bundle.getString("error1");
                        String error2 = bundle.getString("error2");
                        String error3 = bundle.getString("error3");
                        String error4 = bundle.getString("error4");
                        listeneAct.mCurrentCorrect=correctChinese;


                        listeneAct.first.setText(index==first?correctChinese:error1);
                        listeneAct.second.setText(index==second?correctChinese:error2);
                        listeneAct.third.setText(index==third?correctChinese:error3);
                        listeneAct.fourth.setText(index==fourth?correctChinese:error4);

                        break;

                    case CASE_SHOW_CORRECT_MSG:
                        // 获取正确的单词 --> 播放声音
                        String correctEnglish= (String) msg.obj;

                        listeneAct.mTts.speak(correctEnglish,TextToSpeech.QUEUE_FLUSH,null);

                        //设置帧动画
                        listeneAct.content.setBackgroundResource(R.drawable.lound_frame_anim);
                        AnimationDrawable drawable = (AnimationDrawable) listeneAct.content.getBackground();

                        //点击声音 --> 播放tts+动画
                        listeneAct.content.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!listeneAct.mTts.isSpeaking()){
                                    listeneAct.content.setBackgroundResource(R.drawable.lound_frame_anim);
                                    drawable.start();
                                    listeneAct.mTts.speak(correctEnglish,TextToSpeech.QUEUE_FLUSH,null);

                                    listeneAct.mListenerHandler.postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            drawable.stop();
                                        }
                                    },200);
                                }else{
                                    listeneAct.content.setImageResource(R.mipmap.ic_listener_bo);
                                }
                            }
                        });

                        listeneAct.syin.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                listeneAct.mTts.speak(correctEnglish,TextToSpeech.QUEUE_FLUSH,null);

                                //并且让中间的图标开始动画
                                drawable.start();

                                postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        drawable.stop();
                                    }
                                },200);
                            }
                        });
                        LogUtil.d(TAG,"correctEnglish --> "+correctEnglish);
                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen2_selector);

        ButterKnife.bind(this);
        mDialog = new OverDialog(this);
        mDialog.setActivity(this);

        mPop = new UnKnowPopwindow(this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        mListenerHandler = new ListenerHandler(this);
        mListener2SelectorPresenterresenter = Listener2SelectorPresenter.getPresenter();
        mListener2SelectorPresenterresenter.regesiterView(this);
        mListener2SelectorPresenterresenter.doRandomRange();
        mListener2SelectorPresenterresenter.doClearRandom();//清除以前的索引
        mListener2SelectorPresenterresenter.requestData();//请求数据

        mTts = new TextToSpeech(this,this);

        mDrawableError = getResources().getDrawable(R.drawable.shape_text);
        mDrawableNormal = getResources().getDrawable(R.drawable.shape_chien2eng_normal);
        mDrawableSuccess = getResources().getDrawable(R.drawable.shape_chinese_correct);


        initEvent();
    }

    private void initEvent() {

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //点击就下一个单词
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first.getText().equals(mCurrentCorrect)) {
                    onCorrectChange(first,firstIv);
                }else{
                    onErrorChange(first,firstIv);
                    LogUtil.d(TAG,"选择失败");
                }
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (second.getText().equals(mCurrentCorrect)) {
                    onCorrectChange(second,secondIv);
                }else{
                    onErrorChange(second,secondIv);
                    LogUtil.d(TAG,"选择失败");
                }
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (third.getText().equals(mCurrentCorrect)) {
                    onCorrectChange(third,thirdIv);
                }else{
                    onErrorChange(third,thirdIv);
                    LogUtil.d(TAG,"选择失败");
                }

            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourth.getText().equals(mCurrentCorrect)) {
                    onCorrectChange(fourth,fourthIv);
                }else{
                    onErrorChange(fourth,fourthIv);
                    LogUtil.d(TAG,"选择失败");
                }
            }
        });

        //pass
        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex>=mMaxRange){
                    mListener2SelectorPresenterresenter.doInsertData2Room();
                    mDialog.show();
                }else{
                    mListener2SelectorPresenterresenter.requestData();
                }
            }
        });

        tishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPop.showAtLocation(v, Gravity.CENTER,Gravity.FILL_HORIZONTAL,Gravity.FILL_VERTICAL);
                mPop.setOnZhanClickListener(new UnKnowPopwindow.onZhanClickListener() {
                    @Override
                    public void onZhanClick() {
                        if (mCurrentIndex>=mMaxRange){
                            mDialog.show();
                            mListener2SelectorPresenterresenter.doInsertData2Room();
                        }else{
                            mListener2SelectorPresenterresenter.requestData();
                        }
                        mPop.dismiss();
                    }
                });
            }
        });
    }

    private void onCorrectChange(Button view,ImageView iv){
        view.setBackground(mDrawableSuccess);
        iv.setImageResource(R.mipmap.ic_chinese_correct);
        mTts.speak(view.getText().toString(),TextToSpeech.QUEUE_FLUSH,null);

        //判断答题次数
        if (mCurrentIndex >= mMaxRange){

            //插入 --> 数据库
            mListener2SelectorPresenterresenter.doInsertData2Room();

            mDialog.show();
        }else {
            mListener2SelectorPresenterresenter.requestData();
        }


        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                iv.setImageDrawable(null);
                view.setBackground(mDrawableNormal);
            }
        },300);


    }

    private void onErrorChange(View view,ImageView iv){
        view.setBackground(mDrawableError);
        iv.setImageResource(R.mipmap.ic_chinese_error);

        view.postDelayed(new Runnable() {
            @Override
            public void run() {
                view.setBackground(mDrawableNormal);
                iv.setImageDrawable(null);
            }
        },300);
    }


    @Override
    public void onInit(int status) {
        if (status== TextToSpeech.SUCCESS) {
            mTts.setLanguage(Locale.ENGLISH);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        if (mListener2SelectorPresenterresenter != null) {
            mListener2SelectorPresenterresenter.unRegesiterView(this);
        }
        if (mPop != null) {
            mPop.dismiss();
        }
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    @Override
    public void showCorrect(String correctEnlish) {
        Message msg=Message.obtain();
        msg.what=CASE_SHOW_CORRECT_MSG;
        msg.obj=correctEnlish;
        mListenerHandler.sendMessage(msg);
        LogUtil.d(TAG,"correc --> "+correctEnlish);
    }

    @Override
    public void showError(String correctChinese,String error1, String error2, String error3, String error4) {
        Message msg=Message.obtain();
        msg.what=CASE_SHOW_ERROR_MSG;
        Bundle bundle=new Bundle();
        bundle.putString("correctChinese",correctChinese);
        bundle.putString("error1",error1);
        bundle.putString("error2",error2);
        bundle.putString("error3",error3);
        bundle.putString("error4",error4);
        msg.setData(bundle);
        mListenerHandler.sendMessage(msg);
        LogUtil.d(TAG,correctChinese+":"+error1+":"+error2+":"+error3+":"+error4);
    }

    @Override
    public void getRange(int maxRang) {
        this.mMaxRange=maxRang;
        tvFinalyProgress.setText(maxRang+"");
    }

    @Override
    public void getCurrentIndex(int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mCurrentIndex=index;
                tvCurrentProgress.setText(index+" /  ");
            }
        });
    }

    @Override
    public void showAllData(List<Words> mWords,int index) {
        if (mWords != null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mPop.getRange(String.valueOf(mMaxRange));
                    mPop.getData(mWords,index);
                }
            });
        }
    }
}
