package interfaces;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/10 11:46
 * <p>
 * 作用： xxxx
 */
public interface IReciteWordCallback {
    //显示输入的单词范围
    void showRange(String range);

    //显示所有单词
    void showAllWordsList(List<Words> mWords);


    //显示背单词的次数
    void showCurrentSize(int currentSize);

    //显示背单词的最大上限
    void showMaxSize(int maxSize);
}
