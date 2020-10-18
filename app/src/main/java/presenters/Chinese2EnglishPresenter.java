package presenters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.Chinese2EnglishDao;
import dao.WordsDao;
import entirys.Chinese2English;
import entirys.Words;
import interfaces.IChinese2EnglishPresenter;
import interfaces.IChineses2EnglishCallback;
import rooms.AllTaskDB;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/16 16:36
 * <p>
 * 作用： xxxx
 */
public class Chinese2EnglishPresenter implements IChinese2EnglishPresenter {

    private static final String TAG = "Chinese2EnglishPresenter";
    private List<IChineses2EnglishCallback> mCallbackList=new ArrayList<>();
    private int mCurrentBookNum;
    private final WordsDao mWordsDao;
    private int mCurrentIndex;//当前第几个
    private final Chinese2EnglishDao mC2eDao;
    private int mFinalProgress;

    private Chinese2EnglishPresenter() {
        WordsDB wordsDB = WordsDB.getWordsDB();
        mWordsDao = wordsDB.getWordsDao();

        AllTaskDB allTaskDB = AllTaskDB.getAllTaskDB();
        mC2eDao = allTaskDB.getChinese2EnglishDao();
    }

    private static volatile Chinese2EnglishPresenter sPresenter;

    public static Chinese2EnglishPresenter getPresenter() {
        if (sPresenter==null){
            synchronized (Chinese2EnglishPresenter.class){
                if (sPresenter==null){
                    sPresenter=new Chinese2EnglishPresenter();
                }
            }
        }
        return sPresenter;
    }

    public void doClear(){
        mCurrentIndex=0;
    }

    @Override
    public void requestData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取全部Words
                List<Words> sameNumWords = mWordsDao.getSameNumWords(mCurrentBookNum);

                //随机返回任意的单词
                Random random=new Random();
                int randomIndex = random.nextInt(sameNumWords.size() - 10);
                Words words = sameNumWords.get(randomIndex);
                mCurrentIndex++;//当前做过的题



                for (IChineses2EnglishCallback iChineses2EnglishCallback : mCallbackList) {
                    iChineses2EnglishCallback.showContent(words.getTran());//content
                    iChineses2EnglishCallback.showAllStr(words.getHeadWord(),sameNumWords.get(randomIndex+1).getHeadWord(),
                            sameNumWords.get(randomIndex-1).getHeadWord()
                            ,sameNumWords.get(randomIndex+2).getHeadWord()
                            ,sameNumWords.get(randomIndex-2).getHeadWord());
                    iChineses2EnglishCallback.showCurrentProgress(mCurrentIndex);
                    iChineses2EnglishCallback.getData2Pop(sameNumWords,randomIndex,mFinalProgress,mCurrentIndex);
                }
                LogUtil.d(TAG,"words random --> "+randomIndex+":"+words.getHeadWord()+":"+words.getTran());

            }
        }).start();
    }



    @Override
    public void doFinalProgress() {
        Random random=new Random();
        mFinalProgress =random.nextInt(30);
        while (true){
            if (mFinalProgress>8)
                break;
            else{
                mFinalProgress =random.nextInt(30);
            }
        }

        for (IChineses2EnglishCallback iChineses2EnglishCallback : mCallbackList) {
            iChineses2EnglishCallback.showAllProgress(mFinalProgress);
        }
    }

    @Override
    public void doInsertChineseRecord() {

        new Thread(inserChinese).start();
    }

    private Runnable inserChinese =new Runnable() {
        @Override
        public void run() {
            SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
            Date date=new Date(System.currentTimeMillis());
            String finishDate = format.format(date);

            //完成任务 --> 插入数据库
            Chinese2English c2e=new Chinese2English(mCurrentBookNum,finishDate,true,mFinalProgress);
            mC2eDao.insertChinese(c2e);
        }
    };


    @Override
    public void regesiterView(IChineses2EnglishCallback callbak) {
        if (mCallbackList != null && !mCallbackList.contains(callbak)) {
            mCallbackList.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(IChineses2EnglishCallback callback) {
        if (mCallbackList != null && !mCallbackList.isEmpty()) {
            mCallbackList.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookNum=currentBookNum;
    }
}
