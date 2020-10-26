package interfaces;


import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/24 15:26
 *  作用： xxxx
 */
public interface IGameCallback {
    //显示title;
    void showTitle(String title);

    //显示正确和错误的答案
    void showCorrect(String correct);

    void showCorrectAndError(String correct,String error1,String error2,String error3,String error4);

    //显示出现过的words
    void showWinnerWordList(List<Words> mList);

}

