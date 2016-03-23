package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft1_active;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.tonychuang.tmuhttc_0_5.R;

public class SignInActiveActivity extends AppCompatActivity {


    static ActionBar actionBar;
    static String pid;
    static String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_active);
        actionBar = getSupportActionBar();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.content, new SignInActive1Fragment()).commit();
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


    /**
     * v1
     */
    /**
     *
     */


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
     * d2
     */
    /**
     *
     */





}
