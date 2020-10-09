package presenters;

import java.util.ArrayList;
import java.util.List;

import dao.WordClipDao;
import dao.WordsDao;
import entirys.Words;
import interfaces.IManagerDetailPresent;
import interfaces.IMangerDetailCallback;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/9 11:25
 * <p>
 * 作用： xxxx
 */
public class ManagerDetailPresent implements IManagerDetailPresent {

    private static final String TAG = "ManagerDetailPresent";
    private final WordClipDao mWordClipDao;
    private final WordsDao mWordsDao;
    private List<IMangerDetailCallback> mCallbacks=new ArrayList<>();
    private int mCurrentBookPos;
    private int mCurrentClipsId;
    private int mCurrentWordID;

    private ManagerDetailPresent(){
        WordsDB db = WordsDB.getWordsDB();
        mWordClipDao = db.getWordClipDao();
        mWordsDao = db.getWordsDao();
    }

    private volatile static ManagerDetailPresent sPresent;

    public static ManagerDetailPresent getPresent() {
        if (sPresent==null){
            synchronized (ManagerDetailPresent.class){
                sPresent=new ManagerDetailPresent();
            }
        }
        return sPresent;
    }

    //查询收藏夹内 单词的 线程
    private Runnable mQuey=new Runnable() {
        @Override
        public void run() {
            List<Words> managerWordsList = mWordsDao.getWordsByCollectionID(mCurrentBookPos, mCurrentClipsId);
            for (IMangerDetailCallback callback : mCallbacks) {
                callback.getWords(managerWordsList);
            }
            LogUtil.d(TAG,mCurrentBookPos+":"+mCurrentClipsId);
        }
    };


    //删除收藏夹内的单词
    private Runnable deleWordRun=new Runnable() {
        @Override
        public void run() {
            Words delteWords = mWordsDao.getDelteWords(mCurrentBookPos, mCurrentClipsId, mCurrentWordID);
            mWordsDao.deleteWords(delteWords);

            queryCollectionIDForWords();
        }
    };


    @Override
    public void queryCollectionIDForWords() {
        new Thread(mQuey).start();
    }

    @Override
    public void getCollectionID(int clipsID) {
        this.mCurrentClipsId=clipsID;

    }

    @Override
    public void getWordsID(int wordsID) {
        this.mCurrentWordID=wordsID;
    }

    @Override
    public void doDeleteWords() {
        new Thread(deleWordRun).start();
    }

    @Override
    public void regesiterView(IMangerDetailCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(IMangerDetailCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
       this.mCurrentBookPos=currentBookNum;
    }
}
