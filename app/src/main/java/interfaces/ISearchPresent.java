package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/2 21:13
 * <p>
 * 作用： xxxx
 */
public interface ISearchPresent extends BaseInterface<ISearchCallback> {
    //搜索
    void doSearchResult(String keyword);

    //联想搜索
    void doSuggestSearch(String suggest);
}
