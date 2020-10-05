package presenters;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;

import com.example.englishapp_bishe.HomeActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import bases.BaseAppciation;
import dao.WordClipDao;
import entirys.WordClips;
import entirys.Words;
import interfaces.ICollectionDialogCallback;
import interfaces.ICollectionDialogPresent;
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
    private List<String> mPicList=new ArrayList<>();
    private String mCurrentData;
    private String mCurrentPic;
//    private final WordClipsDB mDb;
    private final WordsDB mDb;
    //private final WordClipDao mClipsDao;
    private final WordClipDao mClipsDao;
    private int mCurrentBookPos;
    private int mDefaultClipsWordsSize=0;
    private String mPicture;
    private int mCurrentPos;

    private CollectionDialogPresent(){
       // mDb = WordClipsDB.getDB();
     //   mClipsDao = mDb.getClipsDao();

        mDb = WordsDB.getWordsDB();
        mClipsDao = mDb.getWordClipDao();
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
    public void getViewData(String currentData) {
        this.mCurrentData=currentData;
        mDataList.add(mCurrentData);
    }


    //点击后 --> 插入到数据库
    @Override
    public void submitData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                if (mPicList != null && mPicList.size()>0) {
                    Random random=new Random();
                    int i = random.nextInt(mPicList.size());
                    String s = mPicList.get(i);

                    //  String s1 = mPicList.get(mCurrentPos);
                    WordClips clips=new WordClips(mCurrentBookPos,mCurrentData,s,mDefaultClipsWordsSize,"",false);
                    try {
                        mClipsDao.insertWords(clips);
                    } catch (SQLiteConstraintException e) {
                        e.printStackTrace();
                        LogUtil.d(TAG,e.getMessage());
                    }
                    LogUtil.d(TAG, mCurrentBookPos +":"+mCurrentData+":"+s);
                }else{
                    WordClips clips=new WordClips(mCurrentBookPos,mCurrentData,"",mDefaultClipsWordsSize,"",false);
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
            }
        }).start();
    }

    @Override
    public void queryAllClips() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<WordClips> wordClips = mClipsDao.getSameNumWords(mCurrentBookPos);
                LogUtil.d(TAG,"wordClis size --> "+wordClips.size());
                for (ICollectionDialogCallback callback : mCallbacks) {
                    callback.showAllClips(wordClips);
                }
            }
        }).start();
    }

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


    public void getPicText(List<Words> list, int position) {
        Words words = list.get(position-1);
        mPicture = words.getPicture();
        mPicList.add(mPicture);
        this.mCurrentPos=position-1;
        LogUtil.d(TAG,"mpic --> "+mPicture);
    }
}
