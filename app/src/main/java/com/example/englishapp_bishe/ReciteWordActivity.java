package com.example.englishapp_bishe;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ReciteWordActivity extends AppCompatActivity {

    private TextView mTvProgress;
    private TextView mTvEnglish;
    private TextView mTvFayin;
    private TextView mTvEnglishOther;
    private TextView mTvKnow;
    private TextView mTvUnkonw;
    private TextView mTvMohu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite_word);

        initView();

        initEvent();
    }

    private void initEvent() {
        //TODO:显示当前进度

        //TODO:显示当前英语

        //TODO:显示当前英语发音

        //TODO:明白直接下一个单词

        //TODO:模糊显示单词英语翻译

        //TODO:不明白弹出单词的详情页
    }

    private void initView() {
        mTvProgress = findViewById(R.id.recite_progress);
        mTvEnglish = findViewById(R.id.recite_english);
        mTvFayin = findViewById(R.id.recite_fayin);
        mTvEnglishOther = findViewById(R.id.recite_english_other);
        mTvKnow = findViewById(R.id.tv_recite_know);
        mTvUnkonw = findViewById(R.id.tv_recitye_unkonow);
        mTvMohu = findViewById(R.id.tv_recitye_mohu);
    }
}
