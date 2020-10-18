package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/16 16:32
 * <p>
 * 作用： xxxx
 */
public interface IChinese2EnglishPresenter extends BaseInterface<IChineses2EnglishCallback> {
    //获取数据
    void requestData();


    //显示最终进度
    void doFinalProgress();

    //插入完成的记录 --> 数据库
    void doInsertChineseRecord();

}
