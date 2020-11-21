package presenters;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import beans.DateTemps;
import commonparms.Commons;
import dao.Chinese2EnglishDao;
import dao.ListenerDao;
import dao.ReciteWordsDao;
import dao.SpellDao;
import dao.TjDateDao;
import entirys.Chinese2English;
import entirys.Listeners;
import entirys.ReciteWords;
import entirys.Spells;
import interfaces.IStudyProgressCallback;
import interfaces.IStudyProgressPresenter;
import rooms.AllTaskDB;
import rooms.DateDB;
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
    private File mDateFile;
    private String mCurrentDate;
    private  TjDateDao mDateDao;

    private StudyProgressPresenter() {
        AllTaskDB taskDB = AllTaskDB.getAllTaskDB();
        mC2eDao = taskDB.getChinese2EnglishDao();
        mListenerDao = taskDB.getListenerDao();
        mReciteWordsDao = taskDB.getReciteWordsDao();
        mSpellDao = taskDB.getSpellDao();

        DateDB dateDB = DateDB.getDateDB();
        mDateDao = dateDB.getDateDao();

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

    //c2e的日期
    private Set<String> mDateSet=new HashSet<>();

    //spell的日期
    private Set<String> spellSet=new HashSet<>();

    //listeners的日期
    private Set<String> mListenersSet=new HashSet<>();

    //ReciteWord的日期
    private Set<String> mReciteSet=new HashSet<>();

    private Set<String> mQuerySet = new HashSet<>();//每次查询返回的时间
    private ArrayList<String> mQueryList = new ArrayList<>();//用List来存储每次查询返回的Set时间
    private StringBuffer sb = new StringBuffer();
    @Override
    public void doQueryFinishTask() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mQueryList.clear();//防止时间重叠

                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date(System.currentTimeMillis());
                mCurrentDate = format.format(date);
                 //String currentDate2 = "2020-10-19";

//                mQuerySet.add(mCurrentDate);

                DateTemps dateTemps = new DateTemps(mCurrentDate,Commons.getmCurrentLoginAccount(),mCurrentBookPos);
               // DateTemps dateTemps2 = new DateTemps(currentDate2,Commons.getmCurrentLoginAccount(),mCurrentBookPos);
                mDateDao.insertDate(dateTemps);

                List<String> dateList = mDateDao.queryDate(mCurrentBookPos, Commons.getmCurrentLoginAccount());

                for (String s : dateList) {
                    mQuerySet.add(s);
                    LogUtil.d(TAG,"数据库中的日期 --> "+s+":"+dateList.size() +"set 中的大小 --> "+mQuerySet.size());
                }

                mQueryList.addAll(mQuerySet);

                for (IStudyProgressCallback callback : mCallbacks) {
                    callback.showAllFinsihTimes(mQueryList);
                }

//                //C2e的数据
                getC2eData(mCurrentDate);
//
//                //Spell的数据
                getSpellData(mCurrentDate);
//
//                //Listener的数据
                getListenerData(mCurrentDate);
//
//                //ReciteWord的数据
                getReciteData(mCurrentDate);

                for (IStudyProgressCallback callback : mCallbacks) {
                    callback.showAllData(true);
                }

            }
        }).start();
    }
//    public void doQueryFinishTask() {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                mQueryList.clear();//防止时间重叠
//
//
////                DateTempBeans dateTempBeans = new DateTempBeans(currentDate);
//                try {
//                    File exterFile = BaseAppciation.getContext().getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
//                    mDateFile = new File(exterFile, "date_temp.txt");
//                    mDateFile.createNewFile();
//                    System.out.println("1");
//                    ObjectInputStream ois = new ObjectInputStream(new FileInputStream(mDateFile));
//                    HashSet<String> strings= (HashSet<String>) ois.readObject();
//                    if (strings != null) {
//                       mQuerySet=strings;
//                    }
//                    System.out.println("2");
//                    LogUtil.d(TAG,"mQuerySet size --> "+mQuerySet.size());
//
//                    //查询所有任务完成情况 --> 根据日期来排序
//                    SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//                    Date date=new Date(System.currentTimeMillis());
//                    String currentDate = format.format(date);
//                    System.out.println("3");
//                    String currentDate2 = "2020-10-19";
////                    mQuerySet.add(currentDate);
//                    mQuerySet.add(currentDate2);
//
//
//                    ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(mDateFile));
//                    oos.writeObject(mQuerySet);
//                    oos.flush();
//                    oos.close();
//
//                    ois = new ObjectInputStream(new FileInputStream(mDateFile));
//                    Set<String> o = (Set<String>) ois.readObject();
//                    LogUtil.d(TAG,"序列后的数据 --> "+o.size());
//
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
////                for (String s : mQuerySet) {
////                    LogUtil.d(TAG,"set中的日期 --> "+s+":"+mQuerySet.size());
////                }
////
////                for (String s : mQueryList) {
////                    LogUtil.d(TAG,"List中的日期 --> "+s+":"+mQueryList.size());
////                }
//
////                for (IStudyProgressCallback callback : mCallbacks) {
////                    callback.showAllFinsihTimes(mQueryList);
////                }
//
////                //C2e的数据
////                getC2eData(currentDate);
////
////                //Spell的数据
////                getSpellData(currentDate);
////
////                //Listener的数据
////                getListenerData(currentDate);
////
////                //ReciteWord的数据
////                getReciteData(currentDate);
//
//                for (IStudyProgressCallback callback : mCallbacks) {
////                    callback.showAllFinsihTimes(mQueryList);
//                    callback.showAllData(true);
//                }
//            }
//        }).start();
//    }

    private ArrayList<String> mDates = new ArrayList<>();
    private void getReciteData(String currentDate) {
//        mReciteSet.clear();
        mDates.clear();
        List<ReciteWords> reciteWords = mReciteWordsDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos,Commons.getmCurrentLoginAccount());
        if (reciteWords != null && reciteWords.size()>0) {
            for (ReciteWords reciteWord : reciteWords) {  //放日期
                mReciteSet.add(reciteWord.getFinishDate());
            }
            for (String s : mReciteSet) {
                List<ReciteWords> reciteWords1 = mReciteWordsDao.queryFinishByDate(true, s, mCurrentBookPos,Commons.getmCurrentLoginAccount());

                mDates.add(s);//时间
                for (IStudyProgressCallback callback : mCallbacks) {
                    callback.showReciteWordTask(reciteWords1.size());
                   // callback.showAllFinsihTimes(mDates);

                    LogUtil.d(TAG,"背单词的次数 --> "+reciteWords1.size());
                }
            }

            LogUtil.d(TAG,"时间 --> "+mDates.size());
        }
    }

    //Listener的数据
    private void getListenerData(String currentDate) {
//        mListenersSet.clear();
        List<Listeners> listeners = mListenerDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos,Commons.getmCurrentLoginAccount());
        if (listeners != null && listeners.size()>0) {
            for (Listeners listener : listeners) {
                mListenersSet.add(listener.getFinishDate());
            }

            for (String s : mListenersSet) {
                List<Listeners> lisnten = mListenerDao.queryFinishByDate(true, s, mCurrentBookPos,Commons.getmCurrentLoginAccount());
                for (IStudyProgressCallback callback : mCallbacks) {
                    callback.showListenersTask(lisnten.size());
                }
                LogUtil.d(TAG,"背单词的次数 --> "+lisnten.size());
            }
        }
    }

    private void getSpellData(String currentDate) {
//        spellSet.clear();
        //查询这个范围内完成的拼写任务
        List<Spells> spells = mSpellDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos,Commons.getmCurrentLoginAccount());
        if (spells != null && spells.size()>0) {
            for (Spells spell : spells) {
                spellSet.add(spell.getFinishDate());//有可能一天有多次进行 --> 防止时间重复
            }

            for (String s : spellSet) {
                List<Spells> spells1 = mSpellDao.queryFinishByDate(true, s, mCurrentBookPos,Commons.getmCurrentLoginAccount());
                for (IStudyProgressCallback callback : mCallbacks) {
                    callback.showSpellTask(spells1.size());
                }
                System.out.println("spells1 大小"+spells1.size());
            }
        }
    }

    private void getC2eData(String currentDate) {
//        mDateSet.clear();
        //范围 --> c2e任务 完成日期
        List<Chinese2English> rangeC2e = mC2eDao.queryFinishByDate(true, "2020-10-31", currentDate, mCurrentBookPos,Commons.getmCurrentLoginAccount());

        if (rangeC2e != null && rangeC2e.size()>0) {
            for (Chinese2English chinese2English : rangeC2e) {//日期去重
                mDateSet.add(chinese2English.getFinishDate());
                LogUtil.d(TAG,"range finish --> "+chinese2English.getFinishDate());
            }

            for (String s : mDateSet) {
                List<Chinese2English> chinese2Englishes = mC2eDao.queryFinishByDate(true, s, mCurrentBookPos, Commons.getmCurrentLoginAccount());
                for (IStudyProgressCallback callback : mCallbacks) {
                    callback.showC2eTask(chinese2Englishes.size());
                }
                LogUtil.d(TAG,"c2e分日期数据 --> "+chinese2Englishes.size());
            }
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

