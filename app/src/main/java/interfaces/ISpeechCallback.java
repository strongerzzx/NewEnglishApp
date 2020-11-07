package interfaces;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/24 13:09
 * <p>
 * 作用： xxxx
 */
public interface ISpeechCallback {
    //返回识别的字符
    void showRecongize(String result);

    //返回说话的分贝
    void showFenBei(double fb);

    //返回正确的单词
    void showCorrectWord(String correctWord,String fayin);

    //返回全部数据
    void showAllData(List<Words> mList, int anInt);

    //返回单词的意思
    void showWordChinese(String translate);

    void showTimes(int currentTime,int maxTime);
}

