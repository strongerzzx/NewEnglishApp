package bases;

/**
 * 作者：zzx on 2020/10/2 19:24
 * <p>
 * 作用： xxxx
 */
public interface BaseInterface<T> {

    void regesiterView(T callbak);

    void unRegesiterView(T callback);

    void getBookNum(int currentBookNum);
}
