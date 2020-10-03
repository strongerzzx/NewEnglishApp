package presenters;

import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

import bases.BaseAppciation;
import beans.ContentUrl;
import dao.WordsDao;
import entirys.Words;
import interfaces.ISearchCallback;
import interfaces.ISearchPresent;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/2 21:17
 * <p>
 * 作用： xxxx
 */
public class SearchPresent implements ISearchPresent {

    private static final String TAG = "SearchPresent";
    private List<ISearchCallback> mCallbackList=new ArrayList<>();
    private final WordsDao mWordsDao;
    private int mCurrentBook;

    private SearchPresent(){
        WordsDB db = Room.databaseBuilder(BaseAppciation.getContext(), WordsDB.class, ContentUrl.DB_NAME).build();
        mWordsDao = db.getWordsDao();
    }

    private volatile static SearchPresent sPresent;

    public static SearchPresent getPresent() {
        if (sPresent==null){
            synchronized (SearchPresent.class){
                if (sPresent==null){
                    sPresent=new SearchPresent();
                }
            }
        }
        return sPresent;
    }

    @Override
    public void doSearchResult(String keyword) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Words> searchResultWords = mWordsDao.getSearchResultWords(mCurrentBook, keyword);
                if (searchResultWords != null) {
                    LogUtil.d(TAG,"resultSize -->"+searchResultWords.size());
                    for (ISearchCallback iSearchCallback : mCallbackList) {
                        iSearchCallback.showSearchResult(searchResultWords);
                        System.out.println(searchResultWords.get(0).getHeadWord());
                    }
                }
            }
        }).start();


        LogUtil.d(TAG,"keyword -->"+keyword);
    }

    @Override
    public void doSuggestSearch(String suggest) {

    }

    @Override
    public void regesiterView(ISearchCallback callbak) {
        if (mCallbackList != null && !mCallbackList.contains(callbak)) {
            mCallbackList.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(ISearchCallback callback) {
        if (mCallbackList != null) {
            mCallbackList.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBook =currentBookNum;
    }
}
