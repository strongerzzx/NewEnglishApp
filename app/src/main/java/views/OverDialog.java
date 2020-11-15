package views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishapp_bishe.HomeActivity;
import com.example.englishapp_bishe.R;


/**
 * 作者：zzx on 2020/10/17 21:30
 * <p>
 * 作用： xxxx
 */
public class OverDialog extends Dialog {

    private static final String TAG = "OverDialog";
    private View mInflate;
    private TextView mTitle;
    private Button mKnow;
    private Activity mActivity;


    public OverDialog(@NonNull Context context) {
        this(context,0);
    }

    public OverDialog(@NonNull Context context, int themeResId) {
        this(context, false,null);
    }

    protected OverDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_over_game, null);
        this.setContentView(mInflate);


        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.gravity= Gravity.CENTER;
        attributes.dimAmount=0.2f;
        getWindow().setAttributes(attributes);



        initView();

        initEvent();


    }

    private void initEvent() {
        mKnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), HomeActivity.class);
                getContext().startActivity(intent);

                if (mActivity != null) {
                    mActivity.finish();
                }
            }
        });
    }

    private void initView() {
        mTitle = findViewById(R.id.over_tv);
        mKnow = findViewById(R.id.over_btn);


    }

    public void setActivity(Activity activity) {
        mActivity = activity;
    }
}
