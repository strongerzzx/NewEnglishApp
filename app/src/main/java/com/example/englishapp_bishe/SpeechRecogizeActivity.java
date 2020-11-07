package com.example.englishapp_bishe;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import entirys.Words;
import interfaces.ISpeechCallback;
import presenters.SpeechPresent;
import utils.LogUtil;
import utils.SpeechView;
import views.OverDialog;
import views.UnKnowPopwindow;


//TODO:语音识别
public class SpeechRecogizeActivity extends AppCompatActivity implements ISpeechCallback {

    private static final int TYPE_CORRECT_WORDS= 1;
    private static final int TYPE_CORRECT_TRANSLATE= 2;
    private static final int TYPE_CORRECT_RECONGIZE= 3;
    private static final int TYPE_CORRECT_POPWINDOW= 4;
    private static final String TAG = "SpeechRecogizeActivity";



    @BindView(R.id.speech_finish)
    public ImageView mIvFinish;
    @BindView(R.id.speech_content)
    public TextView mTvContent;
    @BindView(R.id.speech_view)
    public SpeechView mView;
    @BindView(R.id.speech_fayin)
    public TextView mTvFayin;
    @BindView(R.id.speech_chinese)
    public TextView mTvChinese;
    @BindView(R.id.speech_recongize)
    public TextView mTvRecongize;
    @BindView(R.id.speech_tishi)
    public ImageView mIvTishi;

    private SpeechPresent mSpeechPresent;
    private SpeechHandler mSpeechHandler;
    private String mCurrentRecongizeResult;//当前正在识别的单词
    private UnKnowPopwindow mPopwindow;
    private OverDialog mDialog;
    private int mFinishIndex=0;


    public static class SpeechHandler extends Handler{
        private WeakReference<SpeechRecogizeActivity> mAct;
        private String mCorrectWord="";
        private String mFayin;

        public SpeechHandler(SpeechRecogizeActivity mSpeech) {
            mAct = new WeakReference<SpeechRecogizeActivity>(mSpeech);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            SpeechRecogizeActivity speechAct = mAct.get();
            super.handleMessage(msg);
            if (speechAct!=null){
                switch (msg.what) {
                    case TYPE_CORRECT_WORDS:
                        Bundle data = msg.getData();
                        mCorrectWord = data.getString("correctWord");
                        mFayin = data.getString("fayin");
                        break;

                    case TYPE_CORRECT_TRANSLATE:
                        String chinese = (String) msg.obj;
                        speechAct.mTvChinese.setText(chinese);
                        break;

                    case TYPE_CORRECT_RECONGIZE:
                        Bundle bundle = msg.getData();
                        String recongize = bundle.getString("result");
                        LogUtil.d(TAG,"mCorrectWord -->"+mCorrectWord);
                        LogUtil.d(TAG,"recongize -->"+recongize);
                        if (recongize.trim().equals(mCorrectWord)){
                            speechAct.mTvRecongize.setText(recongize);

                            //延迟200ms 请求新数据
                            speechAct.mSpeechHandler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    speechAct.mFinishIndex++;
                                    speechAct.mTvRecongize.setText("_ _ _ _");

                                    if (speechAct.mFinishIndex>5){
                                        speechAct.mDialog.show();
                                    }else{
                                        speechAct.mSpeechPresent.doQueryData();
                                        speechAct.mTvContent.setVisibility(View.INVISIBLE);
                                        speechAct.mTvFayin.setVisibility(View.INVISIBLE);
                                    }
                                }
                            },200);

                        }else{
                            speechAct.mTvContent.setVisibility(View.VISIBLE);
                            speechAct.mTvFayin.setVisibility(View.VISIBLE);
                            speechAct.mTvContent.setText(mCorrectWord);
                            speechAct.mTvFayin.setText(mFayin);
                        }

                        break;
                }
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recogize);

        ButterKnife.bind(this);

        mSpeechHandler = new SpeechHandler(this);
        mSpeechPresent = SpeechPresent.getPresent();
        mSpeechPresent.regesiterView(this);
        mSpeechPresent.doQueryData();//获取数据
        mPopwindow = new UnKnowPopwindow(this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);

        mDialog = new OverDialog(this);
        initEvent();
    }

    private void initEvent() {
        mIvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mView.setOnPressCallback(new SpeechView.onPressCallback() {
            @Override
            public void onStartRecord() {

                //计算分贝
                mSpeechPresent.doCalSoundfb();

                //语音识别
                mSpeechPresent.doSpeechRecogize();
            }

            @Override
            public void onStopRecord() {

            }

            @Override
            public void onCancleRecord() {

            }
        });


        mIvTishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopwindow.showAtLocation(v, Gravity.CENTER,Gravity.FILL_HORIZONTAL,Gravity.FILL_VERTICAL);
            }
        });

    }

    //识别出的单词
    @Override
    public void showRecongize(String result) {

        Message msg=Message.obtain();
        msg.what=TYPE_CORRECT_RECONGIZE;
        Bundle bundle=new Bundle();
        bundle.putString("result",result.toLowerCase());
        msg.setData(bundle);
        mSpeechHandler.sendMessage(msg);

        this.mCurrentRecongizeResult=result;

        LogUtil.d(TAG,"result --> "+result.toLowerCase());
    }

    @Override
    public void showFenBei(double fb) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //显示不同的分贝
                mView.showLevel((int) fb);
            }
        });
        LogUtil.d(TAG,"fb --> "+fb);

    }

    //答案
    @Override
    public void showCorrectWord(String correctWord, String fayin) {

        if (correctWord != null && fayin!=null) {
            Message msg=Message.obtain();
            msg.what=TYPE_CORRECT_WORDS;
            Bundle bundle=new Bundle();
            bundle.putString("correctWord",correctWord);
            bundle.putString("fayin",fayin);
            msg.setData(bundle);
            mSpeechHandler.sendMessage(msg);
        }

        LogUtil.d(TAG,"correctWord --> "+correctWord+":"+fayin);
    }

    //按钮点击 --> 弹出Pop
    @Override
    public void showAllData(List<Words> mList, int anInt) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPopwindow.getData(mList,anInt);
                mPopwindow.setOnZhanClickListener(new UnKnowPopwindow.onZhanClickListener() {
                    @Override
                    public void onZhanClick() {
                        mSpeechPresent.doQueryData();
                        mTvFayin.setVisibility(View.INVISIBLE);
                        mTvContent.setVisibility(View.INVISIBLE);
                        mPopwindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    public void showWordChinese(String translate) {
        Message msg=Message.obtain();
        msg.what=TYPE_CORRECT_TRANSLATE;
        msg.obj=translate;
        mSpeechHandler.sendMessage(msg);
    }

    @Override
    public void showTimes(int currentTime, int maxTime) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSpeechPresent != null) {
            mSpeechPresent.onDestrioyRecord();
            mSpeechPresent.unRegesiterView(this);
        }
        if (mSpeechHandler != null) {
            mSpeechHandler.removeCallbacksAndMessages(null);
        }
    }
}