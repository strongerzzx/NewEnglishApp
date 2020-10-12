package presenters;

import java.util.ArrayList;
import java.util.List;

import dao.AllTaskDao;
import dao.ReciteWordsDao;
import dao.WordsDao;
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

    private ReciteWordPresent(){
        WordsDB db = WordsDB.getWordsDB();
        mWordsDao = db.getWordsDao();


        AllTaskDB tasksDB=AllTaskDB.getAllTaskDB();
        mLearnTaskDao = tasksDB.getLearnTaskDao();
        mReciteWordsDao = tasksDB.getReciteWordsDao();
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
                int range = Integer.parseInt(mCurrentRange);
                ReciteWords reciteWords=new ReciteWords(mCurrentBookNum,range,mCurrentTime,true,mCurrentInput);
                //写入到单独的任务表
                mReciteWordsDao.insertRecite(reciteWords);
                List<ReciteWords> allRecite = mReciteWordsDao.getAllRecite(mCurrentBookNum);

                //获取最后一个ID --> 即刚插入的id
                int id = allRecite.get(allRecite.size() - 1).getId();
                LogUtil.d(TAG,"reciteWordsID -->"+id);
                //插入关系表
                LearnTasks learnTasks=new LearnTasks(id,mCurrentBookNum,true);
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
