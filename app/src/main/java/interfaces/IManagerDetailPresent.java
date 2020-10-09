package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/9 11:23
 * <p>
 * 作用： xxxx
 */
public interface IManagerDetailPresent extends BaseInterface<IMangerDetailCallback> {

    //查询收藏ID 对应的单词
    void queryCollectionIDForWords();

    //获取收藏夹的ID
    void getCollectionID(int clipsID);

    //获取单词ID
    void getWordsID(int wordsID);

    //删除单词
    void doDeleteWords();
}
