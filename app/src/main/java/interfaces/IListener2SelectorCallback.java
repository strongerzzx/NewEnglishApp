package interfaces;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/18 14:22
 * <p>
 * 作用： xxxx
 */
public interface IListener2SelectorCallback {
    //显示正确单词
    void showCorrect(String correctEnlish);

    //显示错误翻译和正确的翻译
    void showError(String correctChinese,String error1,String error2,String error3,String error4);

    //获取最大上限
    void getRange(int maxRang);

    //获取当前index
    void getCurrentIndex(int index);

    //获取全部数据给Pop
    void showAllData(List<Words> mWords,int index);
}
