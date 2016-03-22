package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft2_forget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.tonychuang.tmuhttc_0_5.MainActivity;
import com.test.tonychuang.tmuhttc_0_5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInForget3Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;


    public SignInForget3Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_forget3, container, false);
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
                Intent intent = new Intent().setClass(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().finish();
                //1.正是情況是要跳到登入頁面，要求再次輸入帳號密碼
                //2.成功註冊後，還要再寄一封記有會員名稱、會員aid、會員新密碼並說明密碼已經變更的信件
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
            actionBarText.setText("步驟 3 輸入新密碼");
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
     * 忘記密碼第三步-輸入新密碼
     * 1.輸入新密碼、確認新密碼，判斷兩者是否相等
     * 2.送出新密碼，接收結果(true-修改成功,跳到登入頁面，要求再次輸入帳號密碼 false-修改失敗,系統發生錯誤,請重新修改密碼或直接聯絡遠距照護中心)
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
