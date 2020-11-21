package presenters;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import commonparms.Commons;
import dao.SpellDao;
import dao.WordsDao;
import entirys.Spells;
import entirys.Words;
import interfaces.ISpellWordCallback;
import interfaces.ISpellWordPresenter;
import rooms.AllTaskDB;
import rooms.WordsDB;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/18 20:32
 * <p>
 * 作用： xxxx
 */
public class SpellWordPresenter implements ISpellWordPresenter {

    private static final String TAG = "SpellWordPresenter";
    private List<ISpellWordCallback> mCallbacks = new ArrayList<>();
    private int mCurrentIndex;
    private int mCurrentRange;

    private static volatile SpellWordPresenter sPresenter;
    private final WordsDao mWordsDao;
    private int mCurrentBookPos;
    private final SpellDao mSpellDao;

    private SpellWordPresenter() {
        WordsDB wordsDB = WordsDB.getWordsDB();
        mWordsDao = wordsDB.getWordsDao();
        AllTaskDB allTaskDB = AllTaskDB.getAllTaskDB();
        mSpellDao = allTaskDB.getSpellDao();
    }

    public static SpellWordPresenter getPresenter() {
        if (sPresenter==null){
            synchronized (SpellWordPresenter.class){
                if (sPresenter==null){
                    sPresenter=new SpellWordPresenter();
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
                int index = 0;
                index=random.nextInt(wordsList.size()-10);
                //获取随机的单词 len > 7
                while (true){
                    if (wordsList.get(index).getHeadWord().length()>7)
                        break;
                    else{
                        index=random.nextInt(wordsList.size()-10);
                    }
                }
                mCurrentIndex++;

                //返回完整的英文单词和解释
                Words word = wordsList.get(index);
                for (ISpellWordCallback callback : mCallbacks) {
                    callback.showCorrentWord(word.getHeadWord());
                    callback.showCorrectTra(word.getTran());
                    callback.showAllWords(wordsList,index);
                    callback.getCurrentIndex(mCurrentIndex);
                }
                LogUtil.d(TAG,"word len --> "+word.getHeadWord());

                //截取字符串
                doStubWord(word);

            }
        }).start();
    }

    @Override
    public void doRandomRange() {
        Random random=new Random();
        int range=0;
        range=random.nextInt(30);
        while (true){
            if (range>8)
                break;
            else{
                range=random.nextInt(30);
            }
        }
        for (ISpellWordCallback callback : mCallbacks) {
            callback.getMaxRange(range);
        }
    }

    @Override
    public void doClearIndex() {
        mCurrentIndex=0;
    }

    @Override
    public void doInsert2Room() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
                Date date=new Date(System.currentTimeMillis());
                String time = format.format(date);
//                Spells spells=new Spells(mCurrentBookPos,"2020-11-18",Commons.getmCurrentLoginAccount(),true);
                Spells spells=new Spells(mCurrentBookPos,time, Commons.getmCurrentLoginAccount(),true);
                mSpellDao.insertSpell(spells);

            }
        }).start();
    }

    //截取 --> replace
    private void doStubWord(Words word) {
        String headWord = word.getHeadWord();
        String substring = headWord.substring(2, headWord.length() - 3);//正确的截取
        String replace = headWord.replace(substring, "_");//返回content的单词
        for (ISpellWordCallback callback : mCallbacks) {
            callback.showStubContent(replace);
        }
        doRandomSub(substring);
    }
    //对截取的进行随机排序
    private void doRandomSub(String substring) {
        String error1="",error2="",error4="",error3="";
        String s1 = randomStr(substring, error1);
        String s2 = randomStr(substring, error2);
        String s3 = randomStr(substring, error3);
        String s4 = randomStr(substring, error4);
        while (true){
            if (!s1.equals(s2)  && !s1.equals(s3)  && !s1.equals(s4)  && !s2.equals(s3)  && !s2.equals(s4)  && !s3.equals(s4)
                     &&!s1.equals(substring) && !s2.equals(substring) && !s3.equals(substring) && !s4.equals(substring)) {
                LogUtil.d(TAG,"si --> "+s1+":"+s2+":"+s3+":"+s4);
                break;
            } else{
                s1 = randomStr(substring, error1);
                s2 = randomStr(substring, error2);
                s3 = randomStr(substring, error3);
                s4 = randomStr(substring, error4);
            }
        }
        for (ISpellWordCallback callback : mCallbacks) {
            callback.showStubErrorWord(substring,s1,s2,s3,s4);
        }
    }

    private String randomStr(String sub,String error){
        List<Character> mList=new ArrayList<>();
        char[] subChars = sub.toCharArray();
        mList.clear();
        for (int i = 0; i < subChars.length; i++) {
            mList.add(subChars[i]);
        }
        Collections.shuffle(mList);
        for (int i = 0; i < mList.size(); i++) {
            error+=String.valueOf(mList.get(i));
        }
        return error;
    }

    @Override
    public void regesiterView(ISpellWordCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(ISpellWordCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {
        this.mCurrentBookPos=currentBookNum;
    }
}
