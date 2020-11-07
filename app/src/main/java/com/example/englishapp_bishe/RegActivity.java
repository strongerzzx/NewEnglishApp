package com.example.englishapp_bishe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import interfaces.IRegCallback;
import presenters.RegPresent;

public class RegActivity extends AppCompatActivity implements IRegCallback {

    private static final String TAG = "RegActivity";
    private Button mConfirm;
    private EditText mPswd;
    private EditText mAccount;
    private RegPresent mPresent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);


        getWindow().setStatusBarColor(getResources().getColor(R.color.loginWindow));
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);


        mPresent = RegPresent.getPresent();
        mPresent.regRegCallback(this);

        initView();
    }

    private void initView() {
        mAccount = findViewById(R.id.et_reg_account);
        mPswd = findViewById(R.id.et_reg_pswd);
        mConfirm = findViewById(R.id.btn_reg_confirm);


        mConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String account = mAccount.getText().toString();
                String pswd = mPswd.getText().toString();

                mPresent.getAccount(account);
                mPresent.getPswd(pswd);

                mPresent.requestReg();

                Intent intent=new Intent(RegActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

}
