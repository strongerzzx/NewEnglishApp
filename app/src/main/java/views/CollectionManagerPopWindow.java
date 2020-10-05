package views;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.example.englishapp_bishe.R;

/**
 * 作者：zzx on 2020/10/6 00:02
 * <p>
 * 作用： xxxx
 */
public class CollectionManagerPopWindow extends PopupWindow {
    public CollectionManagerPopWindow(Context context) {
        super(context);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        View inflate = LayoutInflater.from(context).inflate(R.layout.collecton_manager_pop_window, null);
        this.setContentView(inflate);
    }
}
