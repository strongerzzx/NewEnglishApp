package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/18 20:30
 * <p>
 * 作用： xxxx
 */
public interface ISpellWordPresenter extends BaseInterface<ISpellWordCallback> {
    //请求数据 ——-> 数据库
    void requestData();

    void doRandomRange();

    void doClearIndex();

    void doInsert2Room();
}
