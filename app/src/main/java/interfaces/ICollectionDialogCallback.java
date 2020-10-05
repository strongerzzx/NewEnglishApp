package interfaces;

import java.util.List;

import entirys.WordClips;

/**
 * 作者：zzx on 2020/10/5 16:32
 * <p>
 * 作用： xxxx
 */
public interface ICollectionDialogCallback {

    //返回查询的数据 ——-> 显示
    void showAllClips(List<WordClips> clipsList);
}
