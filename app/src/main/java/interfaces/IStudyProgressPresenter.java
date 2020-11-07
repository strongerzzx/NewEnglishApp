package interfaces;

import bases.BaseInterface;

public
/**
 * 作者：zzx on 2020/10/30 15:54
 *  作用： xxxx
 */
interface IStudyProgressPresenter extends BaseInterface<IStudyProgressCallback> {
    //查询所有已完成的任务
    void doQueryFinishTask();

}
