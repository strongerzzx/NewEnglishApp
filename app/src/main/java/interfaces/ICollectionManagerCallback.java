package interfaces;

import java.util.List;

import entirys.WordClips;

/**
 * 作者：zzx on 2020/10/5 21:46
 * <p>
 * 作用： xxxx
 */
public interface ICollectionManagerCallback {
    //展示每个单词夹的信息
    void showCollectionInfo(List<WordClips> wordClips);

    //显示单词夹的所有个数
    void showCollectionSum(int size);

    void onLoading();

    void onFinish();
}
