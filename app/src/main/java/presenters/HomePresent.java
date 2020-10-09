package presenters;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.WordsDao;
import entirys.Words;
import interfaces.IHomeCallback;
import interfaces.IHomePresent;
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


    private HomePresent() {
        WordsDB db =WordsDB.getWordsDB();
        mWordsDao = db.getWordsDao();
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

    public String getCurrentBookTitle() {
        return mCurrentBookTitle;
    }

    public int getCurrentBookSize() {
        return mCurrentBookSize;
    }
}
