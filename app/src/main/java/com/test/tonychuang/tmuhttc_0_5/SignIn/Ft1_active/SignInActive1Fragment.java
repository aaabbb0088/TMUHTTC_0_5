package com.test.tonychuang.tmuhttc_0_5.SignIn.Ft1_active;


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
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.test.tonychuang.tmuhttc_0_5.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SignInActive1Fragment extends Fragment {


    private ActionBar actionBar;
    private View view;
    private RadioGroup memberRdioGrp;
    private LinearLayout memberLayout;
    private LinearLayout unmemberLayout;
    private TextView nextTv;


    public SignInActive1Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sign_in_active1, container, false);
        initBar();
        initViews();
        initBottomBar();
        initListener();

        return view;
    }



    /**
     * v1
     */
    /**
     *
     */

    void initBar() {
        actionBar = SignInActiveActivity.actionBar;
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
            actionBarText.setText("步驟 1 輸入基本資料");
            actionBarText.setTextColor(Color.WHITE);
            actionBar.setCustomView(titleView, lp);
            actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
        }
    }

    private void initViews() {
        memberRdioGrp = (RadioGroup) view.findViewById(R.id.memberRdioGrp);
        memberLayout = (LinearLayout) view.findViewById(R.id.memberLayout);
        memberLayout.setVisibility(View.GONE);
        unmemberLayout = (LinearLayout) view.findViewById(R.id.unmemberLayout);
        unmemberLayout.setVisibility(View.GONE);
    }

    private void initBottomBar() {
        nextTv = (TextView) view.findViewById(R.id.nextTv);
        nextTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.content, new SignInActive2Fragment())    // 也可用.add()，差在原Fragment會不會觸發destory
                        .commit();
            }
        });
//        setTvEnabledFalse(nextTv);
    }

    private void initListener() {
        memberRdioGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.memberBtn:
                        memberLayout.setVisibility(View.VISIBLE);
                        unmemberLayout.setVisibility(View.GONE);
                        //清空unmemberLayout裡面所有的Ed
                        break;
                    case R.id.unmemberBtn:
                        unmemberLayout.setVisibility(View.VISIBLE);
                        memberLayout.setVisibility(View.GONE);
                        //清空memberLayout裡面所有的Ed
                        break;
                }
            }
        });
    }


    /**
     * v2
     */
    /**
     *
     */
    private void setTvEnabledFalse(TextView textView){
        textView.setEnabled(false);
        textView.setBackgroundResource(R.drawable.background_active_btn_true);
    }

    private void setTvEnabledTrue(TextView textView){
        textView.setEnabled(false);
        textView.setBackgroundResource(R.drawable.selector_active_forget_tv);
    }


    /**
     * d1
     */
    /**
     *
     */
    /**
     * 註冊帳號第一步-輸入基本資料
     * 1.
     */


    /**
     * d2
     */
    /**
     *
     */
}
