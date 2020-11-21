package views;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.englishapp_bishe.R;

import bases.BaseAppciation;
import presenters.CollectionDialogPresent;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/5 14:36
 * <p>
 * 作用： xxxx
 */
public class NewWordsCollectionDialog extends Dialog{
    private static final String TAG = "NewWordsCollectionDialog";

    private View mInflate;
    private TextView mCancel;
    private TextView mSubmit;
    private EditText mInput;
    private InputMethodManager mIm;
    private CollectionDialogPresent mPresent;


    public NewWordsCollectionDialog(@NonNull Context context) {
        this(context,0);
    }

    public NewWordsCollectionDialog(@NonNull Context context, int themeResId) {
        this(context, true,null);
    }

    protected NewWordsCollectionDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mIm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);

        mInflate = LayoutInflater.from(getContext()).inflate(R.layout.new_collection_dialog, null);
        setContentView(mInflate);

        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width= WindowManager.LayoutParams.MATCH_PARENT;
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        int heigt=defaultDisplay.getHeight()/4;
        LogUtil.d(TAG,"height --> "+heigt);
        attributes.height=heigt;
        attributes.dimAmount=0.2f;
        attributes.gravity= Gravity.CENTER;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.NewCustomDialogAnim);


        initChilidView();

        initEvent();
    }

    public void ToastShow(String content){
        BaseAppciation.getHandler().post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEvent() {
        //设置键盘弹出
        mInput.postDelayed(new Runnable() {
            @Override
            public void run() {
                mInput.requestFocus();
                mIm.showSoftInput(mInput,InputMethodManager.SHOW_IMPLICIT);
            }
        },500);


        //填写数据源  -->  插入数据库  --> 并且直接收藏在点击新创的单词夹
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIm.hideSoftInputFromWindow(mInput.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
                String title = mInput.getText().toString();
                mPresent = CollectionDialogPresent.getPresent();
                //先查询 要添加的收藏夹 --> 是否已经存在
                 mPresent.doExistTitle(new CollectionDialogPresent.JudegetTitleIsExist() {
                    @Override
                    public void onJudgeTitleExist(boolean isExist) {
                        if (isExist) {
                            ToastShow("该收藏夹已存在...");
                        }else{
                            ToastShow("添加"+title+"成功...");
                        }
                    }
                });

                //获取标题
                mPresent.getViewData(title);
                mPresent.submitData();
                dismiss();
            }
        });


        //取消 --> Dialog消失
        mCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mIm.hideSoftInputFromWindow(mInput.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
                dismiss();
            }
        });

        //改变Hint样式
        String hineStr="请输入:";
        SpannableString ss=new SpannableString(hineStr);
        AbsoluteSizeSpan ass=new AbsoluteSizeSpan(16,true);
        mInput.setHintTextColor(Color.parseColor("#B04D4D4D"));
        ss.setSpan(ass,0,ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        mInput.setHint(new SpannableString(ss));

    }

    private void initChilidView() {
        mInput = mInflate.findViewById(R.id.new_collection_input);
        mSubmit = mInflate.findViewById(R.id.new_collection_submit);
        mCancel = mInflate.findViewById(R.id.new_collection_cancel);
    }

}
