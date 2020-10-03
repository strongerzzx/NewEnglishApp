package interfaces;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/2 19:24
 * <p>
 * 作用： xxxx
 */
public interface ICikuCallback {
    //展示全部单词
    void showAllWords(List<Words> mList);

    //上啦刷新
    void onPullRefresh(int size);


}
