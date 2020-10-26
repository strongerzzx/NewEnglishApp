package com.example.englishapp_bishe;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import interfaces.ITranslateCallback;
import presenters.TranslatePresenter;

public class OtherTranslateActivity extends AppCompatActivity implements ITranslateCallback {

    @BindView(R.id.translate_input)
    public EditText mEtInput;
    @BindView(R.id.translate_show)
    public TextView mTvShow;
    @BindView(R.id.translate_start)
    public Button mStart;
    @BindView(R.id.translate_finish)
    public ImageView mIvfinish;
    @BindView(R.id.translate_conver)
    public ImageView mIvConver;
    @BindView(R.id.translate_src)
    public TextView mTvSrc;
    @BindView(R.id.translate_dest)
    public TextView mTvDest;


    private TranslatePresenter mTranslatePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_translate);

        ButterKnife.bind(this);


        mTranslatePresenter = TranslatePresenter.getPresenter();
        mTranslatePresenter.regesiterView(this);

        initEvent();
    }

    private void initEvent() {
        mTvShow.setText("");


        //点击切换src 和 dest 互换
        mIvConver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTranslatePresenter.doExchangeSrc(mTvSrc.getText().toString(),mTvDest.getText().toString());
            }
        });

        mIvfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //根据选择的语种 --> 开始翻译
        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mTvSrc.getText().equals("英文")){
                    //把输入的数据给P
                    mTranslatePresenter.getData(mEtInput.getText().toString());
                    mTranslatePresenter.doMixSign();//生成签名
                    mTranslatePresenter.doTranslate("en","zh");
                }else{
                    mTranslatePresenter.getData(mEtInput.getText().toString());
                    mTranslatePresenter.doMixSign();//生成签名
                    mTranslatePresenter.doTranslate("zh","en");
                }
            }
        });

    }

    @Override
    public void showTranslateResult(String result) {
        mTvShow.setText(result);

    }

    @Override
    public void showChangeName(String src, String dest) {
        mTvSrc.setText(src);
        mTvDest.setText(dest);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mTranslatePresenter != null) {
            mTranslatePresenter.unRegesiterView(this);
        }
    }
}