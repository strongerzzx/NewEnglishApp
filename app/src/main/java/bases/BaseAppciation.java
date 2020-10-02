package bases;

import android.app.Application;
import android.content.Context;
import android.os.Handler;

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

        sHandler=new Handler();

    }

    public static Context getContext() {
        return sContext;
    }

    public static Handler getHandler() {
        return sHandler;
    }
}
