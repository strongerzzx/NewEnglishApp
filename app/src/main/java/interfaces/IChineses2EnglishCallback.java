package interfaces;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/16 16:32
 * <p>
 * 作用： xxxx
 */
public interface IChineses2EnglishCallback {
    //显示Content
    void showContent(String content);

    //返回随机的内容
    void showAllStr(String correct,String error1,String error2,String error3,String error4);

    //返回当前的进度
    void showCurrentProgress(int currentProgress);

    //返回总进度
    void showAllProgress(int sumProgress);

    //返回数据给popWinodw
    void getData2Pop(List<Words> mWords,int currentIndex,int finalyProgress,int currentProgress);
}
