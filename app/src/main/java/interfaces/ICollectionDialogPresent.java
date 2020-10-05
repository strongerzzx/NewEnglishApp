package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/5 16:32
 * <p>
 * 作用： xxxx
 */
public interface ICollectionDialogPresent extends BaseInterface<ICollectionDialogCallback> {

    //获取View的数据
    void getViewData(String currentData);


    //提交 --> 插入数据库
    void submitData();

    //查询数据 --> 并显示
    void queryAllClips();
}
