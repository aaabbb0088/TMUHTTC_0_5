package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.tonychuang.tmuhttc_0_5.R;


/**
 * 1.聯絡遠距中心or申請修改密碼 -> 填寫身分證字號、Email (Dialog)
 * 2.輸入驗證碼頁面
 * 3.輸入新的密碼頁面(輸入新密碼、double check密碼)
 */


public class SignInForgetActivity extends AppCompatActivity {

    static ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_forget);
        actionBar = getSupportActionBar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content, new SignInForget1Fragment()).commit();
        }
    }

    // 返回鍵->回到上一個Fragment
    @Override
    public void onBackPressed() {
        FragmentManager fm = this.getSupportFragmentManager();

        if (fm.getBackStackEntryCount() == 0) {
            this.finish();
        } else {
            fm.popBackStack();
        }
    }
}
