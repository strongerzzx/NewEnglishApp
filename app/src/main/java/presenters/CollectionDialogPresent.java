package presenters;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;

import com.example.englishapp_bishe.HomeActivity;

import java.util.ArrayList;
import java.util.List;

import bases.BaseAppciation;
import commonparms.Commons;
import dao.WordClipDao;
import dao.WordsDao;
import entirys.WordClips;
import entirys.Words;
import interfaces.ICollectionDialogCallback;
import interfaces.ICollectionDialogPresent;
import interfaces.IManagerTitle;
import rooms.WordsDB;
import utils.LogUtil;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

/**
 * 作者：zzx on 2020/10/5 16:34
 * <p>
 * 作用： xxxx
 */
public class CollectionDialogPresent implements ICollectionDialogPresent {
    private static final String TAG = "CollectionDialogPresent";
    private List<ICollectionDialogCallback> mCallbacks=new ArrayList<>();
    private List<String> mDataList=new ArrayList<>();//存储全部当前获取的data
    private String mCurrentTitle;//收藏夹的标题
    private final WordsDB mDb;
    private final WordClipDao mClipsDao;
    private int mCurrentBookPos;//点击哪本书 --> 显示哪个收藏夹
    private int mCurrentPos;//词库中点击的pos
    private List<Words> mCurrentWords=new ArrayList<>();//词库中的List
    private List<Words> mCiKuWords=new ArrayList<>();//词库中的List

    private List<Words> mCollectionWords = new ArrayList<>();    //存储收藏夹中的单词List


    private String mHeadWord;
    private String mPicture;
    private final WordsDao mWordsDao;
    private int mCollectionID;//当前点击的收藏夹ID
    private int mManagerPos;


    private CollectionDialogPresent(){

        mDb = WordsDB.getWordsDB();
        mClipsDao = mDb.getWordClipDao();
        mWordsDao = mDb.getWordsDao();
    }

    private static CollectionDialogPresent sPresent;

    public static CollectionDialogPresent getPresent() {
        if (sPresent==null){
            synchronized (CollectionDialogPresent.class){
                if (sPresent==null){
                    sPresent=new CollectionDialogPresent();
                }
            }
        }
        return sPresent;
    }

    @Override
    public void getViewData(String title) {
        this.mCurrentTitle =title;
        mDataList.add(mCurrentTitle);
    }


    //点击后 --> 插入到数据库
    @Override
    public void submitData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mCurrentWords != null && mCurrentWords.size()>0) {
                    WordClips clips=new WordClips(mCurrentBookPos, mCurrentTitle,mPicture,"","",true);
                    try {
                        //收藏夹插入到数据库
                        mClipsDao.insertWords(clips);
                        //直接把点击的单词插入到新建的收藏夹

                    } catch (SQLiteConstraintException e) {
                        e.printStackTrace();
                        LogUtil.d(TAG,e.getMessage());
                    }
                }else{
                    WordClips clips=new WordClips(mCurrentBookPos, mCurrentTitle,"","","",true);
                    Intent intent=new Intent(BaseAppciation.getContext(), HomeActivity.class);
                    intent.setFlags(FLAG_ACTIVITY_NEW_TASK);
                    BaseAppciation.getContext().startActivity(intent);
                    try {
                        mClipsDao.insertWords(clips);
                    } catch (SQLiteConstraintException e) {
                        e.printStackTrace();
                        LogUtil.d(TAG,e.getMessage());
                    }
                }

                queryAllClips();
            }
        }).start();
    }


    @Override
    public void queryAllClips() {
        new Thread(() -> {

            List<WordClips> wordClips = mClipsDao.getSameNumWords(mCurrentBookPos);
            //遍历全部收藏夹 --> 显示
            for (WordClips wordClip : wordClips) {
               // LogUtil.d(TAG,"收藏id:"+wordClip.getId());
                int trueWords = mWordsDao.getTrueWords(true, wordClip.getId());
                wordClip.setWordsNum(trueWords+"");
                //LogUtil.d(TAG,"单词数量:"+trueWords);
            }

            //Dialog和Fragemnt中的数据 都来自这
            for (ICollectionDialogCallback callback : mCallbacks) {
                callback.showAllClips(wordClips);
            }
        }).start();
    }

    //修改单词表中的单词属性
    @Override
    public void doCollection2ExistFavorites() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //修改成true
                Words words = mCurrentWords.get(mCurrentPos);
                LogUtil.d(TAG,"mCurrentPos --> "+mCurrentPos);
              //  Words words = mCiKuWords.get(mCurrentPos);
                words.setIscollection(true);
                words.setCollectionpos(mCollectionID);//插入收藏的位置
                mWordsDao.updateWords(words);
            }
        }).start();
    }

    //获取收藏夹中的单词
    @Override
    public void doQueryTrueWord(int pos) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //获取收藏夹的ID
                List<WordClips> wordClips = mClipsDao.getSameNumWords(mCurrentBookPos);
                WordClips clips = wordClips.get(pos);
                int clipsID = clips.getId();
                LogUtil.d(TAG, clipsID +":"+ clips.getTitle());

                //根据收藏夹的ID --> 找出单词
                List<Words> wordsByCollectionID = mWordsDao.getWordsByCollectionID(mCurrentBookPos, clipsID);
                for (ICollectionDialogCallback callback : mCallbacks) {
                    callback.getWordInCollection(wordsByCollectionID);
                }

                Commons.setWordsList(wordsByCollectionID);
            }
        }).start();
    }

    //通过pop --> 删除收藏夹选中数据
    @Override
    public void deleCollectionData(int pos) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WordClips> wordClips = mClipsDao.getSameNumWords(mCurrentBookPos);
                WordClips clips = wordClips.get(pos);

                LogUtil.d(TAG,"pos --> "+pos);
                mClipsDao.deleteWords(clips);
            }
        }).start();
    }

    @Override
    public void getClipsTitle(int pos,IManagerTitle IManager) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WordClips> wordClips = mClipsDao.getSameNumWords(mCurrentBookPos);
                WordClips clips = wordClips.get(pos);
                String title = clips.getTitle();
                IManager.GetManagerTitle(title);
            }
        }).start();
    }

    @Override
    public void queryAllCollectionClipsNum() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WordClips> sameNumWords = mClipsDao.getSameNumWords(mCurrentBookPos);
                int size = sameNumWords.size();
                LogUtil.d(TAG,"coll size --> "+size);
                for (ICollectionDialogCallback callback : mCallbacks) {
                    callback.showClipsNum(size);
                }
            }
        });
    }

    @Override
    public void getClipsWords() {

    }

//    //获取单词夹中的单词
//    public void getClipsWords(int pos) {
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<Words> sameNumWords = mWordsDao.getSameNumWords(mCurrentBookPos);
//                int collectionpos = sameNumWords.get(0).getCollectionpos();//获取单词的收藏夹ID
//
//                List<WordClips> wordClips = mClipsDao.getSameNumWords(mCurrentBookPos);
//                int id = wordClips.get(pos).getId();//收藏夹ID
//            }
//        }).start();
//    }

    @Override
    public void regesiterView(ICollectionDialogCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(ICollectionDialogCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookPos =currentBookNum;
    }


    //获取当前点击的图片
    public void getPicText(List<Words> list, int position) {
        mHeadWord = list.get(position - 1).getHeadWord();
        mPicture = list.get(position - 1).getPicture();
        LogUtil.d(TAG,"zhijie headword --> "+ mHeadWord);
        mCurrentWords.addAll(list);
        //mCiKuWords.addAll(list);
        mCurrentPos=(position-1);
    }

    public void getCollectionPos(int mCurrentCollectionID) {
        this.mCollectionID =mCurrentCollectionID;
    }
}
