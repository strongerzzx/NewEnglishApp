package presenters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.AllTaskDao;
import dao.Chinese2EnglishDao;
import dao.ListenerDao;
import dao.ReciteWordsDao;
import dao.WordsDao;
import entirys.Chinese2English;
import entirys.LearnTasks;
import entirys.ReciteWords;
import entirys.Words;
import interfaces.IReciteWordCallback;
import interfaces.IReciteWordPresent;
import rooms.AllTaskDB;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/10 11:50
 * <p>
 * 作用： xxxx
 */
public class ReciteWordPresent implements IReciteWordPresent {

    private static final String TAG = "ReciteWordPresent";
    private List<IReciteWordCallback> mCallbackList=new ArrayList<>();
    private int mCurrentBookNum;
    private final WordsDao mWordsDao;
    private final AllTaskDao mLearnTaskDao;
    private final ReciteWordsDao mReciteWordsDao;
    private String mCurrentInput;
    private String mCurrentTime;
    private String mCurrentRange;
    private int mFinishMostTime;
    private int mFinishTime;
    private boolean isRandom=false;//是否要重置随技数
    private final Chinese2EnglishDao mChinese2EnglishDao;
    private final ListenerDao mListenerDao;


    private ReciteWordPresent(){
        WordsDB db = WordsDB.getWordsDB();
        mWordsDao = db.getWordsDao();


        AllTaskDB tasksDB=AllTaskDB.getAllTaskDB();
        mLearnTaskDao = tasksDB.getLearnTaskDao();
        mReciteWordsDao = tasksDB.getReciteWordsDao();
        mChinese2EnglishDao = tasksDB.getChinese2EnglishDao();
        mListenerDao = tasksDB.getListenerDao();
    };

    private volatile  static ReciteWordPresent sPresent;

    public static ReciteWordPresent getPresent() {
        if (sPresent==null){
            synchronized (ReciteWordPresent.class){
                if (sPresent==null){
                    sPresent=new ReciteWordPresent();
                }
            }
        }
        return sPresent;
    }

    @Override
    public void getWordRange(String range) {
        LogUtil.d(TAG,"range --> "+range);
    }

    private Runnable getWordsByDb =new Runnable() {
        @Override
        public void run() {
            List<Words> wordsList = mWordsDao.getSameNumWords(mCurrentBookNum);
            for (IReciteWordCallback iReciteWordCallback : mCallbackList) {
                iReciteWordCallback.showAllWordsList(wordsList);
            }
        }
    };



    @Override
    public void getWordList() {
        new Thread(getWordsByDb).start();
    }

    //把完成的任务写入到数据库
    @Override
    public void doFinishTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                //插入 --> 查询刚插入的任务ID --> 插入到关系表
                int range = Integer.parseInt(mCurrentRange);
                mFinishTime+=1;
                ReciteWords reciteWords=new ReciteWords(mCurrentBookNum,range,mCurrentTime,mFinishTime,mFinishMostTime,true,mCurrentInput);
                LogUtil.d(TAG,"mFinishTime --> "+mFinishTime);
                LogUtil.d(TAG,"isert finishmostTime -->"+mFinishMostTime);

                for (IReciteWordCallback iReciteWordCallback : mCallbackList) {
                    iReciteWordCallback.showCurrentSize(mFinishTime);
                }

                //写入到单独的任务表
                mReciteWordsDao.insertRecite(reciteWords);
                List<ReciteWords> allRecite = mReciteWordsDao.getAllRecite(mCurrentBookNum);

                //获取最后一个ID --> 即刚插入的id
                int id = allRecite.get(allRecite.size() - 1).getId();
                LogUtil.d(TAG,"reciteWordsID -->"+id);
                //插入关系表

                //虚假的数据
                Chinese2English c2e=new Chinese2English(1,"1",true,1);
                mChinese2EnglishDao.insertChinese(c2e);

                LearnTasks learnTasks=new LearnTasks(id,1,mCurrentBookNum,true);
                mLearnTaskDao.insertReciteTask(learnTasks);
            }
        }).start();
    }

    @Override
    public void getFinishInfo(String range, String compltetTime, String input) {
        this.mCurrentRange=range;

        this.mCurrentTime=compltetTime;
        this.mCurrentInput=input;

    }

    //在还没插入数据的时候 --> 设置最大点击次数
    private Runnable maxUpperTask=new Runnable() {
        @Override
        public void run() {
            //获取最新的背单词任务ID
            int maxID;
            maxID =mLearnTaskDao.getReMaxID(mCurrentBookNum);
            LogUtil.d(TAG,"maxID -->"+(maxID));


            //根据任务ID --> 获取完成的时间
            String newDate = mReciteWordsDao.getNewDate(mCurrentBookNum, maxID);
            LogUtil.d(TAG,"newDate -->"+newDate);
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            String currentDate = format.format(System.currentTimeMillis());


            int newRandom=0;

            //如果是同一天的话 --> 不更新random
            if (!currentDate.equals(newDate)){
                //更新
                isRandom=true;
            }else{
                //不更新
                isRandom=false;
            }

            LogUtil.d(TAG,"isRandom --> "+isRandom);
            if (isRandom){
                Random random=new Random();
                newRandom=random.nextInt(6);
                if (newRandom==0)
                    newRandom=1;
                mFinishMostTime=newRandom;
            }

            for (IReciteWordCallback iReciteWordCallback : mCallbackList) {
                iReciteWordCallback.showMaxSize(mFinishMostTime);
            }
            LogUtil.d(TAG,"mFinishMostTime -->"+mFinishMostTime);
        }
    };

    @Override
    public void isRandomMaxUpper() {
        //TODO: 查询是否为同一天  如果是同一天 则不更新随技数 --> 并且设置最大完成次数
        new Thread(maxUpperTask).start();
    }

    @Override
    public void regesiterView(IReciteWordCallback callbak) {
        if (mCallbackList != null && !mCallbackList.contains(callbak)) {
            mCallbackList.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(IReciteWordCallback callback) {
        if (mCallbackList != null && !mCallbackList.isEmpty()) {
            mCallbackList.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookNum=currentBookNum;
    }
}
