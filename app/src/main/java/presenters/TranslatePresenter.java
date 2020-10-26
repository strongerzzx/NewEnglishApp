package presenters;


import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apis.ApiService;
import beans.BaiduTranslateBeas;
import commonparms.Commons;
import interfaces.ITranslateCallback;
import interfaces.ITranslatePresenter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.String2MD5Utils;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/24 08:15
 *  作用： xxxx
 */
public class TranslatePresenter implements ITranslatePresenter {


    private static final String TAG = "TranslatePresenter";

    private List<ITranslateCallback> mCallbacks=new ArrayList<>();
    private String mCurrentInput;
    private String mSign;
    private int mCurrentClickIndex=1;

    private TranslatePresenter() {
    }

    private static volatile TranslatePresenter sPresenter;

    public static TranslatePresenter getPresenter() {
        if (sPresenter==null){
            synchronized (TranslatePresenter.class){
                if (sPresenter==null){
                    sPresenter=new TranslatePresenter();
                }
            }
        }
        return sPresenter;
    }

    @Override
    public void doMixSign() {
        String temp=Commons.appid+mCurrentInput+Commons.salt+Commons.sercet;
        mSign = doMD5(temp);
    }

    private String doMD5(String temp) {
        String sigin = String2MD5Utils.hashKeyForCache(temp);
        return sigin;
    }

    @Override
    public void doTranslate(String from, String to) {

        Map<String,String> map=new HashMap<>();
        map.put("q",mCurrentInput);
        map.put("from",from);//en
        map.put("to",to);//zh
        map.put("appid",Commons.appid);
        map.put("salt",Commons.salt);
        map.put("sign",mSign);

        Retrofit build = new Retrofit.Builder().baseUrl(Commons.BAI_DU_TRANSLATE).addConverterFactory(GsonConverterFactory.create()).build();
        ApiService api = build.create(ApiService.class);
        Call<BaiduTranslateBeas> call = api.getTranslateChinese(map);
        call.enqueue(new Callback<BaiduTranslateBeas>() {
            @Override
            public void onResponse(Call<BaiduTranslateBeas> call, Response<BaiduTranslateBeas> response) {
                int code = response.code();
                LogUtil.d(TAG,"translate code ---> "+code);
                if (code== HttpURLConnection.HTTP_OK){
                    List<BaiduTranslateBeas.TransResultBean> trans_result = response.body().getTrans_result();
                    if (trans_result!=null){
                        for (BaiduTranslateBeas.TransResultBean transResultBean : trans_result) {
                            mCallbacks.get(0).showTranslateResult(transResultBean.getDst());
                            LogUtil.d(TAG,"beans --> "+transResultBean.getSrc()+":"+transResultBean.getDst());
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<BaiduTranslateBeas> call, Throwable t) {
                LogUtil.d(TAG,"error --> "+t.toString());
            }
        });

    }

    @Override
    public void getData(String data) {
        this.mCurrentInput =data;
    }

    @Override
    public void doExchangeSrc(String src, String dest) {
        mCurrentClickIndex++;
        if (mCurrentClickIndex%2==0){
            String temp=src;
            src=dest;
            dest=temp;

            for (ITranslateCallback callback : mCallbacks) {
                callback.showChangeName(src,dest);
            }
            return;
        }
        for (ITranslateCallback callback : mCallbacks) {
                callback.showChangeName("英文","中文");
        }

    }

    @Override
    public void regesiterView(ITranslateCallback callbak) {
        if (mCallbacks != null && !mCallbacks.contains(callbak)) {
            mCallbacks.add(callbak);
        }
    }

    @Override
    public void unRegesiterView(ITranslateCallback callback) {
        if (mCallbacks != null && !mCallbacks.isEmpty()) {
            mCallbacks.remove(callback);
        }
    }

    @Override
    public void getBookNum(int currentBookNum) {

    }
}

