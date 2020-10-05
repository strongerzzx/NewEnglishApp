package fragments;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp_bishe.R;

import java.util.ArrayList;
import java.util.List;

import adapters.CollectionManagerAdapter;
import entirys.WordClips;
import interfaces.ICollectionManagerCallback;
import presenters.CollectionManagerPresenter;
import utils.LogUtil;
import views.CollectionManagerPopWindow;
import views.NewWordsCollectionDialog;
import views.UILoader;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment implements ICollectionManagerCallback {


    private static final String TAG = "CollectionFragment";
    private View mInflate;
    private TextView mCollectionSum;
    private ImageView mCollectionAdd;
    private RecyclerView mManagerRv;
    private CollectionManagerAdapter mManagerAdapter;
    private CollectionManagerPresenter mManagerPresenter;
    private UILoader mUiLoader;
    private List<WordClips> mClips =new ArrayList<>();

    public CollectionFragment() {
        // Required empty public constructor
    }

    //TODO:显示所有收藏夹 --> 点击则显示对应的单词
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LogUtil.d(TAG,"onCreateView ");
        if (mUiLoader==null){
            mUiLoader = new UILoader(container.getContext()) {
                @Override
                protected View getSuccessView(ViewGroup container) {
                    return createSuccessView();
                }
            };
        }
        if (mUiLoader.getParent() instanceof ViewGroup) {
            ((ViewGroup) mUiLoader.getParent()).removeView(mUiLoader);
        }


        mInflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_collection, container, false);
        FrameLayout contentLayout=mInflate.findViewById(R.id.collection_manager_content);
        contentLayout.addView(mUiLoader);

        mManagerPresenter = CollectionManagerPresenter.getPresenter();
        mManagerPresenter.regesiterView(this);

        initView();


        return mInflate;
    }

    private View createSuccessView() {
        View successView = LayoutInflater.from(getContext()).inflate(R.layout.collection_manager_success_rv, null);
        mManagerRv=successView.findViewById(R.id.collection_manager_rv);
        mManagerRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mManagerAdapter = new CollectionManagerAdapter();
        mManagerRv.setAdapter(mManagerAdapter);
        return successView;
    }

    private void initView() {
        mCollectionSum = mInflate.findViewById(R.id.collection_manager_sum);
        mCollectionAdd = mInflate.findViewById(R.id.collection_manager_add);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        LogUtil.d(TAG,"onActivityCreated");
        initEvent();
    }

    @Override
    public void onResume() {
        super.onResume();
        //清空防止数据叠加
        mClips.clear();
        mManagerPresenter.getCollectionsInfo();
        LogUtil.d(TAG,"onResume");
        LogUtil.d(TAG,"刷新数据1");
    }


    private void initEvent() {
        mCollectionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewWordsCollectionDialog newDialog=new NewWordsCollectionDialog(getContext());
                newDialog.show();
            }
        });

        //TODO:点击更多 --> 弹出PopWindow 可以下载 删除
        mManagerAdapter.setOnCollectionManagerClickListener(new CollectionManagerAdapter.onCollectionManagerClickListener() {
            @Override
            public void onCollectionManagerClick(int pos) {
                CollectionManagerPopWindow pop=new CollectionManagerPopWindow(getContext());
                pop.showAsDropDown(mCollectionSum, 0,0, Gravity.BOTTOM);
                LogUtil.d(TAG,"pos --> "+pos);
            }
        });
    }

    @Override
    public void showCollectionInfo(List<WordClips> wordClips) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mClips.clear();
                if (wordClips != null) {
                    mClips.addAll(wordClips);
                    mManagerAdapter.setData(mClips);
                }
            }
        });
    }

    @Override
    public void showCollectionSum(int size) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mCollectionSum.setText("收藏夹"+"("+size+")");
            }
        });

    }

    @Override
    public void onLoading() {
        mUiLoader.updateStatus(UILoader.UIStatus.LOADING);
    }

    @Override
    public void onFinish() {
        mUiLoader.updateStatus(UILoader.UIStatus.SUCCESS);
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mManagerPresenter != null) {
            mManagerPresenter.unRegesiterView(this);;
        }
        LogUtil.d(TAG,"onDestroy");
    }
}
