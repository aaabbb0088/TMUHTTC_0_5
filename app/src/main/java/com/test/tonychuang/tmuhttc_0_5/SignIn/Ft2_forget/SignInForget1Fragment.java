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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.test.tonychuang.tmuhttc_0_5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInForget1Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;

    private Button changOnLineBtn;
    private Button changOnPhoneBtn;


    public SignInForget1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_forget1, container, false);
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
        changOnLineBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content, new SignInForget2Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                        .commit();
            }
        });

        changOnPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "電話連略遠距健康中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initViews() {
        changOnLineBtn = (Button) view.findViewById(R.id.changOnLineBtn);
        changOnPhoneBtn = (Button) view.findViewById(R.id.changOnPhoneBtn);
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
            actionBarText.setText("步驟 1 選擇修改密碼方法");
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
     * 忘記密碼第一步-選擇修改密碼方法
     * 1.線上修改密碼
     *   輸入身分證、Email，比對是否正確，接收結果(1.正確,成功產生註冊碼，並寄出信件，跳到下一頁 2.不正確,顯示不正確,顯聯絡遠距照護中心 3.系統發生錯誤,請聯絡遠距中心)
     * 2.直接連絡遠距中心,關閉APP
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
