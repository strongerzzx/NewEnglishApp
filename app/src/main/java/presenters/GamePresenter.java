package presenters;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import dao.WordsDao;
import entirys.Words;
import interfaces.IGameCallback;
import interfaces.IGamePresent;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/24 15:31
 *  作用： xxxx
 */
public class GamePresenter implements IGamePresent {

    private static final String TAG = "GamePresenter";
    private List<IGameCallback> mCallbacks=new ArrayList<>();
    private static volatile GamePresenter sPresenter;
    private int mCurrentBookNum;
    private final WordsDao mWordsDao;

    private List<Words> mRandomWords=new ArrayList<>();

    private GamePresenter() {
        WordsDB wordsDB = WordsDB.getWordsDB();
        mWordsDao = wordsDB.getWordsDao();
    }

    public static GamePresenter getPresenter() {
        if (sPresenter==null){
            synchronized (SpeechPresent.class){
                if (sPresenter==null){
                    sPresenter=new GamePresenter();
                }
            }
        }
        return sPresenter;
    }

    @Override
    public void doQueryData() {

        Random random=new Random();

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Words> wordsList = mWordsDao.getSameNumWords(mCurrentBookNum);
                int index = random.nextInt(wordsList.size() - 10);
                Words words = wordsList.get(index);
                String headWord = words.getHeadWord();//当前Title
                String tran = words.getTran();//当前正确的解释


                //每一次都会缓存这些随机的单词
                mRandomWords.add(words);

                LogUtil.d(TAG,"randomWors size --> "+mRandomWords.size());
                //全部回调给V
                for (IGameCallback callback : mCallbacks) {
                    callback.showCorrect(tran);
                    callback.showTitle(headWord);
                    callback.showCorrectAndError(tran,wordsList.get(index-1).getTran(),wordsList.get(index+1).getTran()
                    ,wordsList.get(index-2).getTran(),wordsList.get(index+2).getTran());
                    callback.showWinnerWordList(mRandomWords);
                }
            }
        }).start();

    }

    @Override
    public void regesiterView(IGameCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(IGameCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookNum=currentBookNum;
    }

    public List<Words> getRandomWords() {
        return mRandomWords;
    }
}

