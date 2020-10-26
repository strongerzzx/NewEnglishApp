package com.example.englishapp_bishe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.ISpeechCallback;
import presenters.SpeechPresent;
import utils.SpeechView;


//TODO:语音识别
public class SpeechRecogizeActivity extends AppCompatActivity implements ISpeechCallback {

    private static final int TYPE_START_AUDIO= 100;
    private static final String TAG = "SpeechRecogizeActivity";


    @BindView(R.id.speech_finish)
    public ImageView mIvFinish;
    @BindView(R.id.speech_text)
    public Button mText;
    @BindView(R.id.speech_content)
    public TextView mTvContent;
    @BindView(R.id.speech_view)
    public SpeechView mView;
    private SpeechPresent mSpeechPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_speech_recogize);

        ButterKnife.bind(this);
        mSpeechPresent = SpeechPresent.getPresent();
        mSpeechPresent.regesiterView(this);


        initEvent();
    }

    private void initEvent() {
        mIvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        mText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                mText.setText("请开始对话");


                return false;
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
    }

    @Override
    public void showRecongize(String result) {
        mTvContent.setText(result);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSpeechPresent != null) {
            mSpeechPresent.unRegesiterView(this);
        }
    }
}