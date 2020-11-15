package presenters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.AllTaskDao;
import dao.ReciteWordsDao;
import dao.WordsDao;
import entirys.Words;
import interfaces.ICanClickRecite;
import interfaces.IHomeCallback;
import interfaces.IHomePresent;
import rooms.AllTaskDB;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/2 15:52
 * <p>
 * 作用： xxxx
 */
public class HomePresent implements IHomePresent {

    private static final String TAG = "HomePresent";
    private List<IHomeCallback> mCallbackList=new ArrayList<>();
    private int currentBookNum;
    private final WordsDao mWordsDao;
    private boolean isRandom=true;
    private String mCurrentBookTitle;
    private int mCurrentBookSize;
    private final AllTaskDao mLearnTaskDao;
    private final ReciteWordsDao mReciteWordsDao;


    private HomePresent() {
        WordsDB db =WordsDB.getWordsDB();
        mWordsDao = db.getWordsDao();

        AllTaskDB allTaskDB = AllTaskDB.getAllTaskDB();
        mLearnTaskDao = allTaskDB.getLearnTaskDao();
        mReciteWordsDao = allTaskDB.getReciteWordsDao();
    }

    private volatile static HomePresent sPresent;

    public static HomePresent getPresent() {
        if (sPresent==null){
            synchronized (HomePresent.class){
                if (sPresent==null){
                    sPresent=new HomePresent();
                }
            }
        }
        return sPresent;
    }

    @Override
    public void queryWords() {
        //查询与书号一样的信息
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Words> wordsList = mWordsDao.getSameNumWords(currentBookNum);
                while (isRandom){
                    Random random=new Random();
                    int randomIndex = random.nextInt(wordsList.size());
                    String headWord = wordsList.get(randomIndex).getHeadWord();
                    String simpleTran = wordsList.get(randomIndex).getSimpleTran();
                    for (IHomeCallback iHomeCallback : mCallbackList) {
                        iHomeCallback.showSingle(headWord,simpleTran);
                        isRandom=false;
                    }
                    if (!isRandom){
                        isRandom=true;
                        break;
                    }
                }
            }
        }).start();

    }



    @Override
    public void getBookNum(int position) {
        this.currentBookNum=position;
        //把书号传递给词库的P层
        CIkuPresent.getPresent().getBookNum(currentBookNum);
        ManagerDetailPresent.getPresent().getBookNum(currentBookNum);
        LogUtil.d(TAG,"bookNum --> "+currentBookNum);
    }

    @Override
    public void regesiterHomeView(IHomeCallback callback) {
        if (mCallbackList != null && !mCallbackList.contains(callback)) {
            mCallbackList.add(callback);
        }
    }

    @Override
    public void unRegesiterHomeView(IHomeCallback callback) {
        if (mCallbackList != null) {
            mCallbackList.remove(callback);
        }
    }

    @Override
    public void getBookName(String title) {
        this.mCurrentBookTitle= title;
        LogUtil.d(TAG,"title -->"+title);
        for (IHomeCallback iHomeCallback : mCallbackList) {
            iHomeCallback.showBookName(title);
        }

    }

    @Override
    public void getBookSize(int size) {
        this.mCurrentBookSize=size;
    }

    @Override
    public void canClickRecite(ICanClickRecite iscan) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                String currentDate = format.format(System.currentTimeMillis());

                int maxID;
                maxID =mLearnTaskDao.getReMaxID(currentBookNum);
                LogUtil.d(TAG,"maxID -->"+(maxID));


//                //TODO:获取上一个登陆的account
//                List<String> allAccount = mReciteWordsDao.getAllAccount(currentBookNum);
//                String frontAccount ="";
//                LogUtil.d(TAG,"allAcount size --> "+allAccount.size());
//                if (allAccount.size()>0){
//                    frontAccount = allAccount.get(allAccount.size()-1);
//                }

                //根据任务ID --> 获取完成的时间
                String newDate = mReciteWordsDao.getNewDate(currentBookNum, maxID);
                LogUtil.d(TAG,"newDate -->"+newDate);
                if (!currentDate.equals(newDate) ){
                    //可以跳
                    iscan.isClickRecite(true);
                }else{
                    iscan.isClickRecite(false);
                }
            }

        }).start();

    }

    public String getCurrentBookTitle() {
        return mCurrentBookTitle;
    }

    public int getCurrentBookSize() {
        return mCurrentBookSize;
    }
}
