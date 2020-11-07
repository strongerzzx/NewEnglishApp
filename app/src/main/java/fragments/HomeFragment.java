package fragments;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.englishapp_bishe.CikuActivity;
import com.example.englishapp_bishe.MakePlaneActivity;
import com.example.englishapp_bishe.R;
import com.example.englishapp_bishe.SearchActivity;
import com.example.englishapp_bishe.StudyProgressActivity;

import interfaces.ICanClickRecite;
import interfaces.IHomeCallback;
import presenters.HomePresent;
import utils.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements IHomeCallback {

    private static final String TAG = "HomeFragment";
    private ImageView mSingleIv;
    private Button mBtnSearch;
    private Button mBtnCiku;
    private Button mBtnStart;
    private TextView mTvEnglish;
    private TextView mTvChinese;
    private HomePresent mHomePresent;
    private String currentEnglish;
    private String currentChinese;
    private TextView mBookName;
    private ImageView mIvUser;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_home, container, false);


        mHomePresent = HomePresent.getPresent();
        mHomePresent.regesiterHomeView(this);
        mHomePresent.queryWords();

        initView(inflate);


        return inflate;
    }

    private void initEvent() {

        //跳转到学习进度页面
        mIvUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), StudyProgressActivity.class);
                startActivity(intent);
            }
        });

        //单个单词出现
        mSingleIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mHomePresent.queryWords();
                mTvChinese.setText(currentChinese);
                mTvEnglish.setText(currentEnglish);
            }
        });

        //进入搜索
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        //进入词库
        mBtnCiku.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CikuActivity.class);
                startActivity(intent);
            }
        });


        //每天完成任务后 --> 在点击背单词 没效果

        mHomePresent.canClickRecite(new ICanClickRecite() {
            @Override
            public void isClickRecite(boolean isCan) {

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println(Thread.currentThread().getName());
                        if (isCan){
                            mBtnStart.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(getActivity(), MakePlaneActivity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }
                            });
                        }else{
                            mBtnStart.setTextColor(Color.BLACK);
                            mBtnStart.setText("已完成今日任务");
                            Drawable finishDrawable = getResources().getDrawable(R.drawable.shape_home_start_bei__finish_normal);
                            finishDrawable.setBounds(0,0,finishDrawable.getMinimumWidth(),finishDrawable.getMinimumHeight());
                            mBtnStart.setBackgroundDrawable(finishDrawable);
                        }
                    }
                });

            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String currentBookTitle = mHomePresent.getCurrentBookTitle();
        mBookName.setText(currentBookTitle);
        LogUtil.d(TAG,"currentBookTitle --> "+currentBookTitle);
        initEvent();
    }

    private void initView(View inflate) {
        mBookName = inflate.findViewById(R.id.home_book_name);
        mSingleIv = inflate.findViewById(R.id.home_refresh_iv);
        mBtnSearch = inflate.findViewById(R.id.home_btn_search);
        mBtnCiku = inflate.findViewById(R.id.home_btn_ciku);
        mBtnStart = inflate.findViewById(R.id.home_btn_start_bei);
        mTvEnglish = inflate.findViewById(R.id.home_tv_english);
        mTvChinese = inflate.findViewById(R.id.home_tv_chinese);
        mIvUser = inflate.findViewById(R.id.home_user_iv);
    }

    @Override
    public void showSingle(String english, String chinese) {
        this.currentEnglish=english;
        this.currentChinese=chinese;
        LogUtil.d(TAG,"english -->"+english+"-->"+chinese);
    }

    @Override
    public void showBookName(String bookName) {
//        LogUtil.d(TAG,"bookName -->"+bookName);
//        if (bookName != null) {
//            mBookName.setText(bookName);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mHomePresent != null) {
            mHomePresent.unRegesiterHomeView(this);
        }
    }
}
