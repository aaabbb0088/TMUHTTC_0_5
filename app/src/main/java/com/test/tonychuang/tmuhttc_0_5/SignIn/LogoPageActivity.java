package com.test.tonychuang.tmuhttc_0_5.SignIn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.tonychuang.secrettextview.SecretTextView;
import com.test.tonychuang.tmuhttc_0_5.R;

public class LogoPageActivity extends AppCompatActivity {

    SecretTextView secretTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo_page);

        initBar();

        secretTextView = (SecretTextView)findViewById(R.id.textview);
        secretTextView.setIsVisible(true);
        secretTextView.show();

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
}
