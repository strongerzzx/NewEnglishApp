package commonparms;

import java.util.ArrayList;
import java.util.List;

import entirys.Words;

/**
 * 作者：zzx on 2020/9/29 13:28
 * <p>
 * 作用： xxxx
 */
public class Commons {
    //Home的base
    public static final String BASE_YOU_DAO="https://reciteword.youdao.com/";

    //Login的base
    public static final String BASE_LOGIN="http://47.100.170.185:8081/";

    //收藏dialog的数据库名
    public static final String DB_TYPE_WORDSCLIPS="words_clips_db";

    //百度OCR请求网址
    public static final String OCR_BAI_DU="https://aip.baidubce.com/";

    //获取到的AccessToken
    public static  String accessToken="";

    //百度文字识别
    public static final String COMPLETE_URL="https://aip.baidubce.com/rest/2.0/ocr/v1/general_basic";

    //百度翻译识别
    public static final String BAI_DU_TRANSLATE="http://api.fanyi.baidu.com/";


    public static final String appid = "20200911000563747";
    public static final String sercet = "Mq8ioNowJM83qXx6rCWU";
    public static final String salt = "1435660288";

    private static List<Words> mWordsList = new ArrayList<>();
    private static String mCurrentDownloadTitle;
    private static String mCurrentLoginAccount;

    public static void setWordsList(List<Words> wordsList) {
        mWordsList = wordsList;
    }

    public static List<Words> getWordsList() {
        return mWordsList;
    }

    public static void setTitleTemp(String title) {
        mCurrentDownloadTitle=title;
    }

    public static String getmCurrentDownloadTitle() {
        return mCurrentDownloadTitle;
    }

    public static void setAccount(String username) {
        mCurrentLoginAccount = username;
    }

    public static String getmCurrentLoginAccount() {
        return mCurrentLoginAccount;
    }
}
