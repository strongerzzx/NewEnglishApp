package interfaces;

import java.util.ArrayList;

public
/**
 * 作者：zzx on 2020/10/30 15:54
 *  作用： xxxx
 */
interface IStudyProgressCallback {
    //返回完成的次数 + 日期
    void showFinishTimes(int c2eCount,int listenerCount,int reciteCount,int spellCount,String date);

    //返回所有日期
    void showAllFinsihTimes(ArrayList<String> mTimes);

    //返回C2e任务数目
    void showC2eTask(int finishNum);

    //返回Spell任务数目
    void showSpellTask(int finishNum);

    //返回Listeners任务数目
    void showListenersTask(int finishNum);

    //返回ReciteWord任务数目
    void showReciteWordTask(int finishNum);

}

