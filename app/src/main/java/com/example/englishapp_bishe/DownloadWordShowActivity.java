package com.example.englishapp_bishe;

import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import commonparms.Commons;

public class DownloadWordShowActivity extends AppCompatActivity {

    private static final String TAG = "DownloadWordShowActivity";
    @BindView(R.id.down_word_show)
    public TextView mShowTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_word_show);

        ButterKnife.bind(this);

        initEvent();
    }

    private void initEvent() {
        File exterFile = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        File downFile = new File(exterFile.getPath()+File.separator +Commons.getmCurrentDownloadTitle()+".txt");

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(downFile));
            String len ="";
            while ((len=reader.readLine())!=null){
                sb.append(len+"\n\n");
            }
            mShowTv.setText(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}