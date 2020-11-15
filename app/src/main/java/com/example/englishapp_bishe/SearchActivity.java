package com.example.englishapp_bishe;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adapters.SearchResultAdapter;
import entirys.WordClips;
import entirys.Words;
import fragments.EmptyFragment;
import interfaces.ICollectionDialogCallback;
import interfaces.ISearchCallback;
import presenters.CollectionDialogPresent;
import presenters.DetailPresent;
import presenters.SearchPresent;
import utils.LogUtil;
import views.CollectionDialog;
import views.SuggestPopWindow;

public class SearchActivity extends AppCompatActivity implements ISearchCallback, ICollectionDialogCallback {

    private static final String TAG = "SearchActivity";
    private RecyclerView mResultRv;
    private ImageView mIvDelete;
    private TextView mSearch;
    private EditText mEtInput;
    private ImageView mIvFinish;
    private SearchPresent mSearchPresent;
    private InputMethodManager mIm;
    private SearchResultAdapter mResultAdapter;
    private SuggestPopWindow mSuggestPop;
    private FrameLayout mEmptyLayout;
    private EmptyFragment mEmptyFragment;
    private CollectionDialogPresent mCollectionDialogPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        mIm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        mSearchPresent = SearchPresent.getPresent();
        mCollectionDialogPresent = CollectionDialogPresent.getPresent();
        mSearchPresent.regesiterView(this);
        mCollectionDialogPresent.regesiterView(this);


        initView();

        initEvent();
    }

    private void initEvent() {
        mEtInput.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEtInput.requestFocus();
                mIm.showSoftInput(mEtInput,InputMethodManager.SHOW_IMPLICIT);
            }
        },400);

        //返回
        mIvFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //情空
        mIvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mEtInput.setText("");
            }
        });

        //搜索
        mSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newStr = mEtInput.getText().toString().replaceAll(" ", "");
                String s = newStr.replaceAll("\\p{Punct}", "");
                LogUtil.d(TAG,"s-->"+s);
                if (TextUtils.isEmpty(s)){
                    Toast.makeText(SearchActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    mIm.hideSoftInputFromWindow(mEtInput.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
                    mSearchPresent.doSearchResult(s);
                    mResultRv.setVisibility(View.VISIBLE);
                    mSuggestPop.dismiss();
                    updatePopBg(1.0f);
                }
            }
        });

        //联想词
        mEtInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String newStr = s.toString().replaceAll(" ","");
                String suggestStr = newStr.replaceAll("\\p{Punct}", "");
                LogUtil.d(TAG,"suggest -->"+suggestStr);
                if (TextUtils.isEmpty(suggestStr)){
                    mIvDelete.setVisibility(View.GONE);
                    mSuggestPop.dismiss();
                }else{
                    mIvDelete.setVisibility(View.VISIBLE);
                    mSearchPresent.doSuggestSearch(suggestStr);
                    //显示Pop --> 调整pop亮度
                    mSuggestPop.showAsDropDown(mEtInput,0,0, Gravity.LEFT);
                    updatePopBg(0.8f);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        //跳转到详情页
        mResultAdapter.setOnItemSearchResultClickListener(new SearchResultAdapter.onItemSearchResultClickListener() {
            @Override
            public void onSearchResultClickListner(int position, List<Words> mWords) {
                Intent intent=new Intent(SearchActivity.this,CikuDetailActivity.class);
                DetailPresent.getPresent().getCikuData(position+1,mWords);
                startActivity(intent);
            }
        });

        //弹出Dialog并收藏
        mResultAdapter.setOnItemSearchResultCollectionClick(new SearchResultAdapter.onItemSearchResultCollectionClick() {
            @Override
            public void onSearchResultCollectionClick(int position, List<Words> words) {
                CollectionDialog dialog=new CollectionDialog(SearchActivity.this);

                mCollectionDialogPresent.setRestDate();
                mCollectionDialogPresent.getPicText(words,(position+1));

                dialog.show();
            }
        });
    }

    private void updatePopBg(float alpha) {
        WindowManager.LayoutParams attributes = getWindow().getAttributes();
        attributes.alpha=alpha;
        getWindow().setAttributes(attributes);
    }

    private void initView() {
        mEmptyFragment = new EmptyFragment();
        mEmptyLayout = findViewById(R.id.search_empty);
        mSuggestPop = new SuggestPopWindow(SearchActivity.this);
        mIvFinish = findViewById(R.id.search_left_arrow);
        mEtInput = findViewById(R.id.search_input);
        mIvDelete = findViewById(R.id.search_input_delete);
        mSearch = findViewById(R.id.search_btn);
        mResultRv = findViewById(R.id.search_result_rv);
        mResultRv.setLayoutManager(new LinearLayoutManager(this));
        //样式一样 --> 就用了
        mResultAdapter = new SearchResultAdapter();
        mResultRv.setAdapter(mResultAdapter);
    }

    @Override
    public void showSearchResult(List<Words> searchWords) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (searchWords != null) {
                    mResultAdapter.setData(searchWords);
                }
            }
        });
    }

    @Override
    public void showSearchSuggest(List<Words> suggestWords) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mSuggestPop.getWords(suggestWords);
                LogUtil.d(TAG,"suggestSize -->"+suggestWords.size());
            }
        });

    }

    @Override
    public void onEmpty() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().add(R.id.search_empty,mEmptyFragment).commit();
            }
        });
    }

    @Override
    public void onSuccessData() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getSupportFragmentManager().beginTransaction().remove(mEmptyFragment).commit();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSearchPresent != null) {
            mSearchPresent.unRegesiterView(this);
        }

        if (mCollectionDialogPresent != null) {
            mCollectionDialogPresent.unRegesiterView(this);
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

    }
}
