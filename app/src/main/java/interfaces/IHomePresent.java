package interfaces;

/**
 * 作者：zzx on 2020/10/2 15:50
 * <p>
 * 作用： xxxx
 */
public interface IHomePresent {
    //搜索单词
    void queryWords();

    //获取点击词书的号数
    void getBookNum(int position);

    void regesiterHomeView(IHomeCallback callback);

    void unRegesiterHomeView(IHomeCallback callback);
}
