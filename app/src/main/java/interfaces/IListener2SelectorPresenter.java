package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/18 14:22
 * <p>
 * 作用： xxxx
 */
public interface IListener2SelectorPresenter  extends BaseInterface<IListener2SelectorCallback> {
    //获取数据
    void requestData();

    //随机设置range
    void doRandomRange();

    //清除随机
    void doClearRandom();

    void doInsertData2Room();

}
