package presenters;

import java.util.ArrayList;
import java.util.List;

import dao.WordsDao;
import entirys.Words;
import interfaces.IReciteWordCallback;
import interfaces.IReciteWordPresent;
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
    private boolean isClick=false;

    private ReciteWordPresent(){
        WordsDB db = WordsDB.getWordsDB();
        mWordsDao = db.getWordsDao();
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
