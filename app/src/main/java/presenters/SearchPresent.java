package presenters;

import java.util.ArrayList;
import java.util.List;

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
    private List<Words> mSuggestWords =new ArrayList<>();

    private SearchPresent(){
        WordsDB db = WordsDB.getWordsDB();
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
                        iSearchCallback.onSuccessData();
                    }
                }
                if (searchResultWords == null || searchResultWords.size()==0) {
                    for (ISearchCallback iSearchCallback : mCallbackList) {
                        iSearchCallback.onEmpty();
                    }
                }
            }
        }).start();
    }

    @Override
    public void doSuggestSearch(String suggest) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mSuggestWords.clear();
                List<Words> suggestWordsList = mWordsDao.getSearchResultWords(mCurrentBook, suggest);
                if (suggestWordsList!=null && suggestWordsList.size()>0){
                    List<Words> subWords = suggestWordsList.subList(0, suggestWordsList.size() > 6 ? 6 : suggestWordsList.size());
                    for (ISearchCallback iSearchCallback : mCallbackList) {
                        iSearchCallback.showSearchSuggest(subWords);
                    }
                }
            }
        }).start();
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
