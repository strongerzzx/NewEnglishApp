package views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishapp_bishe.R;

public class CustomProgressDialog extends Dialog {

    private View mInflate;
    private TextView mTvLoad;

    public CustomProgressDialog(@NonNull Context context) {
        this(context,0);
    }

    public CustomProgressDialog(@NonNull Context context, int themeResId) {
        this(context, false,null);
    }

    protected CustomProgressDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_loading_view, null);
        setContentView(mInflate);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent); //去除Dialog的白边
        getWindow().getAttributes().gravity = Gravity.CENTER;
        getWindow().getAttributes().dimAmount = 0.2f;

        initView();
    }

    private void initView() {
        mTvLoad = mInflate.findViewById(R.id.tv_message);
    }

}
