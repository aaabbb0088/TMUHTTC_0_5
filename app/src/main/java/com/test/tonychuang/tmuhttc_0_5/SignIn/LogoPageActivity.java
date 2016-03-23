package com.test.tonychuang.tmuhttc_0_5.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.tonychuang.secrettextview.SecretTextView;
import com.test.tonychuang.tmuhttc_0_5.R;
import com.test.tonychuang.tmuhttc_0_5.Z_other.ShrPref.SignInShrPref;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LogoPageActivity extends AppCompatActivity {

    private SecretTextView secretTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);

        initBar();

        secretTextView = (SecretTextView) findViewById(R.id.textview);
        secretTextView.setIsVisible(true);
        secretTextView.setDuration(0);
        secretTextView.show();


        /**
         * 1.判斷SignInShrPref裡面的PID是否是有效值(非error) 是-2、否-登入頁面
         * 2.判斷SignInShrPref裡面的SignInStatus是否是登入狀態 是-3、否-登入頁面
         * 3.判斷SignInShrPref裡面的SIGNINDATETIME與現在相差是否在7天內 是-4、否-登入頁面
         * 4.判斷SignInShrPref裡面的PID是否是遠距會員->記到SignInShrPref裡面的MEMBERFLAG
         * 5.判斷SignInShrPref裡面的SameSignInMachine->記到SignInShrPref裡面的SameSignInMachine，決定是否更新設定相關資料
         */

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.TAIWAN);
        SignInShrPref signInShrPref = new SignInShrPref(this);
        if (!signInShrPref.getPID().equals("error")) {
            try {
                if (7 >= differ(simpleDateFormat.parse(signInShrPref.getSignInDatetime()), new Date())) {
                    //判斷PID是不是遠距會員
                    //判斷與上次使用的裝飾是否相同
                    //利用signInShrPref裡的帳號密碼登入
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogoPageActivity.this, SignInActivity.class);
                startActivity(intent);
                LogoPageActivity.this.finish();
            }
        }, 3000);
    }

    private void initBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }


    public long differ(Date date1, Date date2) {
        //return date1.getTime() / (24*60*60*1000) - date2.getTime() / (24*60*60*1000);
        return date2.getTime() / 86400000 - date1.getTime() / 86400000;  //用立即數，減少乘法計算的開銷
    }
}
