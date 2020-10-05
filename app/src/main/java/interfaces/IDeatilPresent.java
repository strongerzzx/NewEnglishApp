package interfaces;

import java.util.List;

import bases.BaseInterface;
import entirys.Words;

/**
 * 作者：zzx on 2020/10/4 15:49
 * <p>
 * 作用： xxxx
 */
public interface IDeatilPresent extends BaseInterface<IDeatilCallback> {
    //获取词库中点击的数据数据
    void getCikuData(int clickPosition, List<Words> currentWords);

    void getData();
}
