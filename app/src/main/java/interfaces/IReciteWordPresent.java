package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/10 11:46
 * <p>
 * 作用： xxxx
 */
public interface IReciteWordPresent extends BaseInterface<IReciteWordCallback> {
    //获取输入范围
    void getWordRange(String range);

    //获取词库中单词数据
    void getWordList();


    //完成任务 --> 写入数据库
    void doFinishTask();

    //获取完成的内容
    void getFinishInfo(String range, String compltetTime, String input);

    //是否要更新当天的上限
    void isRandomMaxUpper();
}
