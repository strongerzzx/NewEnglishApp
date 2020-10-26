package com.example.englishapp_bishe;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import entirys.Words;
import interfaces.IGameCallback;
import presenters.GamePresenter;
import utils.LogUtil;


//TODO：实现这个游戏
public class GameActivity extends AppCompatActivity implements IGameCallback {

    private static final String TAG = "GameActivity";
    private static final int CASE_SHOW_TITLE = 1;
    private static final int CASE_SHOW_CHOOSE = 2;

    @BindView(R.id.game_finish)
    public ImageView mGameFinish;
    @BindView(R.id.game_content)
    public TextView mGameContent;
    @BindView(R.id.game_time_cut_down)
    public TextView mGameCutDowm;
    @BindView(R.id.game_destion)
    public ImageView mGameDestion;
    @BindView(R.id.game_path)
    public ProgressBar mGamePath;
    @BindView(R.id.game_rabbit)
    public ImageView mGameRabbit;
    @BindView(R.id.game_tortoise)
    public ImageView mGametortoise;
    @BindView(R.id.game_first_daan)
    public Button mGameFirst;
    @BindView(R.id.game_second_daan)
    public Button mGameSecond;
    @BindView(R.id.game_third_daan)
    public Button mGameThird;
    @BindView(R.id.game_fourth_daan)
    public Button mGameFourth;
    private GamePresenter mGamePresenter;
    private GameHandler mGHandler;
    private CountDownTimer mDownTimer;
    private String mCurrentCorrect;
    private int rabbitSpeed =3;
    private int tortoiseSpeed =5;

    public class GameHandler extends Handler{
        private WeakReference<GameActivity> mGameActivity;

        public GameHandler(GameActivity gameActivity) {
            mGameActivity=new WeakReference<GameActivity>(gameActivity);
        }

        @Override
        public void handleMessage(@NonNull Message msg) {
            GameActivity gameActivity = mGameActivity.get();
            super.handleMessage(msg);
            if (gameActivity!=null){
                switch (msg.what) {
                    case CASE_SHOW_TITLE:
                        String title = (String) msg.obj;
                        gameActivity.mGameContent.setText(title);
                        break;
                    case CASE_SHOW_CHOOSE:
                        Bundle data = msg.getData();
                        int first=0,second=1,third=2,fourth=3;
                        Random random=new Random();
                        int anInt = random.nextInt(4);
                        String correct = data.getString("correct");
                        String error1 = data.getString("error1");
                        String error2 = data.getString("error2");
                        String error3 = data.getString("error3");
                        String error4 = data.getString("error4");
                        gameActivity.mGameFirst.setText(anInt==first?correct:error1);
                        gameActivity.mGameSecond.setText(anInt==second?correct:error2);
                        gameActivity.mGameThird.setText(anInt==third?correct:error3);
                        gameActivity.mGameFourth.setText(anInt==fourth?correct:error4);

                        mCurrentCorrect=correct;

                        break;
                }
            }
        }
    }
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        //强制横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        ButterKnife.bind(this);

        mGHandler = new GameHandler(this);

        mGamePresenter = GamePresenter.getPresenter();
        mGamePresenter.regesiterView(this);
        mGamePresenter.doQueryData();//获取数据

        initEvent();
    }

    private int rabbitTemp =0;
    private Runnable rabbitRun =new Runnable() {
        @Override
        public void run() {
            rabbitTemp +=10;

            ObjectAnimator animator=ObjectAnimator.ofFloat(mGameRabbit,"translationX", rabbitSpeed *10+ rabbitTemp);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float) animation.getAnimatedValue();
                    mGamePath.setSecondaryProgress((int) animatedValue);
                }
            });
            animator.setDuration(200);
            animator.start();

            mGHandler.postDelayed(this,300);

            int secondProgress = mGamePath.getSecondaryProgress();

            if (secondProgress==mGamePath.getMax()){
                LogUtil.d(TAG,"恭喜兔子获得冠军");

                mDownTimer.cancel();
                mGHandler.removeCallbacksAndMessages(null);

                Intent intent=new Intent(GameActivity.this,GameWinActivity.class);
                intent.putExtra("win","恭喜兔子获得冠军");
                startActivity(intent);
                finish();
            }

            LogUtil.d(TAG,"secondProgress --> "+secondProgress);
        }
    };

    private int tortiseTemp=0;
    private Runnable tortoiseRun =new Runnable() {
        @Override
        public void run() {
            tortiseTemp+=10;
            ObjectAnimator animator=ObjectAnimator.ofFloat(mGametortoise,"translationX", tortoiseSpeed *10+ tortiseTemp);
            animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    float animatedValue = (float) animation.getAnimatedValue();
                    mGamePath.setProgress((int) animatedValue);
                }
            });

            animator.setDuration(200);
            animator.start();
            mGHandler.postDelayed(this,300);

            int progress = mGamePath.getProgress();
            if (progress>=mGamePath.getMax()){
                LogUtil.d(TAG,"恭喜乌龟获得冠军");

                mDownTimer.cancel();
                mGHandler.removeCallbacksAndMessages(null);

                Intent intent=new Intent(GameActivity.this,GameWinActivity.class);
                intent.putExtra("win","恭喜乌龟获得冠军");
                startActivity(intent);
                finish();
            }

            LogUtil.d(TAG,"progress --> "+progress);
        }
    };

    private void initEvent() {

        mGamePath.setMax(1000);

        //一开始就在运动  点击按键 ——> 只是改变速度
        new Thread(new Runnable() {
            @Override
            public void run() {
                mGHandler.post(rabbitRun);//兔子
                mGHandler.post(tortoiseRun);//乌龟
            }
        }).start();


        doCutDownTimer();


        mGameFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrectEvent(mGameFirst);
            }
        });

        mGameSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrectEvent(mGameSecond);
            }
        });

        mGameThird.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrectEvent(mGameThird);
            }
        });

        mGameFourth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCorrectEvent(mGameFourth);
            }
        });
    }

    //判断是否相等
    private void onCorrectEvent(Button btn) {
        String currentChoose = btn.getText().toString();
        if (currentChoose.equals(mCurrentCorrect)){
            mGamePresenter.doQueryData();

            //加快乌龟的速度
            tortoiseSpeed+=1;

        }else{
            rabbitSpeed +=1;
        }
    }

    private void doCutDownTimer() {
        //每个题目 都5s的答题
        mDownTimer = new CountDownTimer(5*1000+380,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mGameCutDowm.setText("剩余: "+millisUntilFinished/1000+"秒");
            }

            @Override
            public void onFinish() {
                //5s内没答出来 --> 就下一个
                mGamePresenter.doQueryData();

                //并且重新倒数
                mDownTimer.start();
            }
        };
        mDownTimer.start();
    }

    @Override
    public void showTitle(String title) {
        Message msg = Message.obtain();
        msg.what=CASE_SHOW_TITLE;
        msg.obj=title;
        mGHandler.sendMessage(msg);
    }

    @Override
    public void showCorrect(String correct) {
        System.out.println(Thread.currentThread().getName());
    }

    @Override
    public void showCorrectAndError(String correct, String error1, String error2, String error3, String error4) {
        Bundle bundle=new Bundle();
        bundle.putString("correct",correct);
        bundle.putString("error1",error1);
        bundle.putString("error2",error2);
        bundle.putString("error3",error3);
        bundle.putString("error4",error4);
        Message msg= Message.obtain();
        msg.what=CASE_SHOW_CHOOSE;
        msg.setData(bundle);
        mGHandler.sendMessage(msg);

        LogUtil.d(TAG,"correct:"+correct+":"+error1+":"+error2+":"+error3+":"+error4);
    }

    @Override
    public void showWinnerWordList(List<Words> mList) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mGamePresenter != null) {
            mGamePresenter.unRegesiterView(this);
        }
        if (mDownTimer != null) {
            mDownTimer.cancel();
        }
        if (mGHandler != null) {
            mGHandler.removeCallbacksAndMessages(null);
        }
    }
}