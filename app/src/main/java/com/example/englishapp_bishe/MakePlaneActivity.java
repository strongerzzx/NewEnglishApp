package com.example.englishapp_bishe;

import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import presenters.HomePresent;

public class MakePlaneActivity extends AppCompatActivity {

    private ImageView mFinishIv;
    private ImageView mStartIv;
    private EditText mInputRange;
    private TextView mBookName;
    private TextView mRangeNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_plane);

        initView();

        initEvent();
    }

    private void initEvent() {
        //结束当前页面
        mFinishIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //设置Hint
        String hint="请输入每日背单词量";
        SpannableString ss=new SpannableString(hint);
        AbsoluteSizeSpan ass=new AbsoluteSizeSpan(20,true);
        ss.setSpan(ass,0,ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mInputRange.setHint(new SpannableString(ss));

        //显示选择的词书名字
        mBookName.setText(HomePresent.getPresent().getCurrentBookTitle());

        //显示选择词书的单词范围
        mRangeNum.setText("0 ~ "+HomePresent.getPresent().getCurrentBookSize());

        //TODO:开始背单词
        mStartIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MakePlaneActivity.this,ReciteWordActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        mFinishIv = findViewById(R.id.make_plane_finish_iv);
        mStartIv = findViewById(R.id.make_plane_go);
        mInputRange = findViewById(R.id.make_plane_input_number);
        mBookName = findViewById(R.id.make_plane_current_book_name);
        mRangeNum = findViewById(R.id.make_plane_range_number);
    }
}
