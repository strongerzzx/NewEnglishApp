package interfaces;

import bases.BaseInterface;

/**
 * 作者：zzx on 2020/10/24 08:13
 * <p>
 * 作用： xxxx
 */
public  interface ITranslatePresenter extends BaseInterface<ITranslateCallback> {
    //生成签名
    void doMixSign();

    //开始翻译
    void doTranslate(String from, String to);

    //获取要翻译的字符串
    void getData(String data);

    //每次点击 就 交互 src 和 dest
    void doExchangeSrc(String src, String dest);
}
