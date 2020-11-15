package com.example.englishapp_bishe;

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
import interfaces.ISpellWordCallback;
import presenters.SpellWordPresenter;
import utils.LogUtil;
import views.OverDialog;
import views.UnKnowPopwindow;

public class SpellWordActivity extends AppCompatActivity implements ISpellWordCallback, TextToSpeech.OnInitListener {

    public static final int CASE_SHOW_SUBCONTENT=1;
    public static final int CASE_SHOW_SUBTRA=2;
    public static final int CASE_SHOW_BTN_SUB=3;
    public static final int CASE_SHOW_CORRECT_ENGLISH=4;
    private static final String TAG = "SpellWordActivity";
    @BindView(R.id.spell_finish)
    public ImageView finish;
    @BindView(R.id.spell_content)
    public TextView content;
    @BindView(R.id.spell_content_tran)
    public TextView tran;
    @BindView(R.id.spell_first)
    public Button first;
    @BindView(R.id.spell_second)
    public Button second;
    @BindView(R.id.spell_third)
    public Button third;
    @BindView(R.id.spell_fourth)
    public Button fourth;
    @BindView(R.id.spell_pass)
    public TextView pass;
    @BindView(R.id.spell_tishi)
    public ImageView tishi;
    @BindView(R.id.spell_shengyin)
    public ImageView syin;
    @BindView(R.id.spell_current_prorgess)
    public TextView tvCurrentProg;
    @BindView(R.id.spell_finaly_profress)
    public TextView tvFinalyProg;

    private SpellWordPresenter mSpellPresenter;
    private SpellHandler mSpellHandler;
    private TextToSpeech mTts;

    private String mCurrentSub;
    private String mCurrentContent;
    private int mCurrentIndex;
    private int mMaxRange;
    private OverDialog mDialog;
    private UnKnowPopwindow mPop;

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            mTts.setLanguage(Locale.ENGLISH);
        }
    }


    public static class SpellHandler extends Handler{
        private  WeakReference<SpellWordActivity> mAct;

        public SpellHandler(SpellWordActivity activity) {
            mAct = new WeakReference<SpellWordActivity>(activity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            SpellWordActivity activity = mAct.get();
            super.handleMessage(msg);
            if (activity != null) {
                switch (msg.what) {
                    case CASE_SHOW_SUBTRA:
                        String tra = (String) msg.obj;
                        activity.tran.setText(tra);
                        break;
                    case CASE_SHOW_SUBCONTENT:
                        String content = (String) msg.obj;
                        activity.mCurrentContent=content;
                        activity.content.setText(content);
                        break;

                    case CASE_SHOW_BTN_SUB:
                        Bundle bundle = msg.getData();
                        String correct = bundle.getString("correct");
                        String error1 = bundle.getString("error1");
                        String error2 = bundle.getString("error2");
                        String error3 = bundle.getString("error3");
                        String error4 = bundle.getString("error4");
                        activity.mCurrentSub=correct;

                        int first=0,second=1,third=2,fourth=3;

                        Random random=new Random();
                        int index = random.nextInt(4);
                        activity.first.setText(first==index?correct:error1);
                        activity.second.setText(second==index?correct:error2);
                        activity.third.setText(third==index?correct:error3);
                        activity.fourth.setText(fourth==index?correct:error4);

                        break;
                }
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spell_word);

        ButterKnife.bind(this);
        mDialog = new OverDialog(this);
        mDialog.setActivity(this);

        mPop = new UnKnowPopwindow(this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        mPop.setClippingEnabled(false);

        mSpellPresenter = SpellWordPresenter.getPresenter();
        mSpellPresenter.regesiterView(this);
        mSpellPresenter.doRandomRange();
        mSpellPresenter.doClearIndex();
        mSpellPresenter.requestData();

        mTts = new TextToSpeech(this,this);

        mSpellHandler = new SpellHandler(this);

        initEvent();
    }


    private Runnable replaceTask =new Runnable() {
        @Override
        public void run() {
            String newContent = mCurrentContent.replace("_", mCurrentSub);
            content.setText(newContent);
        }
    };

    private void initEvent() {

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (first.getText().equals(mCurrentSub)) {

                    onCorrect();

                }else{
                    LogUtil.d(TAG,"回答错误");
                }
            }
        });

        second.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (second.getText().equals(mCurrentSub)) {

                   onCorrect();

                }else{
                    LogUtil.d(TAG,"回答错误");
                }
            }
        });

        third.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (third.getText().equals(mCurrentSub)) {

                  onCorrect();

                }else{
                    LogUtil.d(TAG,"回答错误");
                }
            }
        });

        fourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (fourth.getText().equals(mCurrentSub)) {

                   onCorrect();

                }else{
                    LogUtil.d(TAG,"回答错误");
                }
            }
        });

        pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mCurrentIndex>=mMaxRange){
                    mSpellPresenter.doInsert2Room();
                    mDialog.show();
                }else {
                    mSpellPresenter.requestData();
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
                            mSpellPresenter.doInsert2Room();
                            mDialog.show();
                        }else {
                            mSpellPresenter.requestData();
                        }
                        mPop.dismiss();
                    }
                });
            }
        });
    }

    private void onCorrect() {
        if (mCurrentIndex>=mMaxRange){
            mSpellPresenter.doInsert2Room();
            mDialog.show();
        }else{
            mSpellHandler.post(replaceTask);
            mSpellHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mSpellPresenter.requestData();
                }
            },500);
        }
    }

    @Override
    public void showCorrentWord(String ernglish) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                syin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mTts.speak(ernglish,TextToSpeech.QUEUE_FLUSH,null);
                    }
                });
            }
        });
        LogUtil.d(TAG,"ernglish --> "+ernglish);
    }

    @Override
    public void showCorrectTra(String tra) {
        Message msg=Message.obtain();
        msg.what=CASE_SHOW_SUBTRA;
        msg.obj=tra;
        mSpellHandler.sendMessage(msg);
        LogUtil.d(TAG,"tra --> "+tra);
    }

    @Override
    public void showStubContent(String subContent) {
        Message msg=Message.obtain();
        msg.what=CASE_SHOW_SUBCONTENT;
        msg.obj=subContent;
        mSpellHandler.sendMessage(msg);
        LogUtil.d(TAG,"subContent --> "+subContent);
    }

    @Override
    public void showStubErrorWord(String correct, String error1, String error2, String error3, String error4) {
        Message msg=Message.obtain();
        Bundle bundle=new Bundle();
        bundle.putString("correct",correct);
        bundle.putString("error1",error1);
        bundle.putString("error2",error2);
        bundle.putString("error3",error3);
        bundle.putString("error4",error4);
        msg.what=CASE_SHOW_BTN_SUB;
        msg.setData(bundle);
        mSpellHandler.sendMessage(msg);

        LogUtil.d(TAG,"correct :"+correct+":"+error1+":"+error2+error3+":"+error4);
    }

    @Override
    public void showAllWords(List<Words> mWords,int index) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mPop.getRange(String.valueOf(mMaxRange));
                mPop.getData(mWords,index);
            }
        });
    }

    @Override
    public void getMaxRange(int maxRange) {
        this.mMaxRange=maxRange;
        tvFinalyProg.setText(maxRange+"");
    }

    @Override
    public void getCurrentIndex(int currentIndex) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvCurrentProg.setText(currentIndex+" / ");
                mCurrentIndex=currentIndex;
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSpellPresenter != null) {
            mSpellPresenter.unRegesiterView(this);
        }
        if (mTts != null) {
            mTts.stop();
            mTts.shutdown();
        }
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }
}
