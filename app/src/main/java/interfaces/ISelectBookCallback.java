package interfaces;

import java.util.List;

import beans.SelectBookBeans;

public interface ISelectBookCallback {

    //获取书籍的数据
    void showBookList(List<SelectBookBeans.CatesBean.BookListBean> bookListBeans);

    //上啦刷新
    void pullRefreshMore(int size);


    //加载中
    void onLoading();

    //网络错误
    void onNetworkError();

    //获取当前Zip下载进度
    void downZipProgress(int currentPos,long finalPos);
}
