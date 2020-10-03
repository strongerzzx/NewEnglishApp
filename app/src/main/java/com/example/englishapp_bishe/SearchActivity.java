package com.example.englishapp_bishe;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import adapters.SearchResultAdapter;
import entirys.Words;
import interfaces.ISearchCallback;
import presenters.SearchPresent;

public class SearchActivity extends AppCompatActivity implements ISearchCallback {

    private RecyclerView mResultRv;
    private ImageView mIvDelete;
    private TextView mSearch;
    private EditText mEtInput;
    private ImageView mIvFinish;
    private SearchPresent mSearchPresent;
    private InputMethodManager mIm;
    private SearchResultAdapter mResultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //TODO:搜索界面
        mIm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        mSearchPresent = SearchPresent.getPresent();
        mSearchPresent.regesiterView(this);
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
                if (TextUtils.isEmpty(newStr)){
                    Toast.makeText(SearchActivity.this, "内容不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    mIm.hideSoftInputFromWindow(mEtInput.getWindowToken(),InputMethodManager.HIDE_IMPLICIT_ONLY);
                    mSearchPresent.doSearchResult(newStr);
                    mResultRv.setVisibility(View.VISIBLE);
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
                String suggestStr = s.toString();
                if (TextUtils.isEmpty(suggestStr)){
                    mIvDelete.setVisibility(View.GONE);
                }else{
                    mIvDelete.setVisibility(View.VISIBLE);
                    mSearchPresent.doSuggestSearch(suggestStr);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
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

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mSearchPresent != null) {
            mSearchPresent.unRegesiterView(this);
        }
    }
}
