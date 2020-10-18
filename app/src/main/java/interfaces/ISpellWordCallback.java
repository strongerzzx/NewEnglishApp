package interfaces;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/18 20:30
 * <p>
 * 作用： xxxx
 */
public interface ISpellWordCallback {
    //返回正确的单词
    void showCorrentWord(String ernglish);

    //返回正确的解释
    void showCorrectTra(String tra);

    //返回截取的单词 --> content
    void showStubContent(String subContent);

    //返回错误的情况
    void showStubErrorWord(String correct,String error1,String error2,String error3,String error4);

    //获取数据 --> pop
    void showAllWords(List<Words> mWords,int index);

    //获取最大范围
    void getMaxRange(int maxRange);

    //获取当前次数
    void getCurrentIndex(int currentIndex);
}
