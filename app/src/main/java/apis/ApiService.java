package apis;

import java.util.Map;

import beans.BaiduTokenBeans;
import beans.BaiduTranslateBeas;
import beans.CollectionBeans;
import beans.GetCollectionBeans;
import beans.LoginBeans;
import beans.OcrBeans;
import beans.RegBeans;
import beans.SelectBookBeans;
import commonparms.Commons;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * 作者：zzx on 2020/9/29 13:27
 * <p>
 * 作用： xxxx
 */
public interface ApiService {

    //这是选择词书的API
    @Headers("urlname:text1")
    @GET("reciteword/v1/books?le=en&sv=1.1")
    Call<SelectBookBeans> getSelectJson();

    //下载Zip --> 到SD卡
    @GET("{a}")
    Call<ResponseBody> downloaZip(@Path("a")String parms);


    //Home页面点击刷新一个单词的API
    @Headers("urlname:text1")
    @GET(Commons.BASE_YOU_DAO+"{a}")
    Call<ResponseBody> getSingleJson(@Path("a")String parms);


    //注册账号
    @Headers({"Content-Type:application/json;charset=UTF-8","User-Agent:Retrofit-your-App","urlname:text2"})
    @POST("register")
    Call<RegBeans> regAccount(@Body RequestBody requestBody);


    //登陆
    @Headers({"Content-Type:application/json;charset=UTF-8","User-Agent:Retrofit-your-App","urlname:text2"})
    @POST("login")
    Call<LoginBeans> doLogin(@Body RequestBody requestBody);


    //点击收藏
    @Headers({"Content-Type:application/json;charset=UTF-8","User-Agent:Retrofit-your-App","urlname:text2"})
    @POST("favorites")
    Call<CollectionBeans> doCollection(@Body RequestBody requestBody);


    //获取全部收藏的内容
    @Headers({"Content-Type:application/json;charset=UTF-8","User-Agent:Retrofit-your-App","urlname:text2"})
    @POST("findall")
    Call<GetCollectionBeans>getCollection(@Body RequestBody body);

    //百度OCR
    @Headers("Content-Type:application/x-www-form-urlencoded")
    @FormUrlEncoded
    @POST("rest/2.0/ocr/v1/general_basic")
    Call<OcrBeans>getOcrData(@Field("access_token") String accessToken, @Field("image")String image);
    //Call<HandWriteBeans>getOcrData(@Field("access_token") String accessToken, @Field("image")String image);


    //获取百度的Token
    @GET("oauth/2.0/token")
    Call<BaiduTokenBeans> getAccessToken(@QueryMap Map<String,String> map );


    //百度翻译API
    @GET("api/trans/vip/translate")
    Call<BaiduTranslateBeas> getTranslateChinese(@QueryMap Map<String,String> map);
}
