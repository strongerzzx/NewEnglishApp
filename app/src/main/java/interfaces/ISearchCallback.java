package interfaces;

import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/10/2 21:13
 * <p>
 * 作用： xxxx
 */
public interface ISearchCallback {
    //显示搜索结果
    void showSearchResult(List<Words> searchWords);

    //显示联想词
    void showSearchSuggest(List<Words> suggestWords);

    //找不到 --> 空数据
    void onEmpty();

    //有数据
    void onSuccessData();
}
