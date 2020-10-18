package presenters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import dao.AllTaskDao;
import dao.ListenerDao;
import dao.WordsDao;
import entirys.Listeners;
import entirys.Words;
import interfaces.IListener2SelectorCallback;
import interfaces.IListener2SelectorPresenter;
import rooms.AllTaskDB;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/18 14:25
 * <p>
 * 作用： xxxx
 */
public class Listener2SelectorPresenter implements IListener2SelectorPresenter {

    private static final String TAG = "Listener2SelectorPresenter";
    private List<IListener2SelectorCallback> mCallbacks=new ArrayList<>();
    private final WordsDao mWordsDao;
    private int mCurrentBookPos;//当前点击的书
    private int mRange;
    private int mCurrentIndex;
    private final ListenerDao mListenerDao;
    private final AllTaskDao mLearnTaskDao;

    private Listener2SelectorPresenter() {
        WordsDB wordsDB = WordsDB.getWordsDB();
        mWordsDao = wordsDB.getWordsDao();

        AllTaskDB allTaskDB = AllTaskDB.getAllTaskDB();
        mListenerDao = allTaskDB.getListenerDao();
        mLearnTaskDao = allTaskDB.getLearnTaskDao();
    }

    private static volatile Listener2SelectorPresenter sPresenter;

    public static Listener2SelectorPresenter getPresenter() {
        if (sPresenter==null){
            synchronized (Listener2SelectorPresenter.class){
                if (sPresenter==null){
                    sPresenter=new Listener2SelectorPresenter();
                }
            }
        }
        return sPresenter;
    }

    @Override
    public void requestData() {
        Random random=new Random();
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<Words> wordsList = mWordsDao.getSameNumWords(mCurrentBookPos);
                int index = random.nextInt(wordsList.size() - 10);
                Words words = wordsList.get(index);
                mCurrentIndex++;
                for (IListener2SelectorCallback callback : mCallbacks) {
                    //回调正确的英文
                    callback.showCorrect(words.getHeadWord());

                    //回调数据给pop
                    callback.showAllData(wordsList,index);

                    //所有错误和正确的翻译
                    callback.showError(words.getTran(),wordsList.get(index-1).getTran(),wordsList.get(index+1).getTran()
                            ,wordsList.get(index+2).getTran(),wordsList.get(index-2).getTran());

                    //返回当前次数
                    callback.getCurrentIndex(mCurrentIndex);
                }
            }
        }).start();
    }

    @Override
    public void doRandomRange() {
        Random random=new Random();
        mRange = random.nextInt(30);
        while (true){
            if (mRange>8){
                break;
            }else{
                mRange=random.nextInt(30);
            }
        }
        for (IListener2SelectorCallback callback : mCallbacks) {
            callback.getRange(mRange);
        }

    }

    @Override
    public void doClearRandom() {
        mCurrentIndex=0;
    }

    @Override
    public void doInsertData2Room() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                //插入到自己的表
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date(System.currentTimeMillis());
                String time = format.format(date);
                Listeners listener=new Listeners(mCurrentBookPos,time,true);
                mListenerDao.insertListener(listener);

                //查询表中最新的ID
                List<Listeners> listeners = mListenerDao.getAllListeners(mCurrentBookPos);
                int id = listeners.get(listeners.size() - 1).getId();

                LogUtil.d(TAG,"listenrs id --> "+id);

                //TODO:插入到关系表
            }
        }).start();
    }

    @Override
    public void regesiterView(IListener2SelectorCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(IListener2SelectorCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookPos=currentBookNum;
    }
}
