package interfaces;

public interface ISelectBookPresent {

    //获取书籍的数据
    void requestBook();

    //分页加载
    void doLoadMore();

    //注册回调接口
    void regestierSelectBookCallback(ISelectBookCallback callback);

    //取消回调接口
    void unRegestierSelectBookCallback(ISelectBookCallback callback);

    //获取RecyclerView点击哪个Item
    void requestPositionZip(int pos);



}
