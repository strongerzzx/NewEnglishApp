package bases;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

/**
 * 作者：zzx on 2020/9/29 13:56
 * <p>
 * 作用： xxxx
 */
public class BaseAppciation extends Application {
    private static Context sContext;
    private static Handler sHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=5f968a16");
        sHandler=new Handler();

    }

    public static Context getContext() {
        return sContext;
    }

    public static Handler getHandler() {
        return sHandler;
    }
}
