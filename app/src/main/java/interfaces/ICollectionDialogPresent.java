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


    //提交 --> 插入数据库 新建收藏夹的Dialog
    void submitData();

    //查询数据 --> 并显示
    void queryAllClips();

    //点击单词 --> 插入到已存在的收藏夹中
    void doCollection2ExistFavorites();

    //查询并显示为true的单词
    void doQueryTrueWord(int pos);

    //删除收藏夹列表数据
    void deleCollectionData(int pos);

    //获取单词夹的名字
    void getClipsTitle(int pos,IManagerTitle title);

    //查询所有收藏夹数量
    void queryAllCollectionClipsNum();

    //获取单词夹中的单词
    void getClipsWords();

}
