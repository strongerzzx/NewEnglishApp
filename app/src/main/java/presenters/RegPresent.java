package presenters;

import android.util.Log;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apis.ApiService;
import beans.RegBeans;
import commonparms.Commons;
import interfaces.IRegCallback;
import interfaces.IRegPresent;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.LogUtil;

public class RegPresent implements IRegPresent {

    private static final String TAG = "RegPresent";
    private List<IRegCallback> mCallbacks=new ArrayList<>();
    private String mCurrentPswd;
    private String mCurrentAccount;

    private RegPresent() {
    }

    private static RegPresent sPresent;

    public static RegPresent getPresent() {
        if (sPresent==null){
            synchronized (RegPresent.class){
                if (sPresent==null){
                    sPresent=new RegPresent();
                }
            }
        }
        return sPresent;
    }



    public void getAccount(String account) {
        this.mCurrentAccount=account;

    }

    public void getPswd(String pswd) {
        this.mCurrentPswd=pswd;
    }

    @Override
    public void requestReg(onRegClickCallback regClickCallback) {
        Retrofit builder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Commons.BASE_LOGIN).build();
        ApiService api = builder.create(ApiService.class);

        Map<String, String> map = new HashMap<>();
        map.put("username", mCurrentAccount.trim());
        map.put("password", mCurrentPswd.trim());
        String s = new Gson().toJson(map);
        Log.d(TAG, "onClick: "+s);
        //post 提交必须要有这个body
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
        Call<RegBeans> task = api.regAccount(requestBody);
        task.enqueue(new Callback<RegBeans>() {
            @Override
            public void onResponse(Call<RegBeans> call, Response<RegBeans> response) {
                int code = response.code();
                LogUtil.d(TAG,"code --> "+code);
                if (code== HttpURLConnection.HTTP_OK){
                    String msg = response.body().getMsg();
                    boolean flag = response.body().isFlag();
                    LogUtil.d(TAG,":"+msg+":"+flag);//msg
                    LogUtil.d(TAG,"body --> "+response.body());

                    regClickCallback.onRegCallback(msg);
                }
            }

            @Override
            public void onFailure(Call<RegBeans> call, Throwable t) {
                LogUtil.d(TAG,"error --> "+t.toString());
                regClickCallback.onRegCallback(t.toString());
            }
        });
    }

    @Override
    public void regRegCallback(IRegCallback callback) {
        if (mCallbacks != null && mCallbacks.contains(callback)) {
            mCallbacks.add(callback);
        }
    }

    @Override
    public void unRegRegCallback(IRegCallback callback) {
        if (mCallbacks != null) {
            mCallbacks.remove(callback);
        }
    }

    public interface onRegClickCallback{
        void onRegCallback(String message);
    }
}
