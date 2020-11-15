package views;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.englishapp_bishe.R;

import java.util.ArrayList;
import java.util.List;

import bases.BaseAppciation;
import commonparms.Commons;
import entirys.WordClips;
import entirys.Words;
import interfaces.ICollectionDialogCallback;
import interfaces.IManagerTitle;
import presenters.CollectionDialogPresent;
import services.CollectionDownloadService;
import utils.LogUtil;

/**
 * 作者：zzx on 2020/10/6 00:02
 * <p>
 * 作用： xxxx
 */
public class CollectionManagerPopWindow extends PopupWindow implements ICollectionDialogCallback {

    private static final String TAG = "CollectionManagerPopWindow";
    private final View mInflate;
    private TextView mPopTitle;
    private final CollectionDialogPresent mDialogPresent;
    private List<Words> mWordsInClips =new ArrayList<>();
    private Handler mHandler = new Handler();
    private LinearLayout mDeleteLayout;
    private LinearLayout mDownloadLayout;

    public CollectionManagerPopWindow(Context context) {
        super(context);
        this.setWidth(WindowManager.LayoutParams.MATCH_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);

        this.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        this.setOutsideTouchable(true);

        mInflate = LayoutInflater.from(context).inflate(R.layout.collecton_manager_pop_window, null);
        this.setContentView(mInflate);
        this.setAnimationStyle(R.style.CustonCollectionPop);

        mDialogPresent = CollectionDialogPresent.getPresent();
        mDialogPresent.regesiterView(this);

        initView();
    }

    private void initView() {
        mPopTitle = mInflate.findViewById(R.id.collection_pop_title);
        mDeleteLayout = mInflate.findViewById(R.id.collection_delte_layout);
        mDownloadLayout = mInflate.findViewById(R.id.collection_download_layout);
    }

    @Override
    public void dismiss() {
        super.dismiss();
        if (mDialogPresent != null) {
            mDialogPresent.unRegesiterView(this);
        }
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
        }
    }

    @Override
    public void showAllClips(List<WordClips> clipsList) {

    }

    @Override
    public void getAllClipsTitle(String title) {

    }

    @Override
    public void showClipsNum(int size) {

    }

    @Override
    public void getWordInCollection(List<Words> mCollectionWords) {
        mWordsInClips.clear();
        if (mCollectionWords != null) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mWordsInClips.addAll(mCollectionWords);
                        LogUtil.d(TAG,"收藏夹大小 --> "+mCollectionWords.size());
                    }
                });
        }
    }


    //获取Rv中点击的哪个
    public void getManageInfoPos(int pos) {

        //更新Pop中的标题
        mDialogPresent.getClipsTitle(pos, new IManagerTitle() {
            @Override
            public void GetManagerTitle(String title) {
                mPopTitle.setText("单词夹: "+title);
                Commons.setTitleTemp(title);
            }
        });

        LogUtil.d(TAG,"pos --> "+pos);


        //点击删除整个单词夹
        mDeleteLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogPresent.deleCollectionData(pos);
                dismiss();
            }
        });

        //下载收藏夹中的单词 --> 前台服务显示
        mDownloadLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialogPresent.getClipsTitle(pos, new IManagerTitle() {
                    @Override
                    public void GetManagerTitle(String title) {
                        mDialogPresent.doQueryTrueWord(pos);
                        Intent downIntent = new Intent(BaseAppciation.getContext(), CollectionDownloadService.class);
//                        Intent downIntent = new Intent(v.getContext(), MyService.class);
                        downIntent.putExtra("name",title);
                        BaseAppciation.getContext().startService(downIntent);

                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                dismiss();
                            }
                        });
                    }
                });
            }
        });
    }
}
