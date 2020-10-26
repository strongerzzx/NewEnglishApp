package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishapp_bishe.R;

import java.util.List;

import adapters.ManagerDetailAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import entirys.Words;
import interfaces.IGameCallback;
import presenters.GamePresenter;
import utils.LogUtil;


public class WinnerWordsFragment extends Fragment implements IGameCallback {


    private static final String TAG = "WinnerWordsFragment";
    private Unbinder mBind;

    @BindView(R.id.winner_words_rv)
    public RecyclerView mWinnerWordsRv;
    private ManagerDetailAdapter mAdapter;

    public WinnerWordsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View inflate = LayoutInflater.from(getContext()).inflate(R.layout.fragment_winner_words, container, false);
        mBind = ButterKnife.bind(this, inflate);

        return inflate;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        GamePresenter presenter = GamePresenter.getPresenter();
        presenter.regesiterView(this);

        mWinnerWordsRv.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new ManagerDetailAdapter();
        mWinnerWordsRv.setAdapter(mAdapter);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mBind.unbind();
    }

    @Override
    public void showTitle(String title) {

    }

    @Override
    public void showCorrect(String correct) {

    }

    @Override
    public void showCorrectAndError(String correct, String error1, String error2, String error3, String error4) {

    }

    @Override
    public void showWinnerWordList(List<Words> mList) {

        LogUtil.d(TAG,"size --> "+mList.size());
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.setData(mList);
            }
        });
    }
}