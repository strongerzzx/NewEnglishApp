package interfaces;

/**
 * 作者：zzx on 2020/10/24 08:13
 * <p>
 * 作用： xxxx
 */
public  interface ITranslateCallback {
    //返回翻译后的数据
    void showTranslateResult(String result);

    //显示互换后的名字
    void showChangeName(String src,String dest);
}
