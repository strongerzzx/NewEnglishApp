package fragments;


import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp_bishe.CollectionManagerDetailActivity;
import com.example.englishapp_bishe.R;

import java.util.List;

import adapters.CollectionManagerAdapter;
import entirys.WordClips;
import interfaces.ICollectionDialogCallback;
import presenters.CollectionDialogPresent;
import presenters.ManagerDetailPresent;
import utils.LogUtil;
import views.CollectionManagerPopWindow;
import views.NewWordsCollectionDialog;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment implements ICollectionDialogCallback {


    private static final String TAG = "CollectionFragment";
    private View mInflate;
    private TextView mCollectionSum;
    private ImageView mCollectionAdd;
    private CollectionManagerAdapter mManagerAdapter;
    private CollectionDialogPresent mCollectionPresent;
    private RecyclerView mManagerRv;
    private CollectionManagerPopWindow mManagerPop;
    private CollectionManagerPopWindow mPop;

    public CollectionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        LogUtil.d(TAG,"onCreateView ");



        mInflate = LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_collection, container, false);

        mCollectionPresent = CollectionDialogPresent.getPresent();
        mCollectionPresent.regesiterView(this);
        mCollectionPresent.queryAllClips();

        mPop = new CollectionManagerPopWindow(getContext());
        initView();


        return mInflate;
    }


    private void initView() {
        mManagerPop = new CollectionManagerPopWindow(getContext());
        mCollectionSum = mInflate.findViewById(R.id.collection_manager_sum);
        mCollectionAdd = mInflate.findViewById(R.id.collection_manager_add);
        mManagerRv = mInflate.findViewById(R.id.collection_manager_rv);
        mManagerRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mManagerAdapter=new CollectionManagerAdapter();
        mManagerRv.setAdapter(mManagerAdapter);
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

        mCollectionPresent.queryAllClips();
        LogUtil.d(TAG,"onResume");
    }


    private void initEvent() {
        //添加收藏夹
        mCollectionAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewWordsCollectionDialog newDialog=new NewWordsCollectionDialog(getContext());
                newDialog.show();
            }
        });

        //弹出PopWindow --> 显示pop的title
        mManagerAdapter.setIvOnCollectionManagerClickListener(new CollectionManagerAdapter.onIvCollectionManagerClickListener() {
            @Override
            public void onCollectionManagerClick(int pos,View view) {
                LogUtil.d(TAG,"fragment pos -->"+pos);
                if (mPop != null) {
                    mPop.setFocusable(true);
                }
                if (!mPop.isShowing()){
                    mPop.getManageInfoPos(pos);
                    mPop.showAtLocation(view, Gravity.BOTTOM,0,0);
                }

                updateBgAlpha(0.7f);
            }
        });


        mPop.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                updateBgAlpha(1.0f);
                //消失的时候在查一次 --> 更新UI
                mCollectionPresent.queryAllClips();
            }
        });

        //点击收藏夹 --> 显示所有单词 -->根据对应的收藏夹ID 查找
        mManagerAdapter.setOnCollectionManagerClickListener(new CollectionManagerAdapter.onCollectionManagerClickListener() {
            @Override
            public void onCollectionManagerClick(int clipsID) {

                Intent intent=new Intent(getActivity(), CollectionManagerDetailActivity.class);
                //把收藏夹的ID --> 给单词夹详情的P
                ManagerDetailPresent.getPresent().getCollectionID(clipsID);

                startActivity(intent);
                LogUtil.d(TAG,"收藏管理者的收藏夹ID -->"+clipsID);
            }
        });
    }

    private void updateBgAlpha(float alpha) {
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha=alpha;
        getActivity().getWindow().setAttributes(attributes);
    }


    @Override
    public void onPause() {
        super.onPause();
        LogUtil.d(TAG,"onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        LogUtil.d(TAG,"onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mCollectionPresent != null) {
            mCollectionPresent.unRegesiterView(this);
        }
        if (mPop != null) {
            mPop.dismiss();
        }
        LogUtil.d(TAG,"onDestroy");
    }

    @Override
    public void showAllClips(List<WordClips> clipsList) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                mManagerAdapter.setData(clipsList);
            }
        });
    }

    @Override
    public void getAllClipsTitle(String title) {

    }
}
