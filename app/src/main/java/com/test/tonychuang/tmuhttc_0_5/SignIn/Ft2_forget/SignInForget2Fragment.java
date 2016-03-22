package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.tonychuang.tmuhttc_0_5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInForget2Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;


    public SignInForget2Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_forget2, container, false);
        initBar();
        initViews();

        initBottomBar();

        return view;
    }


    /**
     * v1
     */
    /**
     *
     */

    private void initBottomBar() {
        TextView sendTv = (TextView) view.findViewById(R.id.sendTv);
        sendTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content, new SignInForget3Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                        .commit();
            }
        });
    }

    private void initViews() {
    }

    void initBar() {
        actionBar = SignInForgetActivity.actionBar;
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
            LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            @SuppressLint("InflateParams") View titleView = inflater.inflate(R.layout.action_bar_title_active, null);
            TextView actionBarText = (TextView) titleView.findViewById(R.id.actionBarText);
            actionBarText.setText("步驟 2 輸入驗證碼");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
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
     * 忘記密第二步-輸入驗證碼
     * 1.輸入驗證碼
     * 2.如果超時，可按重新獲得驗證碼鍵(驗證碼鍵有倒數計時)
     * 3.輸入驗證碼，送出驗證碼，取得結果(1.成功,跳到下一頁，2.驗證碼錯誤,重新申請驗證碼，3.系統發生異常)
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
