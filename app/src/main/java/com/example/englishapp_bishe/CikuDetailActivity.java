package com.example.englishapp_bishe;

import android.graphics.Color;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.text.TextUtils;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.example.englishapp_bishe.databinding.DetailWordTextLayoutBinding;

import java.util.Locale;

import entirys.Words;
import interfaces.IDeatilCallback;
import presenters.DetailPresent;
import utils.LogUtil;

public class CikuDetailActivity extends AppCompatActivity implements IDeatilCallback, TextToSpeech.OnInitListener {

    private static final String TAG = "CikuDetailActivity";
    private DetailPresent mDetailPresent;
    private DetailWordTextLayoutBinding mBinding;
    private TextToSpeech mTts;
    private String mHeadWord;
    private String mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this,R.layout.detail_word_text_layout);

        mTts = new TextToSpeech(this,this);
        mDetailPresent = DetailPresent.getPresent();
        mDetailPresent.regesiterView(this);

        mDetailPresent.getData();

        //设置toolBar
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.d(TAG,"onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mTts!=null) {
            mTts.stop();
        }
    }

    private void initEvent() {

        mBinding.collToolLayout.setExpandedTitleColor(Color.WHITE);
        mBinding.collToolLayout.setCollapsedTitleTextColor(Color.BLACK);

        //结束
        mBinding.toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        //语音播报  -->单词
        mBinding.detailFaYinLound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTts != null && !mTts.isSpeaking()) {
                    mTts.speak(mHeadWord,TextToSpeech.QUEUE_FLUSH,null);
                }
            }
        });

        //语音播报 --> 例句
        mBinding.detailLiJuLound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTts.speak(mContent,TextToSpeech.QUEUE_FLUSH,null);
            }
        });

    }

    @Override
    public void showData(Words words) {
        if (words != null) {
            mHeadWord = words.getHeadWord();
            mContent = words.getContent();
            mBinding.collToolLayout.setTitle(mHeadWord);
            mBinding.detailHeadWord.setText(mHeadWord);
            mBinding.detailAdjWord.setText(words.getSimpleTran());
            mBinding.detailAdvWords.setText(words.getTran());
            mBinding.detailFaYinUk.setText(words.getUkphone());
            mBinding.detailFaYinUs.setText(words.getUsphone());
            mBinding.detailJinyiciWord1.setText(words.getSynWord1());
            mBinding.detailLiJuChinese.setText(words.getCnContent());
            mBinding.detailLiJuEnglish.setText(mContent);
            mBinding.detailTransOtherWord.setText(words.getTranOther());
            mBinding.detailPhrasesChinese.setText(words.getPcontent());
            mBinding.detailPhresesEnglish.setText(words.getPcn());
            if (TextUtils.isEmpty(words.getPicture())){
                Glide.with(this).load(R.mipmap.ic_glide_error).into(mBinding.detailLiJuPic);
                Glide.with(this).load(R.mipmap.ic_glide_error).into(mBinding.appBarImage);
            }else{
                Glide.with(this).load(words.getPicture()).into(mBinding.detailLiJuPic);
                Glide.with(this).load(words.getPicture()).into(mBinding.appBarImage);
            }


            //TODO:点击图片 会有一个缩略的动画 并且只显示此图片
            mBinding.appBarImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    getSupportFragmentManager().beginTransaction().replace(R.id.detail_thumb_pic_layout,new ThumbPicFragment()).commit();
                }
            });

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDetailPresent != null) {
            mDetailPresent.unRegesiterView(this);
        }
        if (mTts != null && mTts.isSpeaking()) {
            mTts.shutdown();
        }
    }

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            mTts.setLanguage(Locale.ENGLISH);
        }
    }
}
