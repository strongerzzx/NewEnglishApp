package utils;

import android.content.Context;

/**
 * 作者：zzx on 2020/10/4 20:01
 * <p>
 * 作用： xxxx
 */
public class UiUtil {
    public static int dp2px(Context context, int dp){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    public static int px2dip(Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
