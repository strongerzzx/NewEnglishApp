package com.example.englishapp_bishe;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GameWinActivity extends AppCompatActivity {

    private static final String TAG = "GameWinActivity";

    @BindView(R.id.winner_again)
    public TextView mAgain;
    @BindView(R.id.winner_name)
    public TextView mName;
    @BindView(R.id.winner_exit)
    public TextView mExit;
    @BindView(R.id.winner_see_words)
    public TextView mSeeWords;
    @BindView(R.id.winner_words_content)
    public FrameLayout wordsLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_win);

        ButterKnife.bind(this);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        Intent intent = getIntent();
        String winName = intent.getStringExtra("win");
        mName.setText(winName);

        initEvent();
    }

    private void initEvent() {
        mAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GameWinActivity.this,GameActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GameWinActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mSeeWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(GameWinActivity.this,WinnerWordfsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}