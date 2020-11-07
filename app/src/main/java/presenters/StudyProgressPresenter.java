package presenters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dao.Chinese2EnglishDao;
import dao.ListenerDao;
import dao.ReciteWordsDao;
import dao.SpellDao;
import entirys.Chinese2English;
import entirys.Listeners;
import entirys.ReciteWords;
import entirys.Spells;
import interfaces.IStudyProgressCallback;
import interfaces.IStudyProgressPresenter;
import rooms.AllTaskDB;
import utils.LogUtil;

public
/**
 * 作者：zzx on 2020/10/30 15:56
 *  作用： xxxx
 */
class StudyProgressPresenter implements IStudyProgressPresenter {

    private static final String TAG = "StudyProgressPresenter";
    private List<IStudyProgressCallback> mCallbacks=new ArrayList<>();

    private static volatile StudyProgressPresenter sPresenter;
    private int mCurrentBookPos;
    private final Chinese2EnglishDao mC2eDao;
    private final ListenerDao mListenerDao;
    private final ReciteWordsDao mReciteWordsDao;
    private final SpellDao mSpellDao;

    private StudyProgressPresenter() {
        AllTaskDB taskDB = AllTaskDB.getAllTaskDB();
        mC2eDao = taskDB.getChinese2EnglishDao();
        mListenerDao = taskDB.getListenerDao();
        mReciteWordsDao = taskDB.getReciteWordsDao();
        mSpellDao = taskDB.getSpellDao();
    }

    public static StudyProgressPresenter getPresenter() {
        if (sPresenter==null){
            synchronized (StudyProgressPresenter.class){
                if (sPresenter==null){
                    sPresenter=new StudyProgressPresenter();
                }
            }
        }
        return sPresenter;
    }


    //c2e的日期和任务完成次数
    private Set<String> mDateSet=new HashSet<>();
    private ArrayList<String> mTimes=new ArrayList<>();

    //spell的日期和任务完成次数
    private Set<String> spellSet=new HashSet<>();
    private ArrayList<String> mSpells=new ArrayList<>();

    //listeners的日期和任务完成次数
    private Set<String> mListenersSet=new HashSet<>();
    private ArrayList<String> mListenersList=new ArrayList<>();

    //ReciteWord的日期和任务完成次数
    private Set<String> mReciteSet=new HashSet<>();
    private ArrayList<String> mReciteList=new ArrayList<>();

    @Override
    public void doQueryFinishTask() {
        //查询所有任务完成情况 --> 根据日期来排序
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Date date=new Date(System.currentTimeMillis());
        String currentDate = format.format(date);
        new Thread(new Runnable() {
            @Override
            public void run() {
                LogUtil.d(TAG,"currentDate --> "+currentDate);

                //获取当天日期的每个任务集合
                List<Spells> spellCount = mSpellDao.queryFinishByDate(true, currentDate, mCurrentBookPos);
                List<Listeners> listenerCount = mListenerDao.queryFinishByDate(true, currentDate, mCurrentBookPos);
                List<Chinese2English> c2eCount = mC2eDao.queryFinishByDate(true, currentDate, mCurrentBookPos);
                List<ReciteWords> reciteCount = mReciteWordsDao.queryFinishByDate(true, currentDate, mCurrentBookPos);

                //C2e的数据
                getC2eData(currentDate);

                //Spell的数据
                getSpellData(currentDate);

                //Listener的数据
                getListenerData(currentDate);

                //ReciteWord的数据
                getReciteData(currentDate);

                for (IStudyProgressCallback callback : mCallbacks) {
                    callback.showAllFinsihTimes(mTimes);
                    callback.showFinishTimes(c2eCount.size(),listenerCount.size(),reciteCount.size(),spellCount.size(),currentDate);
                }
            }
        }).start();

    }

    private void getReciteData(String currentDate) {
        mReciteSet.clear();
        mReciteList.clear();
        List<ReciteWords> reciteWords = mReciteWordsDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos);
        for (ReciteWords reciteWord : reciteWords) {
            mReciteSet.add(reciteWord.getFinishDate());
        }
        mReciteList.addAll(mReciteSet);
        for (int i = 0; i < mReciteList.size(); i++) {
            List<ReciteWords> reciteWords1 = mReciteWordsDao.queryFinishByDate(true, mReciteList.get(i), mCurrentBookPos);
            for (IStudyProgressCallback callback : mCallbacks) {
                callback.showReciteWordTask(reciteWords1.size());
            }
        }
    }

    private void getListenerData(String currentDate) {
        //Listener的数据
        mListenersSet.clear();
        mListenersList.clear();
        List<Listeners> listeners = mListenerDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos);
        for (Listeners listener : listeners) {
            mListenersSet.add(listener.getFinishDate());
        }
        mListenersList.addAll(mListenersSet);
        for (int i = 0; i < mListenersList.size(); i++) {
            List<Listeners> lisnten = mListenerDao.queryFinishByDate(true, mListenersList.get(i), mCurrentBookPos);
            for (IStudyProgressCallback callback : mCallbacks) {
                callback.showListenersTask(lisnten.size());
            }
        }
    }

    private void getSpellData(String currentDate) {
        spellSet.clear();
        mSpells.clear();
        List<Spells> spells = mSpellDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos);
        for (Spells spell : spells) {
            spellSet.add(spell.getFinishDate());
        }
        mSpells.addAll(spellSet);
        for (int i = 0; i < mSpells.size(); i++) {
            List<Spells> spells1 = mSpellDao.queryFinishByDate(true, mSpells.get(i), mCurrentBookPos);
            for (IStudyProgressCallback callback : mCallbacks) {
                callback.showSpellTask(spells1.size());
            }
        }
    }

    private void getC2eData(String currentDate) {
        mDateSet.clear();
        mTimes.clear();
        //范围 --> c2e任务 完成日期
        List<Chinese2English> rangeC2e = mC2eDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos);
        for (Chinese2English chinese2English : rangeC2e) {//日期去重
            mDateSet.add(chinese2English.getFinishDate());
            LogUtil.d(TAG,"range finish --> "+chinese2English.getFinishDate());
        }
        //Set --> List  --> 日期数据
        mTimes.addAll(mDateSet);

        //获取日期中 对应的数据
        for (int i = 0; i < mTimes.size(); i++) {
            List<Chinese2English> chinese2Englishes = mC2eDao.queryFinishByDate(true, mTimes.get(i), mCurrentBookPos);
            for (IStudyProgressCallback callback : mCallbacks) {
                callback.showC2eTask(chinese2Englishes.size());
            }
            LogUtil.d(TAG,"c2e分日期数据 --> "+chinese2Englishes.size());
        }
    }

    @Override
    public void regesiterView(IStudyProgressCallback callbak) {
        if (!mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(IStudyProgressCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookPos=currentBookNum;
        LogUtil.d(TAG,"currentBookNum --> "+currentBookNum);
    }
}

