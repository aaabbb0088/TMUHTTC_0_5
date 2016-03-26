package com.test.tonychuang.tmuhttc_0_5.Z_other.LittleWidgetModule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.test.tonychuang.tmuhttc_0_5.R;

import me.grantland.widget.AutofitTextView;

/**
 * Created by TonyChuang on 2016/3/18.
 */
public class MyInitReturnBar {

    private ActionBar actionBar;
    private FrameLayout actionBarLeftLayout;

    public MyInitReturnBar(final AppCompatActivity appCompatActivity, String barTitle, int barMode) {
        actionBar = appCompatActivity.getSupportActionBar();
        if (actionBar != null) {
            actionBar.setElevation(0);
            if (barMode == 0){
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
            }else{
                actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
            }
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
            LayoutInflater inflater = (LayoutInflater) appCompatActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title_return, null);
            AutofitTextView actionBarText = (AutofitTextView) titleView.findViewById(R.id.actionBarText);
            actionBarLeftLayout = (FrameLayout) titleView.findViewById(R.id.actionBarLeftLayout);
            actionBarLeftLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    appCompatActivity.onBackPressed();
                }
            });
            actionBarText.setText(barTitle);
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);

            /**
             * 顯示ActionBar & 左上角回上一頁鍵(onOptionsItemSelected()已mark)
             */
//            actionBar.setDisplayHomeAsUpEnabled(true);
//            actionBar.setHomeButtonEnabled(true);
//            actionBar.setTitle("關心 會員暱稱");
        }
    }

    public ActionBar getActionBar() {
        return actionBar;
    }
    public FrameLayout getActionBarLeftLayout(){
        return actionBarLeftLayout;
    }
}
