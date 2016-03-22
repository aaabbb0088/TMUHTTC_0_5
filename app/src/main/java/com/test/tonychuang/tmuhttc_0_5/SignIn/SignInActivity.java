package com.test.tonychuang.tmuhttc_0_5.SignIn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.processbutton.iml.ActionProcessButton;
import com.maksim88.passwordedittext.PasswordEditText;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.SignIn.Ft1_active.SignInActiveActivity;
import com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget.SignInForgetActivity;

import java.util.Random;

public class SignInActivity extends AppCompatActivity {


    private Button activeBtn;
    private Button forgetBtn;
    private ActionProcessButton btnSignIn;
    private MaterialEditText editPid;
    private PasswordEditText editPassword;
    private ImageView pleaseWaitImgView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        initBar();
        initView();

        processBtnTest();
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD); // Specify that tabs should be displayed in the action bar.
            //禁用ActionBar標題
            actionBar.setDisplayShowTitleEnabled(false);
            //禁用ActionBar圖標
            actionBar.setDisplayUseLogoEnabled(false);
            //禁用ActionBar返回鍵
            actionBar.setDisplayShowHomeEnabled(false);

            //自定義 ActionBar
            ActionBar.LayoutParams lp = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT,
                    Gravity.CENTER);
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title, null);
            TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
            actionBarText.setText("台北醫學大學 遠距照護中心");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void initView() {
        activeBtn = (Button) findViewById(R.id.activeBtn);
        forgetBtn = (Button) findViewById(R.id.forgetBtn);
        btnSignIn = (ActionProcessButton) findViewById(R.id.btnSignIn);
        editPid = (MaterialEditText) findViewById(R.id.editPid);
        editPassword = (PasswordEditText) findViewById(R.id.editPassword);
        pleaseWaitImgView = (ImageView) findViewById(R.id.pleaseWaitImgView);
    }

    private void processBtnTest() {
        btnSignIn.setMode(ActionProcessButton.Mode.ENDLESS);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnSignIn.setProgress(50);
                pleaseWaitImgView.setVisibility(View.VISIBLE);
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    int mProgress;

                    @Override
                    public void run() {
                        mProgress += 10;
                        if (mProgress < 100) {
                            handler.postDelayed(this, new Random().nextInt(1000));
                        } else {
                            btnSignIn.setProgress(100);
//                            Toast.makeText(SignInActivity.this, "Loading Complete, button is disabled", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                            SignInActivity.this.finish();
                        }
                    }
                }, new Random().nextInt(1000));
                btnSignIn.setEnabled(false);
                editPid.setEnabled(false);
                editPassword.setEnabled(false);
                activeBtn.setEnabled(false);
                forgetBtn.setEnabled(false);
            }
        });

        activeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(SignInActivity.this, SignInActiveActivity.class);
                startActivity(intent);
            }
        });

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(SignInActivity.this, SignInForgetActivity.class);
                startActivity(intent);
            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */


    /**
     * d1
     */
    /**
     *
     */
    /**
     * 登入動作
     * 1.輸入身分證字號、密碼，呼叫登入API
     * 2.回傳登入結果(無此帳號、帳號或密碼錯誤、系統發生錯誤)
     * 3.登入成功，將登入結果寫入SignInShrPref(帳號[加密過]、密碼[加密過]、登入時間、登入狀態)
     */




    /**
     * d2
     */
    /**
     *
     */
}
