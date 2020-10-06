package views;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp_bishe.R;

import java.util.List;

import adapters.DialogCollectionAdapter;
import entirys.WordClips;
import interfaces.ICollectionDialogCallback;
import presenters.CollectionDialogPresent;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/4 18:51
 * <p>
 * 作用： xxxx
 */
public class CollectionDialog extends Dialog implements ICollectionDialogCallback {

    private static final String TAG = "CollectionDialog";
    private View mInflate;
    private RecyclerView mDialogRv;
    private DialogCollectionAdapter mAdapter;
    private CollectionDialogPresent mDialogPresent;


    public CollectionDialog(@NonNull Context context) {
        this(context,0);
    }

    public CollectionDialog(@NonNull Context context, int themeResId) {
        this(context, true,null);
    }

    protected CollectionDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        requestWindowFeature(Window.FEATURE_NO_TITLE);

        mInflate = LayoutInflater.from(getContext()).inflate(R.layout.dialog_collection_view, null);
        this.setContentView(mInflate);

        //设置大小
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.width= WindowManager.LayoutParams.MATCH_PARENT;
        //获取屏幕默认分辨率 --> 来适配
        Display defaultDisplay = getWindow().getWindowManager().getDefaultDisplay();
        int height=defaultDisplay.getHeight()/2;
        attributes.height= height;

        attributes.gravity=Gravity.CENTER;
        attributes.dimAmount=0.2f;
        getWindow().setAttributes(attributes);
        getWindow().setWindowAnimations(R.style.CustomDialogAnim);


        mDialogPresent = CollectionDialogPresent.getPresent();
        mDialogPresent.regesiterView(this);
        mDialogPresent.queryAllClips();

        initChildView();

        initEvent();
    }

    private void initEvent() {
        //
        mAdapter.setOnDialogCollectionHeaderViewClickListener(new DialogCollectionAdapter.OnDialogCollectionHeaderClickListener() {
            @Override
            public void onDialogCollectionHeaderClickListener(int position) {
                LogUtil.d(TAG,"hpos --> "+position);
                dismiss();

                //在自定义一个dialog
                NewWordsCollectionDialog dialog=new NewWordsCollectionDialog(getContext());
                dialog.show();

            }
        });

        //TODO:点击item ——>把单词收藏到点击的单词夹
        mAdapter.setOnDialogCollectionItemClickListener(new DialogCollectionAdapter.onDialogCollectionItemClickListener() {
            @Override
            public void onDialogCollectionItemClick(int pos) {

                LogUtil.d(TAG,"ipos-->"+pos);

            }
        });

        mDialogRv.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.getItemOffsets(outRect, view, parent, state);
                outRect.bottom=5;
                outRect.top=5;
            }
        });
    }


    private void initChildView() {
        mDialogRv = mInflate.findViewById(R.id.dialog_collection_rv);
        mDialogRv.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new DialogCollectionAdapter();
        mDialogRv.setAdapter(mAdapter);

        //设置头布局
        setRvHeaderView(mDialogRv);
    }

    private void setRvHeaderView(RecyclerView dialogRv) {
        View headerView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_collection_rv_header, dialogRv, false);
        mAdapter.setHeaderView(headerView);
    }


    @Override
    public void showAllClips(List<WordClips> clipsList) {
        //切回主线程
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                mAdapter.setData(clipsList);
            }
        });
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mDialogPresent != null) {
            mDialogPresent.unRegesiterView(this);
        }
    }
}
