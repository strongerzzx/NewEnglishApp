package utils;

import android.widget.Toast;

import bases.BaseAppciation;

public
/**
 * 作者：zzx on 2020/11/15 13:29
 *  作用： xxxx
 */
class ToastUtil {
    public static void showToast(String content){
        BaseAppciation.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(BaseAppciation.getContext(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }
}

