package com.example.englishapp_bishe;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import interfaces.ILoginCallback;
import presenters.LoginPresent;

public class MainActivity extends AppCompatActivity implements ILoginCallback {

    private static final String TAG = "LoginActivity";
    private Button mBtnLogin;
    private TextView mBtnReg;
    private EditText mEtAccount;
    private EditText mEtPswd;
    private CheckBox mRememberPswd;
    private LoginPresent mPresent;
    private SharedPreferences mSp;
    private SharedPreferences.Editor mEdit;
    private InputMethodManager mIm;
   // private CustomProgressDialog mDialog;
    private long mCurrentProgress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(getResources().getColor(R.color.loginWindow));

        //小键盘管理
        mIm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        mSp = getSharedPreferences("Login",MODE_PRIVATE);
        mPresent = LoginPresent.getPresent();
        mPresent.regLoginCallback(this);

        initView();

        boolean isRem = mSp.getBoolean("isRem", false);
        if (isRem){
            String user = mSp.getString("username", "");
            String pswd = mSp.getString("pswd", "");
            mEtPswd.setText(pswd);
            mEtAccount.setText(user);
            mRememberPswd.setChecked(true);
        }

        initEvent();
    }

    private void initEvent() {
        //进入后延迟直接弹出小键盘
        mEtAccount.postDelayed(new Runnable() {
            @Override
            public void run() {
                mEtAccount.requestFocus();
                mIm.showSoftInput(mEtAccount,InputMethodManager.SHOW_IMPLICIT);
            }
        },50);


        //注册
        mBtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到词书选择界面
                Intent intent=new Intent(MainActivity.this,RegActivity.class);
                startActivity(intent);
            }
        });

        //登陆
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = mEtAccount.getText().toString();
                String pswd = mEtPswd.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pswd)){
                    Toast.makeText(MainActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    mEdit = mSp.edit();
                    //如果勾选了 就显示密码
                    if (mRememberPswd.isChecked()){
                        //记住密码
                        mEdit.putBoolean("isRem",true);
                        mEdit.putString("username",user);
                        mEdit.putString("pswd",pswd);
                    }else{
                        mEdit.clear();
                    }


                    mPresent.setUser(user);
                    mPresent.setPswd(pswd);
                    mPresent.doLogin();
                    mEdit.commit();

                }
            }
        });
    }

    private void initView() {
        mBtnLogin = findViewById(R.id.btn_login);
        mBtnReg = findViewById(R.id.btn_reg);
        mEtAccount = findViewById(R.id.et_account);
        mEtPswd = findViewById(R.id.et_pswd);
        mRememberPswd = findViewById(R.id.ckb_remeber_pswd);

      //  mDialog = new CustomProgressDialog(LoginActivity.this);
    }


    @Override
    public void getLoadingLength(long progress) {
        Intent intent=new Intent(MainActivity.this,SelectorBookActivity.class);
        startActivity(intent);
        finish();
    }
}
