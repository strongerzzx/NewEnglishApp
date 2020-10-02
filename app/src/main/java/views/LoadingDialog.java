package views;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishapp_bishe.R;

public class LoadingDialog  extends Dialog {

    public LoadingDialog(@NonNull Context context) {
        this(context,0);
    }

    public LoadingDialog(@NonNull Context context, int themeResId) {
        this(context, true,null);
    }

    protected LoadingDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);

        setContentView(R.layout.dialog_loading_view);
        setCancelable(false);

    }
}
