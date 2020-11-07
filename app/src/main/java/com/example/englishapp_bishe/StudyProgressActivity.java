package com.example.englishapp_bishe;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.dacer.androidcharts.LineView;
import interfaces.IStudyProgressCallback;
import presenters.StudyProgressPresenter;

public class StudyProgressActivity extends AppCompatActivity implements IStudyProgressCallback{

    private static final String TAG = "StudyProgressActivity";
    private StudyProgressPresenter mStudyProgresspresenter;

    @BindView(R.id.study_iv_finish)
    public ImageView mFinish;
    @BindView(R.id.line_view)
    public LineView mLineView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_progress);

        ButterKnife.bind(this);
        mStudyProgresspresenter = StudyProgressPresenter.getPresenter();
        mStudyProgresspresenter.regesiterView(this);
        mStudyProgresspresenter.doQueryFinishTask();


        initLineView();

        initEvent();


    }

    private void initLineView() {
        mLineView.setShowPopup(LineView.SHOW_POPUPS_MAXMIN_ONLY); //optional
        mLineView.setColorArray(new int[]{Color.BLACK,Color.GREEN,Color.GRAY,Color.CYAN});
    }

    private void initEvent() {

        mFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mStudyProgresspresenter != null) {
            mStudyProgresspresenter.unRegesiterView(this);
        }
    }

    private ArrayList<String> mCurrentDateList =new ArrayList<>();

    private ArrayList<ArrayList<Integer>> mAllData=new ArrayList<>();

    private ArrayList<Integer> mC2eList=new ArrayList<>();
    private ArrayList<Integer> mReciteList=new ArrayList<>();
    private ArrayList<Integer> mSpellList=new ArrayList<>();
    private ArrayList<Integer> mListenerList=new ArrayList<>();



    @Override
    public void showFinishTimes(int c2eCount, int listenerCount, int reciteCount, int spellCount,String date) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAllData.clear();

                mAllData.add(mC2eList);
                mAllData.add(mListenerList);
                mAllData.add(mReciteList);
                mAllData.add(mSpellList);
                mLineView.setDataList(mAllData);
            }
        });
    }

    @Override
    public void showAllFinsihTimes(ArrayList<String> mTimes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mLineView.setBottomTextList(mTimes);
            }
        });
    }

    @Override
    public void showC2eTask(int finishNum) {
        mC2eList.add(finishNum);
    }

    @Override
    public void showSpellTask(int finishNum) {
        mSpellList.add(finishNum);
    }

    @Override
    public void showListenersTask(int finishNum) {
        mListenerList.add(finishNum);
    }

    @Override
    public void showReciteWordTask(int finishNum) {
        mReciteList.add(finishNum);
    }


}