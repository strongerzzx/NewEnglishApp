package com.example.englishapp_bishe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import utils.LogUtil;
import views.CollectionDialog;

public class TakePhotoGetWordActivity extends AppCompatActivity {

    private static final String TAG = "TakePhotoGetWordActivity";

    @BindView(R.id.take_collection)
    public ImageView takeCollection;
    @BindView(R.id.take_home)
    public ImageView takeHome;
    @BindView(R.id.take_content)
    public EditText takeContent;
    private String mPhotoStr;
    private CollectionDialog mCollectionDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo_get_word);


        ButterKnife.bind(this);

        mCollectionDialog = new CollectionDialog(this);

        Intent intent = getIntent();
        mPhotoStr = intent.getStringExtra("photo");
        LogUtil.d(TAG,"photoStr --> "+ mPhotoStr);

        initEvent();
    }

    private void initEvent() {

        takeContent.setText("");
        takeContent.setText(mPhotoStr);

        //返回Home
        takeHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(TakePhotoGetWordActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

        //收藏扫描的单词
        takeCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCollectionDialog.show();
            }
        });
    }
}
