package com.example.englishapp_bishe;

import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import im.dacer.androidcharts.LineView;
import interfaces.IStudyProgressCallback;
import presenters.StudyProgressPresenter;
import utils.LogUtil;

public class StudyProgressActivity extends AppCompatActivity implements IStudyProgressCallback{

    private static final String TAG = "StudyProgressActivity";
    private StudyProgressPresenter mStudyProgresspresenter;

    @BindView(R.id.study_iv_finish)
    public ImageView mFinish;
    @BindView(R.id.line_view)
    public LineView mLineView;
    @BindView(R.id.line_chart)
    public LineChart mLineChart;

    private ArrayList<String> mCurrentDateList =new ArrayList<>();

    private ArrayList<ArrayList<Integer>> mAllData=new ArrayList<>();

    private ArrayList<Integer> mC2eList=new ArrayList<>();
    private ArrayList<Integer> mReciteList=new ArrayList<>();
    private ArrayList<Integer> mSpellList=new ArrayList<>();
    private ArrayList<Integer> mListenerList=new ArrayList<>();


    /*
      新的图标框架
     */

    private List<Entry> yValue1 = new ArrayList<>();
    private List<Entry> yValue2 = new ArrayList<>();
    private List<Entry> yValue3 = new ArrayList<>();
    private List<Entry> yValue4 = new ArrayList<>();

    private LineDataSet mDataSet1;
    private LineDataSet mDataSet2;
    private LineDataSet mDataSet3;
    private LineDataSet mDataSet4;


    //每一个任务 在折线图中的相隔距离
    private int i=0;
    private int j=0;
    private int k=0;
    private int z=0;
    private XAxis mXAxis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_progress);

        ButterKnife.bind(this);
        mStudyProgresspresenter = StudyProgressPresenter.getPresenter();
        mStudyProgresspresenter.regesiterView(this);
        mStudyProgresspresenter.doQueryFinishTask();


        initLineView();

        initAxis();

        initEvent();

    }

    private void initAxis() {

       // mLineChart.setNoDataText("暂时没有完成任意一个学习计划...");

        //y轴
        mLineChart.getAxisRight().setEnabled(false);
        YAxis leftY = mLineChart.getAxisLeft();
        leftY.setDrawGridLines(false);
        leftY.setDrawAxisLine(false);
        leftY.setDrawLabels(false);
        leftY.setGranularity(1f);
        leftY.setAxisMinimum(0f);


        //X轴
        mXAxis = mLineChart.getXAxis();
        //设置X轴的位置（默认在上方)
        mXAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置X轴坐标之间的最小间隔
        mXAxis.setGranularity(1f);
        mXAxis.setDrawAxisLine(false);
        mXAxis.setDrawGridLines(false);
        mXAxis.setTextColor(Color.BLUE);
        mXAxis.setTextSize(14f);


        //设置图例
        Legend legend = mLineChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);


        //隐藏描述
        Description description = new Description();
        description.setEnabled(false);
        mLineChart.setDescription(description);


//        //模拟X轴标签
//        // String[] week = new String[]{"昨天"};
//        String[] week = new String[]{"昨天","今天","明天","后天","测","试"};
//        xAxis.setLabelCount(week.length);
//        xAxis.setTextColor(Color.BLUE);
//        xAxis.setTextSize(14f);
//
//        //设备标签显示
//        xAxis.setValueFormatter(new ValueFormatter() {
//            @Override
//            public String getAxisLabel(float value, AxisBase axis) {
//
//                Log.d(TAG,"week value --> "+value);
//                return week[(int)value];
//            }
//        });

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

    @Override
    public void showAllFinsihTimes(ArrayList<String> mTimes) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //模拟X轴标签
                //X轴
                mXAxis.setLabelCount(mTimes.size());
                LogUtil.d(TAG,"mTimse size --> "+mTimes.size());
                //设备标签显示
                mXAxis.setValueFormatter(new ValueFormatter() {
                    @Override
                    public String getAxisLabel(float value, AxisBase axis) {
                        int IValue = (int) value;
                        CharSequence format = DateFormat.format("MM/dd",
                                System.currentTimeMillis()-(long)((mTimes.size()-IValue))*24*60*60*1000);
                        Log.d(TAG,"week value --> "+value);
                        return format.toString();
                    }
                });
                mLineView.setBottomTextList(mTimes);
            }
        });
    }


    @Override
    public void showC2eTask(int finishNum) {
        if (finishNum>0) {
            mC2eList.add(finishNum);
            yValue1.add(new Entry(i++,finishNum));
        }else{
            mC2eList.add(0);
            yValue1.add(new Entry(0,0));
        }

        mDataSet1 = new LineDataSet(yValue1,"中意选英");
        mDataSet1.setColor(Color.RED);
        mDataSet1.setLineWidth(2f);
        mDataSet1.setDrawCircleHole(false);
        mDataSet1.setCircleColor(Color.RED);

        mAllData.add(mC2eList);
    }

    @Override
    public void showSpellTask(int finishNum) {
        if (finishNum>0) {
            mSpellList.add(finishNum);
            yValue2.add(new Entry(j++,finishNum));
        }else{
            mSpellList.add(0);
            yValue2.add(new Entry(1,finishNum));
        }

        mDataSet2 = new LineDataSet(yValue2,"单词拼写");
        mDataSet2.setColor(Color.BLUE);
        mDataSet2.setLineWidth(2f);
        mDataSet2.setDrawCircleHole(false);
        mDataSet2.setCircleColor(Color.BLUE);

        mAllData.add(mSpellList);
    }

    @Override
    public void showListenersTask(int finishNum) {
        if (finishNum>0) {
            mListenerList.add(finishNum);
            yValue3.add(new Entry(k++,finishNum));
        }else{
            mListenerList.add(0);
            yValue3.add(new Entry(2,0));
        }

        mDataSet3 = new LineDataSet(yValue3,"听音识意");
        mDataSet3.setColor(Color.GREEN);
        mDataSet3.setLineWidth(2f);
        mDataSet3.setDrawCircleHole(false);
        mDataSet3.setCircleColor(Color.GREEN);

        mAllData.add(mListenerList);
    }

    @Override
    public void showReciteWordTask(int finishNum) {
        if (finishNum>0) {
            mReciteList.add(finishNum);
            yValue4.add(new Entry(z++,finishNum));
        }else{
            mReciteList.add(0);
            yValue4.add(new Entry(3,0));
        }

        mDataSet4 = new LineDataSet(yValue4,"背单词");
        mDataSet4.setColor(Color.GRAY);
        mDataSet4.setLineWidth(2f);
        mDataSet4.setDrawCircleHole(false);
        mDataSet4.setCircleColor(Color.GRAY);

        mAllData.add(mReciteList);
    }

    @Override
    public void showAllData(boolean b) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (b){
                    try {
                        LineData mLineData = new LineData(mDataSet1,mDataSet2,mDataSet3,mDataSet4);
                        mLineChart.setData(mLineData);
                        mLineChart.invalidate();

                        mLineView.setDataList(mAllData);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}