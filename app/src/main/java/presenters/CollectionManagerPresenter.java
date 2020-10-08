package presenters;

import java.util.ArrayList;
import java.util.List;

import dao.WordClipDao;
import entirys.WordClips;
import interfaces.ICollectionManagerCallback;
import interfaces.ICollectionManagerPresent;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/5 21:49
 * <p>
 * 作用： xxxx
 */
public class CollectionManagerPresenter implements ICollectionManagerPresent {
    private static final String TAG = "CollectionManagerPresenter";
    private List<ICollectionManagerCallback> mCallbacks=new ArrayList<>();
    private final WordsDB mDb;
    private final WordClipDao mClipDao;
    private int mCurrentBookPos;

    private CollectionManagerPresenter(){
        mDb = WordsDB.getWordsDB();
        mClipDao = mDb.getWordClipDao();
    }

    private static CollectionManagerPresenter sPresenter;

    public static CollectionManagerPresenter getPresenter() {
        if (sPresenter==null){
            synchronized (CollectionManagerPresenter.class){
                if (sPresenter==null){
                    sPresenter=new CollectionManagerPresenter();
                }
            }
        }
        return sPresenter;
    }

    @Override
    public void getCollectionsInfo() {
        for (ICollectionManagerCallback callback : mCallbacks) {
            callback.onLoading();
        }

        new Thread(new Runnable() {
            @Override
            public void run() {

                List<WordClips> sameNumWords = mClipDao.getSameNumWords(mCurrentBookPos, true);
                LogUtil.d(TAG,sameNumWords.size()+"大小");

                //获取本书中的所有收藏列表
//                List<WordClips> wordClips = mClipDao.getSameNumWords(mCurrentBookPos);
//                LogUtil.d(TAG,"size -->"+wordClips.size());
//                for (WordClips wordClip : wordClips) {
//                    LogUtil.d(TAG,"headerword -->"+wordClip.getTitle());
//                }
                for (ICollectionManagerCallback callback : mCallbacks) {
                    callback.showCollectionInfo(sameNumWords);
                    //callback.showCollectionSum(wordClips.size());
                    callback.onFinish();
                }
            }
        }).start();
    }


    @Override
    public void regesiterView(ICollectionManagerCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(ICollectionManagerCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookPos=currentBookNum;
    }
}
