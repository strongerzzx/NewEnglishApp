package presenters;

import android.util.Log;

import com.google.gson.Gson;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import apis.ApiService;
import beans.LoginBeans;
import commonparms.Commons;
import interfaces.ILoginCallback;
import interfaces.ILoginPresent;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import utils.LogUtil;

public class LoginPresent implements ILoginPresent {

    private static final String BASE_LOGIN="http://47.100.170.185:8081/";
    private static final String TAG = "LoginPresent";
    private List<ILoginCallback>mCallbackList=new ArrayList<>();
    private String mCurrentPswd;
    private String mCurrentUser;

    private LoginPresent() {
    }

    private volatile static LoginPresent sPresent;

    public static LoginPresent getPresent() {
        if (sPresent==null){
            synchronized (LoginPresent.class){
                if (sPresent==null){
                    sPresent=new LoginPresent();
                }
            }
        }
        return sPresent;
    }


    public void setUser(String user) {
        this.mCurrentUser=user;
    }

    public void setPswd(String pswd) {
        this.mCurrentPswd=pswd;
    }

    @Override
    public void doLogin() {
        Retrofit builder = new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(Commons.BASE_LOGIN).build();
        ApiService api = builder.create(ApiService.class);
        Map<String,String> map=new HashMap<>();
        map.put("username",mCurrentUser.trim());
        map.put("password",mCurrentPswd.trim());

        String s = new Gson().toJson(map);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), s);
        Call<LoginBeans> login = api.doLogin(requestBody);
        login.enqueue(new Callback<LoginBeans>() {
            @Override
            public void onResponse(Call<LoginBeans> call, Response<LoginBeans> response) {
                LogUtil.d(TAG,"code --> "+response.code());
                if (response.code()== HttpURLConnection.HTTP_OK){
                    if (response.body().isFlag()) {
                        //dialog.dismiss();
                        String username = response.body().getData().getUsername();
                        String password = response.body().getData().getPassword();
                        Headers headers = response.headers();
                        long progress = headers.byteCount();
                        LogUtil.d(TAG,"login count --> "+progress);
                        for (ILoginCallback iLoginCallback : mCallbackList) {
                            iLoginCallback.getLoadingLength(progress);
                        }
                        int id = response.body().getData().getId();

                        //TODO:收藏要获取我登陆的ID

                        //CollectionPresent.getPresent().commitID(id);
                        LogUtil.d(TAG,id+":"+username+":"+password);
                        LogUtil.d(TAG,"response --> "+response);
                    }else {
                        Log.d(TAG, "onResponse: "+response.body().getMsg());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginBeans> call, Throwable t) {
                LogUtil.d(TAG,"error msg --> "+t.toString());
            }
        });
    }

    @Override
    public void regLoginCallback(ILoginCallback callback) {
        if (mCallbackList != null && !mCallbackList.contains(callback)) {
            mCallbackList.add(callback);
        }
    }

    @Override
    public void unReLoginCallback(ILoginCallback callback) {
        if (mCallbackList != null) {
            mCallbackList.remove(callback);
        }
    }
}
