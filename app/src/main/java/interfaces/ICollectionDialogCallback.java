package interfaces;

import java.util.List;

import entirys.WordClips;
import entirys.Words;

/**
 * 作者：zzx on 2020/10/5 16:32
 * <p>
 * 作用： xxxx
 */
public interface ICollectionDialogCallback {

    //返回查询的数据 ——-> 显示
    void showAllClips(List<WordClips> clipsList);


    //获取每个单词夹的名字
    void getAllClipsTitle(String title);

    //返回单词夹数量
    void showClipsNum(int size);

    //获取收藏夹内的单词
    void getWordInCollection(List<Words> mCollectionWords);
}
