package views;

import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.speech.tts.TextToSpeech;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.englishapp_bishe.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import entirys.Words;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/11 14:16
 * <p>
 * 作用： xxxx
 */
public class UnKnowPopwindow extends PopupWindow implements TextToSpeech.OnInitListener {

    private static final String TAG = "UnKnowPopwindow";
    private final View mInflate;
    private TextView mEnglish;
    private TextView mFayinUk;
    private TextView mFayinUS;
    private ImageView mWordLound;
    private TextView mSimpleTra;
    private TextView mComleteTra;
    private TextView mLiguEnglish;
    private ImageView mLijuLound;
    private TextView mLiguChinese;
    private ImageView mLijuPic;
    private TextView mTranOther;
    private TextView mPhresesEnglish;
    private TextView mPhresesChinese;
    private TextView mJinyiWord;
    private TextView mZhan;
    private TextView mResolve;
    private Context mContext;

    private List<Words> mProgressWords=new ArrayList<>();
    private final TextToSpeech mTts;
    private onZhanClickListener mOnZhanClickListener;
    private String mCurrentRange;

    public UnKnowPopwindow(Context context, int width, int height) {
        super(context);

        mContext=context;

        mInflate = LayoutInflater.from(context).inflate(R.layout.unknow_pop_window, null);

        this.setWidth(width);
        this.setHeight(height);
        this.setContentView(mInflate);
        this.setAnimationStyle(R.style.CustonUnknowPop);


        this.setFocusable(true);
        this.setBackgroundDrawable(new BitmapDrawable());


        mTts = new TextToSpeech(context,this);

        initView();

        initEvent();

    }



    private void initView() {
        mEnglish = mInflate.findViewById(R.id.unknow_head_word);
        mFayinUk = mInflate.findViewById(R.id.unknow_fa_yin_uk);
        mFayinUS = mInflate.findViewById(R.id.unknow_fa_yin_us);
        mWordLound = mInflate.findViewById(R.id.unknow_fa_yin_lound);
        mSimpleTra = mInflate.findViewById(R.id.unknow_adv_words);
        mComleteTra = mInflate.findViewById(R.id.unknow_adj_word);
        mLiguEnglish = mInflate.findViewById(R.id.unknow_li_ju_english);
        mLijuLound = mInflate.findViewById(R.id.unknow_li_ju_lound);
        mLiguChinese = mInflate.findViewById(R.id.unknow_li_ju_chinese);
        mLijuPic = mInflate.findViewById(R.id.unknow_li_ju_pic);
        mTranOther = mInflate.findViewById(R.id.unknow_trans_other_word);
        mPhresesEnglish = mInflate.findViewById(R.id.unknow_phreses_english);
        mPhresesChinese = mInflate.findViewById(R.id.unknow_phrases_chinese);
        mJinyiWord = mInflate.findViewById(R.id.unknow_jinyici_word_1);
        mZhan = mInflate.findViewById(R.id.unknow_zhan);
        mResolve = mInflate.findViewById(R.id.unknow_resolve);
    }

    private void initEvent() {

        //继续答题 --> 则关闭pop
        mResolve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAnimationStyle(R.style.CustonUnknowPop);
                dismiss();
            }
        });


        //斩 --> 则下一个

        mZhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnZhanClickListener.onZhanClick();
               // setAnimationStyle(R.style.CustonUnknowPopLeft);
            }
        });
    }

    public void getData(List<Words> bookWords, int currentProgress) {

        mProgressWords.clear();
        if (bookWords != null) {
            mProgressWords.addAll(bookWords);

            //获取数据
            Words words = mProgressWords.get(currentProgress);
            mEnglish.setText(words.getHeadWord());
            mFayinUk.setText(words.getUkphone());
            mFayinUS.setText(words.getUsphone());
            mComleteTra.setText(words.getTran());
            mSimpleTra.setText(words.getSimpleTran());
            mPhresesEnglish.setText(words.getPcontent());
            mPhresesChinese.setText(words.getPcn());
            mJinyiWord.setText(words.getSynWord1());
            mLiguEnglish.setText(words.getContent());
            mLiguChinese.setText(words.getCnContent());
            Glide.with(mContext).load(words.getPicture()).into(mLijuPic);

            //TTS --> 单词
            mWordLound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTts.speak(words.getHeadWord(),TextToSpeech.QUEUE_FLUSH,null);
                }
            });

            //TTS -->例句
            mLijuLound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTts.speak(words.getContent(),TextToSpeech.QUEUE_FLUSH,null);
                }
            });
        }
    }

    public void updateProgress(int currentProgress) {
        if (currentProgress>0){
            Words words = mProgressWords.get(currentProgress);

            mEnglish.setText(words.getHeadWord());
            mFayinUk.setText(words.getUkphone());
            mFayinUS.setText(words.getUsphone());
            mComleteTra.setText(words.getTran());
            mSimpleTra.setText(words.getSimpleTran());
            mPhresesEnglish.setText(words.getPcontent());
            mPhresesChinese.setText(words.getPcn());
            mJinyiWord.setText(words.getSynWord1());
            mLiguEnglish.setText(words.getContent());
            mLiguChinese.setText(words.getCnContent());
            Glide.with(mContext).load(words.getPicture()).into(mLijuPic);


            //TTS --> 单词
            mWordLound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTts.speak(words.getHeadWord(),TextToSpeech.QUEUE_FLUSH,null);
                }
            });

            //TTS -->例句
            mLijuLound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mTts.speak(words.getContent(),TextToSpeech.QUEUE_FLUSH,null);
                }
            });
        }

        LogUtil.d(TAG,"当前进度 -->"+currentProgress);
        LogUtil.d(TAG,"总进度 --> "+mCurrentRange);
    }

    @Override
    public void onInit(int status) {
        if (status==TextToSpeech.SUCCESS){
            mTts.setLanguage(Locale.ENGLISH);
        }
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mTts != null && mTts.isSpeaking()) {
            mTts.shutdown();
        }
    }



    public void setOnZhanClickListener(onZhanClickListener onZhanClickListener) {
        mOnZhanClickListener = onZhanClickListener;
    }

    public void getRange(String range) {
        this.mCurrentRange=range;
    }

    public interface onZhanClickListener{
        void onZhanClick();
    }
}
