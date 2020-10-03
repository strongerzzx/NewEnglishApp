package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/2 19:23
 * <p>
 * 作用： xxxx
 */
public interface ICikuPresent extends BaseInterface<ICikuCallback> {
    //查询所有单词
    void queryAllWords();

    //分页加载
    void doLoader();

    //点击收藏
    void doCollection();
}
