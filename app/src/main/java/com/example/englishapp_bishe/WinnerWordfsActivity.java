package com.example.englishapp_bishe;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adapters.CikuAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import entirys.Words;
import presenters.CollectionDialogPresent;
import presenters.DetailPresent;
import presenters.GamePresenter;
import views.CollectionDialog;

public class WinnerWordfsActivity extends AppCompatActivity {

    private static final String TAG = "WinnerWordfsActivity";
    @BindView(R.id.winner_words_rv_act)
    public RecyclerView mWinnerWordsRv;
    private GamePresenter mGamePresenter;
    private CikuAdapter mAdapter;
    private CollectionDialog mCollectionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_wordfs);

        ButterKnife.bind(this);
        mGamePresenter = GamePresenter.getPresenter();

        initEvent();
    }

    private void initEvent() {

        mWinnerWordsRv.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new CikuAdapter();
        View headerView = LayoutInflater.from(this).inflate(R.layout.ciku_rv_header, mWinnerWordsRv, false);
        mAdapter.setHeaderView(headerView);
        mAdapter.setData(mGamePresenter.getRandomWords());
        mWinnerWordsRv.setAdapter(mAdapter);

        mAdapter.setOnCiKuItemClickListener(new CikuAdapter.onCiKuItemClickListener() {
            @Override
            public void onCiKuClickListener(int position, List<Words> currentWords) {
                Intent intent=new Intent(WinnerWordfsActivity.this,CikuDetailActivity.class);

                //详情页从这获取点击的单词
                DetailPresent.getPresent().getCikuData(position,currentWords);
                startActivity(intent);
            }
        });

        mAdapter.setOnCikuCollectionMoreClickListener(new CikuAdapter.onCikuCollectionMoreClickListener() {
            @Override
            public void onCikuCollectionMoreClick(int pos, List<Words> currentWords) {
                mCollectionDialog = new CollectionDialog(WinnerWordfsActivity.this);;
                CollectionDialogPresent.getPresent().getPicText(currentWords,pos);
                mCollectionDialog.show();
            }
        });
    }

}