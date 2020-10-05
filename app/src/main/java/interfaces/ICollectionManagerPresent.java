package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/5 21:45
 * <p>
 * 作用： xxxx
 */
public interface ICollectionManagerPresent extends BaseInterface<ICollectionManagerCallback> {
    //查询获取收藏夹的信息
    void getCollectionsInfo();


}
