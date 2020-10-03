package presenters;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import bases.BaseAppciation;
import beans.ContentUrl;
import dao.WordsDao;
import entirys.Words;
import interfaces.ICikuCallback;
import interfaces.ICikuPresent;
import rooms.WordsDB;
import utils.ListPageUtils;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/2 19:23
 * <p>
 * 作用： xxxx
 */
public class CIkuPresent implements ICikuPresent {

    private static final String TAG = "CIkuPresent";
    private List<ICikuCallback> mCallbackList=new ArrayList<>();
    private final WordsDao mWordsDao;
    private int mCurrentBookPosition;
    private List<Words> mCurrentWords=new ArrayList<>();
    private int mCurrentPage=1;//起始页
    private ListPageUtils<Words> mPageUtils;

    private CIkuPresent(){
        WordsDB db = Room.databaseBuilder(BaseAppciation.getContext(), WordsDB.class, ContentUrl.DB_NAME).build();
        mWordsDao = db.getWordsDao();
    }

    private volatile static CIkuPresent sPresent;

    public static CIkuPresent getPresent() {
        if (sPresent==null){
            synchronized (CIkuPresent.class){
                if (sPresent==null){
                    sPresent=new CIkuPresent();
                }
            }
        }
        return sPresent;
    }

    @Override
    public void queryAllWords() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Words> bookWords = mWordsDao.getSameNumWords(mCurrentBookPosition);
                mPageUtils = new ListPageUtils(bookWords,20);
                for (ICikuCallback iCikuCallback : mCallbackList) {
                    List pagedList = mPageUtils.getPagedList(mCurrentPage);
                    iCikuCallback.showAllWords(pagedList);
                }
                mCurrentWords.addAll(bookWords);

            }
        }).start();
    }

    @Override
    public void doLoader() {
        mCurrentPage++;
        doLoadMore(true);
    }

    @Override
    public void doCollection() {

    }

    private void doLoadMore(boolean more) {
        if (more){
            List<Words> pagedList = mPageUtils.getPagedList(mCurrentPage);
            for (ICikuCallback iCikuCallback : mCallbackList) {
                iCikuCallback.showAllWords(pagedList);
                iCikuCallback.onPullRefresh(pagedList.size());
            }
        }
    }

    @Override
    public void regesiterView(ICikuCallback callbak) {
        if (mCallbackList != null && !mCallbackList.contains(callbak)) {
            mCallbackList.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(ICikuCallback callback) {
        if (mCallbackList != null) {
            mCallbackList.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookPosition =currentBookNum;
        LogUtil.d(TAG,"mCurrentBookPosition --> "+mCurrentBookPosition);
    }
}
